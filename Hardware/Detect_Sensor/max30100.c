/*
 * max30100.c
 *
 *  Created on: 1 thg 4, 2022
 *      Author: EuTriv
 */

#include "max30100.h"
#include "app_log.h"
#include "sl_i2cspm_mikroe_config.h"
#include "sl_i2cspm.h"
#include "stdio.h"
#include "string.h"
#include <stdbool.h>
#include"ustimer.h"
#include "math.h"
#include <time.h>
#include "em_rtcc.h"
#include "em_cmu.h"
#include "em_chip.h"
#include "em_device.h"
#include "core_cm33.h"
#include <stdint.h>

/* ########################################################################## */
/*                             Mecros Defn                                    */
/* ########################################################################## */
#define I2C_BUFFER_SIZE 10

/* ########################################################################## */
/*                       Local functions declarations                         */
/* ########################################################################## */
//    static void setupRtcc();

    static unsigned long millis();
    static bool detectPulse(float sensor_value);
    static void balanceIntesities( float redLedDC, float IRLedDC );
    static uint8_t Systick_Init(uint32_t ticks);
    static bool debug;
//    static clock_t start;
    static uint8_t redLEDCurrent;
    static unsigned long lastREDLedCurrentCheck;

    static uint8_t currentPulseDetectorState;
    static float currentBPM;
    static float valuesBPM[PULSE_BPM_SAMPLE_SIZE];
    static float valuesBPMSum;
    static uint8_t valuesBPMCount;
    static uint8_t bpmIndex;
    static uint32_t lastBeatThreshold;

//    static fifo_t prevFifo;

    static dcFilter_t dcFilterIR;
    static dcFilter_t dcFilterRed;
    static butterworthFilter_t lpbFilterIR;
    static meanDiffFilter_t meanDiffIR;

    static float irACValueSqSum;
    static float redACValueSqSum;
    static uint16_t samplesRecorded;
    static uint16_t pulsesDetected;
    static float currentSpO2Value;

    static LEDCurrent IrLedCurrent;

    static float prev_sensor_value ;
    static uint8_t values_went_down ;
    static uint32_t currentBeat ;
    static uint32_t lastBeat ;
    static volatile unsigned long msTicks = 0;
/* ########################################################################## */
/*                              Local variables                              */
/* ########################################################################## */
void SysTick_Handler(void)  {                               /* SysTick interrupt Handler. */
  msTicks++;                                                /* See startup file startup_LPC17xx.s for SysTick vector */
}

static uint8_t Systick_Init(uint32_t ticks)
{
  if ((ticks - 1UL) > SysTick_LOAD_RELOAD_Msk)
  {
    return (1UL);                                                   /* Reload value impossible */
  }

  SysTick->LOAD  = (uint32_t)(ticks - 1UL);                         /* set reload register */
  NVIC_SetPriority (SysTick_IRQn, (1UL << __NVIC_PRIO_BITS) - 1UL); /* set Priority for Systick Interrupt */
  SysTick->VAL   = 0UL;                                             /* Load the SysTick Counter Value */
  SysTick->CTRL  = SysTick_CTRL_CLKSOURCE_Msk |
                   SysTick_CTRL_TICKINT_Msk   |
                   SysTick_CTRL_ENABLE_Msk;                         /* Enable SysTick IRQ and SysTick Timer */
  return (0UL);
}
/**************************************************************************//**
 * @brief Write a uint8_t to Max30100 register.
 *****************************************************************************/
sl_status_t max30100_i2c_write_to_register(uint8_t address, uint8_t data)
{
  I2C_TransferSeq_TypeDef    seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[2];
  uint8_t i2c_read_data[1];

  seq.addr = MAX30100_I2C_WRITE_ADDRESS;
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

/**************************************************************************//**
 * @brief Read a uint8_t from Max30100 register.
 *****************************************************************************/
sl_status_t max30100_i2c_read_from_register(uint8_t address, uint8_t *data)
{
  I2C_TransferSeq_TypeDef    seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[1];

  seq.addr = MAX30100_I2C_READ_ADDRESS;
  seq.flags = I2C_FLAG_WRITE_READ;
  i2c_write_data[0] = address;
  seq.buf[0].data = i2c_write_data;
  seq.buf[0].len  = 1;
  /* Select length of data to be read */
  seq.buf[1].data = data;
  seq.buf[1].len  = 1;

  ret = I2CSPM_Transfer(SL_I2CSPM_MIKROE_PERIPHERAL, &seq);
  if (ret != i2cTransferDone) {
    *data = 0x00;
    return SL_STATUS_TRANSMIT;
  }

  return SL_STATUS_OK;
}

/**************************************************************************//**
 * @brief Write a Block to Max30100.
 *****************************************************************************/
sl_status_t max30100_i2c_block_write(uint8_t address, uint8_t length, uint8_t const *data)
{
  I2C_TransferSeq_TypeDef seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[2];
  uint8_t i2c_read_data[1];

  seq.addr = MAX30100_I2C_WRITE_ADDRESS;
  seq.flags = I2C_FLAG_WRITE;
  /* Select register and data to write */
  i2c_write_data[0] = address ;
  for (int i = 0; i < length; i++) {
    i2c_write_data[i + 1] = data[i];
  }
  seq.buf[0].data = i2c_write_data;
  seq.buf[0].len  = length + 1;
  /* Select length of data to be read */
  seq.buf[1].data = i2c_read_data;
  seq.buf[1].len  = 0;
  ret = I2CSPM_Transfer(SL_I2CSPM_MIKROE_PERIPHERAL, &seq);
  if (ret != i2cTransferDone) {
    return SL_STATUS_TRANSMIT;
  }

  return SL_STATUS_OK;
}

/**************************************************************************//**
 * @brief Read a Block from Max30100.
 *****************************************************************************/
sl_status_t max30100_i2c_block_read(uint8_t address, uint16_t length, uint8_t *data)
{
  I2C_TransferSeq_TypeDef seq;
  I2C_TransferReturn_TypeDef ret;
  uint8_t i2c_write_data[1];

  seq.addr  = MAX30100_I2C_READ_ADDRESS;
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
/**************************************************************************//**
 * @brief Initial begin function to max30100
 *****************************************************************************/
void max30100_begin()
{


   Systick_Init(SystemCoreClock / 1000);
//   setupRtcc();
//   start=clock();
   debug=false;

   setMode(DEFAULT_OPERATING_MODE);

   setSamplingRate(DEFAULT_SAMPLING_RATE);
   setLEDPulseWidth(DEFAULT_LED_PULSE_WIDTH);

   redLEDCurrent=(uint8_t)STARTING_RED_LED_CURRENT;
   lastREDLedCurrentCheck = 0;

   IrLedCurrent=DEFAULT_IR_LED_CURRENT;
   setLEDCurrents(redLEDCurrent, IrLedCurrent);
   setHighresModeEnabled(true);

   dcFilterIR.w = 0;
   dcFilterIR.result = 0;

   dcFilterRed.w = 0;
   dcFilterRed.result = 0;


   lpbFilterIR.v[0] = 0;
   lpbFilterIR.v[1] = 0;
   lpbFilterIR.result = 0;

   meanDiffIR.index = 0;
   meanDiffIR.sum = 0;


   valuesBPM[0] = 0;
   valuesBPMSum = 0;
   valuesBPMCount = 0;
   bpmIndex = 0;


   irACValueSqSum = 0;
   redACValueSqSum = 0;
   samplesRecorded = 0;
   pulsesDetected = 0;
   currentSpO2Value = 0;

   lastBeatThreshold = 0;
   prev_sensor_value = 0.0;
   values_went_down = 0;
   currentBeat = 0;
   lastBeat = 0;

}
void setMode(Mode mode)
{ uint8_t value=0;
  uint8_t currentModeReg = max30100_i2c_read_from_register( MAX30100_MODE_CONF,&value );
  max30100_i2c_write_to_register( MAX30100_MODE_CONF, (currentModeReg & 0xF8) | mode );
}

void setHighresModeEnabled(bool enabled)
{   uint8_t value=0;
    uint8_t previous = max30100_i2c_read_from_register(MAX30100_SPO2_CONF,&value);
    if (enabled) {
        max30100_i2c_write_to_register(MAX30100_SPO2_CONF, previous | MAX30100_SPO2_HI_RES_EN);
    } else {
        max30100_i2c_write_to_register(MAX30100_SPO2_CONF, previous & ~MAX30100_SPO2_HI_RES_EN);
    }
}

void setSamplingRate(SamplingRate rate)
{ uint8_t value=0;
  uint8_t currentSpO2Reg = max30100_i2c_read_from_register( MAX30100_SPO2_CONF,&value );
  max30100_i2c_write_to_register( MAX30100_SPO2_CONF, ( currentSpO2Reg & 0xE3 ) | (rate<<2) );
}

void setLEDPulseWidth(LEDPulseWidth pw)
{ uint8_t value=0;
  uint8_t currentSpO2Reg = max30100_i2c_read_from_register( MAX30100_SPO2_CONF,&value );
  max30100_i2c_write_to_register( MAX30100_SPO2_CONF, ( currentSpO2Reg & 0xFC ) | pw );
}

void setLEDCurrents( uint8_t redLedCurrent, uint8_t IRLedCurrent )
{
  max30100_i2c_write_to_register( MAX30100_LED_CONF, (redLedCurrent << 4) | IRLedCurrent );
}

float readTemperature()
{ uint8_t value=0;
  uint8_t currentModeReg = max30100_i2c_read_from_register( MAX30100_MODE_CONF ,&value);
  max30100_i2c_write_to_register( MAX30100_MODE_CONF, currentModeReg | MAX30100_MODE_TEMP_EN );

  USTIMER_Delay(100); //This can be changed to a while loop, there is an interrupt flag for when temperature has been read.

  int8_t temp = (int8_t)max30100_i2c_read_from_register( MAX30100_TEMP_INT,&value );
  float tempFraction = (float)max30100_i2c_read_from_register( MAX30100_TEMP_FRACTION,&value ) * 0.0625;

  return (float)temp + tempFraction;
}

pulseoxymeter_t pulseoxymeter_begin()
{
    // Temp result = 0
    pulseoxymeter_t result={.SpO2=0.0,.dcFilteredIR=0.0,.dcFilteredRed=0.0,.heartBPM=0.0
                            ,.irCardiogram=0.0,.irDcValue=0.0,.lastBeatThreshold=0,
                            .pulseDetected=false,.redDcValue=0.0};

    // Get the value from FIFO
    fifo_t rawData = readFIFO();

    // DC Filter the value
    dcFilterIR = dcRemoval( (float)rawData.rawIR, dcFilterIR.w, ALPHA );
    dcFilterRed = dcRemoval( (float)rawData.rawRed, dcFilterRed.w, ALPHA );

    float meanDiffResIR = meanDiff( dcFilterIR.result, &meanDiffIR);
    lowPassButterworthFilter( meanDiffResIR/*-dcFilterIR.result*/, &lpbFilterIR );
    irACValueSqSum += dcFilterIR.result * dcFilterIR.result;

    redACValueSqSum += dcFilterRed.result * dcFilterRed.result;
    samplesRecorded++;
//    app_log_info("Low pass filter value : %d\r\n",lpbFilterIR.result);
     if( detectPulse( lpbFilterIR.result ) && samplesRecorded > 0 )
     {
       // Find the beat
       result.pulseDetected=true;
       pulsesDetected++;

       float ratioRMS = log( sqrt(redACValueSqSum/samplesRecorded) ) / log( sqrt(irACValueSqSum/samplesRecorded) );

       if( debug == true )
       {
         app_log_info("\r\nRMS Ratio: ");
         app_show_float_value(ratioRMS);
       }

       //This is my adjusted standard model, so it shows 0.89 as 94% saturation. It is probably far from correct, requires proper empircal calibration
       currentSpO2Value = 110.0 - 18.0 * ratioRMS;
       result.SpO2 = currentSpO2Value;

       if( pulsesDetected % RESET_SPO2_EVERY_N_PULSES == 0)
       {
         irACValueSqSum = 0;
         redACValueSqSum = 0;
         samplesRecorded = 0;
       }
     }

     balanceIntesities( dcFilterRed.w, dcFilterIR.w );


     result.heartBPM = currentBPM/10;
     result.irCardiogram = lpbFilterIR.result;
     result.irDcValue = dcFilterIR.w;
     result.redDcValue = dcFilterRed.w;
     result.lastBeatThreshold = lastBeatThreshold;
     result.dcFilteredIR = dcFilterIR.result;
     result.dcFilteredRed = dcFilterRed.result;

     return result;
}

// read from FIFO
fifo_t readFIFO()
{
  fifo_t results;
  uint8_t buffer[4];
  max30100_i2c_block_read(MAX30100_FIFO_DATA, 4, buffer);
  results.rawIR = (buffer[0] << 8) | buffer[1];
  results.rawRed = (buffer[2] << 8) | buffer[3];

  return results;
}
//DC Removal
dcFilter_t dcRemoval(float x, float prev_w, float alpha)
{
  dcFilter_t filtered;
  filtered.w = x + alpha * prev_w;
  filtered.result = filtered.w - prev_w;

  return filtered;
}
//Mean Median Filter
float meanDiff(float M, meanDiffFilter_t* filterValues)
{
  float avg = 0;

  filterValues->sum -= filterValues->values[filterValues->index];
  filterValues->values[filterValues->index] = M;
  filterValues->sum += filterValues->values[filterValues->index];

  filterValues->index++;
  filterValues->index = filterValues->index % MEAN_FILTER_SIZE;


  avg = filterValues->sum / MEAN_FILTER_SIZE;
  return avg ;
}

//Butterworth filter
void lowPassButterworthFilter( float x, butterworthFilter_t * filterResult )
{
  filterResult->v[0] = filterResult->v[1];

  //Fs = 100Hz and Fc = 10Hz
  filterResult->v[1] = (2.452372752527856026e-1 * x) + (0.50952544949442879485 * filterResult->v[0]);

  //Fs = 100Hz and Fc = 4Hz
//  filterResult->v[1] = (1.367287359973195227e-1 * x) + (0.72654252800536101020 * filterResult->v[0]); //Very precise butterworth filter

  filterResult->result = filterResult->v[0] + filterResult->v[1];
}

static bool detectPulse(float sensor_value)
{
  if(sensor_value > PULSE_MAX_THRESHOLD)
  {
    currentPulseDetectorState = PULSE_IDLE;
    prev_sensor_value = 0;
    lastBeat = 0;
    currentBeat = 0;
    values_went_down = 0;
    lastBeatThreshold = 0;
    return false;
  }

  switch(currentPulseDetectorState)
  {
    case PULSE_IDLE:
      if(sensor_value >= PULSE_MIN_THRESHOLD) {
        currentPulseDetectorState = PULSE_TRACE_UP;
        values_went_down = 0;
      }
      break;

    case PULSE_TRACE_UP:

      if(sensor_value > prev_sensor_value)
      {
        currentBeat = millis();
        lastBeatThreshold = sensor_value;
      }
      else
      {

        if(debug == true)
        {
          app_log_info("Peak reached: ");
          app_show_float_value(sensor_value);
          app_log_info(" ");
          app_show_float_value(prev_sensor_value);
        }

        float beatDuration = currentBeat - lastBeat;
        lastBeat = currentBeat;

        float rawBPM = 0.0;
        if(beatDuration > 0)
          rawBPM = 60000.0 / (float)beatDuration;
        if(debug == true)
          app_log_info("%f \r\n",rawBPM);

        //This method sometimes glitches, it's better to go through whole moving average everytime
        //IT's a neat idea to optimize the amount of work for moving avg. but while placing, removing finger it can screw up
        //valuesBPMSum -= valuesBPM[bpmIndex];
        //valuesBPM[bpmIndex] = rawBPM;
        //valuesBPMSum += valuesBPM[bpmIndex];

        valuesBPM[bpmIndex] = rawBPM;
        valuesBPMSum = 0;
        for(int i=0; i<PULSE_BPM_SAMPLE_SIZE; i++)
        {
          valuesBPMSum += valuesBPM[i];
        }

        if(debug == true)
        {
          app_log_info("CurrentMoving Avg: ");
          for(int i=0; i<PULSE_BPM_SAMPLE_SIZE; i++)
          {
            app_show_float_value(valuesBPM[i]);
            app_log_info(" ");
          }

          app_log_info("\r\n ");
        }

        bpmIndex++;
        bpmIndex = bpmIndex % PULSE_BPM_SAMPLE_SIZE;

        if(valuesBPMCount < PULSE_BPM_SAMPLE_SIZE)
          valuesBPMCount++;

        currentBPM = valuesBPMSum / valuesBPMCount;
        if(debug == true)
        {
          app_log_info("average. BPM: ");
          app_show_float_value(currentBPM);
        }


        currentPulseDetectorState = PULSE_TRACE_DOWN;

        return true;
      }
      break;

    case PULSE_TRACE_DOWN:
      if(sensor_value < prev_sensor_value)
      {
        values_went_down++;
      }


      if(sensor_value < PULSE_MIN_THRESHOLD)
      {
        currentPulseDetectorState = PULSE_IDLE;
      }
      break;
  }

  prev_sensor_value = sensor_value;
  return false;
}

static void balanceIntesities( float redLedDC, float IRLedDC )
{
  uint32_t test=millis() - lastREDLedCurrentCheck;
  if( test  >= RED_LED_CURRENT_ADJUSTMENT_MS)
  {
    //app_log_info( redLedDC - IRLedDC );
    if( IRLedDC - redLedDC > MAGIC_ACCEPTABLE_INTENSITY_DIFF && redLEDCurrent < MAX30100_LED_CURRENT_50MA)
    {
      redLEDCurrent++;
      setLEDCurrents( redLEDCurrent, IrLedCurrent );
      if(debug == true)
        app_log_info("RED LED Current +\r\n");
    }
    else if(redLedDC - IRLedDC > MAGIC_ACCEPTABLE_INTENSITY_DIFF && redLEDCurrent > 0)
    {
      redLEDCurrent--;
      setLEDCurrents( redLEDCurrent, IrLedCurrent );
      if(debug == true)
        app_log_info("RED LED Current -\r\n");
    }

    lastREDLedCurrentCheck = millis();
  }
}

void app_show_float_value(float input)
{
  int int_part;

   int float_part;

   int_part=(int)input;

   float_part=(int)(input*1000)-int_part*1000;

   app_log_info("%d.%d\r\n",int_part,float_part);
}
//float millis(clock_t start, clock_t end)
//{
//  float millis;
//  millis = (end - start) / CLOCKS_PER_SEC;
//  return millis;
//}
//void setupRtcc()
//{
//  CMU_ClockSelectSet(cmuClock_RTCC, cmuSelect_LFXO);
//  CMU_ClockEnable(cmuClock_RTCC, true);
//
//  RTCC_Init_TypeDef rtccInit = RTCC_INIT_DEFAULT;
//  RTCC_CCChConf_TypeDef initRTCCCompareChannel = RTCC_CH_INIT_COMPARE_DEFAULT;
//  RTCC_Init(&rtccInit);
//  RTCC_ChannelInit(1, &initRTCCCompareChannel);
//  RTCC_SyncWait();
//  RTCC_Enable(true);
//  RTCC_Start();
//
//    CMU_ClockSelectSet(cmuClock_RTCCCLK, cmuSelect_LFXO);
//    CMU_ClockEnable(cmuClock_RTCC, true);
//
//
//    RTCC_Init_TypeDef RTCInitilization = RTCC_INIT_DEFAULT;
//

//    RTCC_Enable(true);
//}

//uint32_t millis()
//{
//  return RTCC_CounterGet()/1000;
//}
static unsigned long millis(void)
{
  return msTicks;
}
