/***************************************************************************//**
 * @file
 * @brief Core application logic.
 *******************************************************************************
 * # License
 * <b>Copyright 2020 Silicon Laboratories Inc. www.silabs.com</b>
 *******************************************************************************
 *
 * SPDX-License-Identifier: Zlib
 *
 * The licensor of this software is Silicon Laboratories Inc.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source distribution.
 *
 ******************************************************************************/
#include "em_common.h"
#include "app_assert.h"
#include "sl_bluetooth.h"
#include "gatt_db.h"
#include "app.h"
#include "sl_app_log.h"
#include "max30100.h"
#include"ustimer.h"
#include "max30205.h"
#include "sl_simple_timer.h"

// The advertising set handle allocated from Bluetooth stack.
static uint8_t advertising_set_handle = 0xff;
pulseoxymeter_t result;
static sl_simple_timer_t timer;
static uint8_t app_connection = 0xFF;
volatile static uint8_t sent_data[12U];
volatile static uint32_t sensor_data[3U] = {0,0,0};
/***************************************************************************//**
 * Timer Callback
 * @param[in] handle pointer to handle instance
 * @param[in] data pointer to input data
 ******************************************************************************/
static void timer_cb(sl_simple_timer_t *handle, void *data);

/**************************************************************************//**
 * Application Init.
 *****************************************************************************/
SL_WEAK void app_init(void)
{
  /////////////////////////////////////////////////////////////////////////////
  // Put your additional application init code here!                         //
  // This is called once during start-up.                                    //
  /////////////////////////////////////////////////////////////////////////////
  max30100_begin();
  max30205_begin();
}

/**************************************************************************//**
 * Application Process Action.
 *****************************************************************************/
SL_WEAK void app_process_action(void)
{
  /////////////////////////////////////////////////////////////////////////////
  // Put your additional application code here!                              //
  // This is called infinitely.                                              //
  // Do not call blocking functions from here!                               //
  /////////////////////////////////////////////////////////////////////////////
    result = pulseoxymeter_begin();

    if( result.pulseDetected == true )
    {
      sensor_data[0] = result.heartBPM*100;
      sensor_data[1] = result.SpO2*100;
      sensor_data[2] = getTemperature() * 100;
    }
    USTIMER_Delay(10);
//    app_log_info(" Gia tri nhiet do: ");
//    app_show_float_value(getTemperature());
    //app_log_info("\r\n");


}

/**************************************************************************//**
 * Bluetooth stack event handler.
 * This overrides the dummy weak implementation.
 *
 * @param[in] evt Event coming from the Bluetooth stack.
 *****************************************************************************/
void sl_bt_on_event(sl_bt_msg_t *evt)
{
  sl_status_t sc;
  bd_addr address;
  uint8_t address_type;
  uint8_t system_id[8];

  switch (SL_BT_MSG_ID(evt->header)) {
    // -------------------------------
    // This event indicates the device has started and the radio is ready.
    // Do not call any stack command before receiving this boot event!
    case sl_bt_evt_system_boot_id:

      // Extract unique ID from BT Address.
      sc = sl_bt_system_get_identity_address(&address, &address_type);
      app_assert_status(sc);

      // Pad and reverse unique ID to get System ID.
      system_id[0] = address.addr[5];
      system_id[1] = address.addr[4];
      system_id[2] = address.addr[3];
      system_id[3] = 0xFF;
      system_id[4] = 0xFE;
      system_id[5] = address.addr[2];
      system_id[6] = address.addr[1];
      system_id[7] = address.addr[0];

      sc = sl_bt_gatt_server_write_attribute_value(gattdb_system_id,
                                                   0,
                                                   sizeof(system_id),
                                                   system_id);
      app_assert_status(sc);

      // Create an advertising set.
      sc = sl_bt_advertiser_create_set(&advertising_set_handle);
      app_assert_status(sc);

      // Set advertising interval to 100ms.
      sc = sl_bt_advertiser_set_timing(
        advertising_set_handle,
        160, // min. adv. interval (milliseconds * 1.6)
        160, // max. adv. interval (milliseconds * 1.6)
        0,   // adv. duration
        0);  // max. num. adv. events
      app_assert_status(sc);

      // Start general advertising and enable connections.
      sc = sl_bt_advertiser_start(
        advertising_set_handle,
        sl_bt_advertiser_general_discoverable,
        sl_bt_advertiser_connectable_scannable);
      app_assert_status(sc);
      break;

    // -------------------------------
    // This event indicates that a new connection was opened.
    case sl_bt_evt_connection_opened_id:
      app_connection = evt->data.evt_connection_opened.connection;
      app_log_info("Connection open !\r\n");
      break;

    // -------------------------------
    // This event indicates that a connection was closed.
    case sl_bt_evt_connection_closed_id:
      // Restart advertising after client has disconnected.
      sc = sl_bt_advertiser_start(
        advertising_set_handle,
        sl_bt_advertiser_general_discoverable,
        sl_bt_advertiser_connectable_scannable);
      app_assert_status(sc);
      break;

    // --------------------------------
    /* TAG: when the remote device subscribes for notification */
    case sl_bt_evt_gatt_server_characteristic_status_id:

      if (evt->data.evt_gatt_server_characteristic_status.characteristic == gattdb_stack_test) {
        app_log_info("Notify Request Change from remote client !\r\n");
        app_log_info("Status Flag = 0x%x\r\n",evt->data.evt_gatt_server_characteristic_status.status_flags);
        app_log_info("0x%x\r\n",evt->data.evt_gatt_server_characteristic_status.client_config_flags);
        sc = sl_simple_timer_start(&timer,
                                   1000,
                                   timer_cb,
                                   (void *)sent_data,
                                   true);
      }
      break;
    ///////////////////////////////////////////////////////////////////////////
    // Add additional event handlers here as your application requires!      //
    ///////////////////////////////////////////////////////////////////////////
    // -------------------------------
    // Default event handler.
    default:
      break;
  }
}

// Timer Callback
static void timer_cb(sl_simple_timer_t *handle, void *data)
{
  sl_status_t sc;
//  app_log_info( "%d \r\n" ,result.SpO2*100);
  app_log_info("\r\nHR: %d",sensor_data[0]);
  app_log_info("\r\nSPO2: %d",sensor_data[1]);
  app_log_info("\r\nTemp: %d\r\n",sensor_data[2]);
  sent_data[3] = sensor_data[0] & 0xFF;
  sent_data[2] = sensor_data[0] >> 8U;
  sent_data[7] = sensor_data[1] & 0xFF;
  sent_data[6] = sensor_data[1] >> 8U;
  sent_data[11] = sensor_data[2] & 0xFF;
  sent_data[10] = sensor_data[2] >> 8U;
  sensor_data[0] = 0;
  sensor_data[1] = 0;
  sensor_data[2] = 0;
//  app_show_float_value(result.heartBPM);
//  app_log_info("|SpO2:");
//  app_show_float_value(result.SpO2);
//  app_log_info("\r\n Temp: ");
//  app_show_float_value(getTemperature());
  sc = sl_bt_gatt_server_send_notification(app_connection, gattdb_stack_test,12,data);
  app_assert_status(sc);
}
