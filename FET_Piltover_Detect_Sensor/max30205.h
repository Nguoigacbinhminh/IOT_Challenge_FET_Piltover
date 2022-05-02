/*
 * max30205.h
 *
 *  Created on: 17 thg 4, 2022
 *      Author: EuTriv
 */

#ifndef MAX30205_H_
#define MAX30205_H_

// Address Range


#include"stdbool.h"
#include"sl_status.h"
#define MAX30205_ADDRESS1        0x49  // 8bit address converted to 7bit
#define MAX30205_ADDRESS2        0x48  // 8bit address converted to 7bit

// Registers
#define MAX30205_TEMPERATURE    0x00  //  get temperature ,Read only
#define MAX30205_CONFIGURATION  0x01  //
#define MAX30205_THYST          0x02  //
#define MAX30205_TOS            0x03  //



typedef unsigned char uint8_t;

typedef enum{     //For configuration registers
  SHUTDOWN,       // shutdwon mode to reduce power consumption <3.5uA
  COMPARATOR,     // Bit 0 = operate OS in comparator mode, 1= INTERRUPT MODE
  OS_POLARITY,      // Polarity bit ;Bit 0 = Active low output, Bit 1 = Active high
  FAULT_QUEUE_0,    // Fault indication bits
  FAULT_QUEUE_1,    // Fault indication bits
  DATA_FORMAT,      //Data Format
  TIME_OUT,          //Time out
  ONE_SHOT           //1= One shot, 0 = Continuos
}configuration;


//float temperature = 0;
//uint8_t sensorAddress = MAX30205_ADDRESS1;

void max30205_begin(void);
float getTemperature(void);

sl_status_t max30205_i2c_write_to_register(uint8_t address, uint8_t data);
sl_status_t max30205_i2c_block_read(uint8_t address, uint16_t length, uint8_t *data);
#endif /* MAX30205_H_ */
