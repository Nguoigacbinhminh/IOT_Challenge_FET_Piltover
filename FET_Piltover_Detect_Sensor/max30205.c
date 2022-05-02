/*
 * max30205.c
 *
 *  Created on: 17 thg 4, 2022
 *      Author: EuTriv
 */
#include "max30205.h"
#include "app_log.h"
#include "sl_i2cspm_mikroe_config.h"
#include "sl_i2cspm.h"
#include "stdio.h"
#include "string.h"
#include <stdbool.h>
#include"ustimer.h"
#include "math.h"



sl_status_t max30205_i2c_write_to_register(uint8_t address, uint8_t data)
{
  I2C_TransferSeq_TypeDef    seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[2];
  uint8_t i2c_read_data[1];

  seq.addr = 0x90;
  seq.flags = I2C_FLAG_WRITE;
  /* Select register and data to write */
  i2c_write_data[0] = address ;
  i2c_write_data[1] = data;
  seq.buf[0].data = i2c_write_data;
  seq.buf[0].len  = 2;
  /* Select length of data to be read */
  seq.buf[1].data = i2c_read_data;
  seq.buf[1].len  = 0;

  ret = I2CSPM_Transfer(SL_I2CSPM_MIKROE_PERIPHERAL, &seq);
  if (ret != i2cTransferDone) {
    return SL_STATUS_TRANSMIT;
  }

  return SL_STATUS_OK;
}

sl_status_t max30205_i2c_block_read(uint8_t address, uint16_t length, uint8_t *data)
{
  I2C_TransferSeq_TypeDef seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[1];

  seq.addr  = 0x91;
  seq.flags = I2C_FLAG_WRITE_READ;

  i2c_write_data[0] = address;
  seq.buf[0].data = i2c_write_data;
  seq.buf[0].len  = 1;
  /* Select length of data to be read */
  seq.buf[1].data = data;
  seq.buf[1].len  = length;
  ret = I2CSPM_Transfer(SL_I2CSPM_MIKROE_PERIPHERAL, &seq);

  if (ret != i2cTransferDone) {
    return SL_STATUS_TRANSMIT;
  }

  return SL_STATUS_OK;
}

//Setup max30205
void max30205_begin(void){
  max30205_i2c_write_to_register(MAX30205_CONFIGURATION, 0x00);//mode config
  max30205_i2c_write_to_register(MAX30205_THYST, 0x00);// set threshold
  max30205_i2c_write_to_register(MAX30205_TOS, 0x00);//

}

float getTemperature(void){
  float temperature=0;

  uint8_t readRaw[2];
  max30205_i2c_block_read(MAX30205_TEMPERATURE,2, readRaw ); // read two bytes
  int16_t raw = (readRaw[0] << 8) | readRaw[1];  //combine two bytes
    temperature = raw  * 0.00390625;     // convert to
  return  temperature;
}
