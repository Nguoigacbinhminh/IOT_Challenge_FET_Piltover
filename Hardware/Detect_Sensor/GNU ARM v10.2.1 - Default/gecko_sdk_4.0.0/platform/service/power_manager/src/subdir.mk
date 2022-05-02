################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager.c \
C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager_debug.c \
C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager_hal_s2.c 

OBJS += \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager.o \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_debug.o \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_hal_s2.o 

C_DEPS += \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager.d \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_debug.d \
./gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_hal_s2.d 


# Each subdirectory must supply rules for building sources it contributes
gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager.o: C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager.c
	@echo 'Building file: $<'
	@echo 'Invoking: GNU ARM C Compiler'
	arm-none-eabi-gcc -g3 -gdwarf-2 -mcpu=cortex-m33 -mthumb -std=c99 '-DBGM220PC22HNA=1' '-DSL_BOARD_NAME="BRD4314A"' '-DSL_BOARD_REV="A02"' '-DSL_COMPONENT_CATALOG_PRESENT=1' '-DMBEDTLS_CONFIG_FILE=<mbedtls_config.h>' '-DMBEDTLS_PSA_CRYPTO_CONFIG_FILE=<psa_crypto_config.h>' '-DSL_RAIL_LIB_MULTIPROTOCOL_SUPPORT=0' '-DSL_RAIL_UTIL_PA_CONFIG_HEADER=<sl_rail_util_pa_config.h>' '-DSLI_RADIOAES_REQUIRES_MASKING=1' -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/Device/SiliconLabs/BGM22/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_assert" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_log" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//protocol/bluetooth/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//hardware/board/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader/api" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/CMSIS/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_cryptoacc_library/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/device_init/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/host/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/hfxo_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/driver/i2cspm/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/iostream/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/config" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/library" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/mpu/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/nvm3/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/ota_dfu" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/power_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_psa_driver/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/common" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ble" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ieee802154" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/zwave" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/chip/efr32/efr32xg2x" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions/efr32xg22" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/rail_util_pti" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/silicon_labs/silabs_core/memory_manager" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/simple_timer" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/toolchain/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/system/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/sleeptimer/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_protocol_crypto/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/udelay/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/ustimer/inc" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\autogen" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\config" -Os -Wall -Wextra -fno-builtin -ffunction-sections -fdata-sections -imacrossl_gcc_preinclude.h -mfpu=fpv5-sp-d16 -mfloat-abi=hard -c -fmessage-length=0 -MMD -MP -MF"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager.d" -MT"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager.o" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_debug.o: C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager_debug.c
	@echo 'Building file: $<'
	@echo 'Invoking: GNU ARM C Compiler'
	arm-none-eabi-gcc -g3 -gdwarf-2 -mcpu=cortex-m33 -mthumb -std=c99 '-DBGM220PC22HNA=1' '-DSL_BOARD_NAME="BRD4314A"' '-DSL_BOARD_REV="A02"' '-DSL_COMPONENT_CATALOG_PRESENT=1' '-DMBEDTLS_CONFIG_FILE=<mbedtls_config.h>' '-DMBEDTLS_PSA_CRYPTO_CONFIG_FILE=<psa_crypto_config.h>' '-DSL_RAIL_LIB_MULTIPROTOCOL_SUPPORT=0' '-DSL_RAIL_UTIL_PA_CONFIG_HEADER=<sl_rail_util_pa_config.h>' '-DSLI_RADIOAES_REQUIRES_MASKING=1' -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/Device/SiliconLabs/BGM22/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_assert" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_log" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//protocol/bluetooth/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//hardware/board/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader/api" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/CMSIS/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_cryptoacc_library/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/device_init/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/host/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/hfxo_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/driver/i2cspm/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/iostream/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/config" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/library" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/mpu/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/nvm3/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/ota_dfu" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/power_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_psa_driver/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/common" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ble" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ieee802154" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/zwave" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/chip/efr32/efr32xg2x" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions/efr32xg22" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/rail_util_pti" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/silicon_labs/silabs_core/memory_manager" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/simple_timer" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/toolchain/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/system/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/sleeptimer/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_protocol_crypto/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/udelay/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/ustimer/inc" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\autogen" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\config" -Os -Wall -Wextra -fno-builtin -ffunction-sections -fdata-sections -imacrossl_gcc_preinclude.h -mfpu=fpv5-sp-d16 -mfloat-abi=hard -c -fmessage-length=0 -MMD -MP -MF"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_debug.d" -MT"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_debug.o" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_hal_s2.o: C:/Users/Ha\ Duyen\ Duc/SimplicityStudio/SDKs/gecko_sdk/platform/service/power_manager/src/sl_power_manager_hal_s2.c
	@echo 'Building file: $<'
	@echo 'Invoking: GNU ARM C Compiler'
	arm-none-eabi-gcc -g3 -gdwarf-2 -mcpu=cortex-m33 -mthumb -std=c99 '-DBGM220PC22HNA=1' '-DSL_BOARD_NAME="BRD4314A"' '-DSL_BOARD_REV="A02"' '-DSL_COMPONENT_CATALOG_PRESENT=1' '-DMBEDTLS_CONFIG_FILE=<mbedtls_config.h>' '-DMBEDTLS_PSA_CRYPTO_CONFIG_FILE=<psa_crypto_config.h>' '-DSL_RAIL_LIB_MULTIPROTOCOL_SUPPORT=0' '-DSL_RAIL_UTIL_PA_CONFIG_HEADER=<sl_rail_util_pa_config.h>' '-DSLI_RADIOAES_REQUIRES_MASKING=1' -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/Device/SiliconLabs/BGM22/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_assert" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/common/util/app_log" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//protocol/bluetooth/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//hardware/board/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/bootloader/api" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/CMSIS/Include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_cryptoacc_library/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/device_init/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/common/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emlib/host/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/hfxo_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/driver/i2cspm/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/iostream/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/config" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/include" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/mbedtls/library" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_mbedtls_support/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/mpu/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/nvm3/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/ota_dfu" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/power_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_psa_driver/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/common" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ble" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/ieee802154" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/protocol/zwave" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/chip/efr32/efr32xg2x" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/pa-conversions/efr32xg22" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/radio/rail_lib/plugin/rail_util_pti" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/se_manager/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/silicon_labs/silabs_core/memory_manager" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//app/bluetooth/common/simple_timer" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/common/toolchain/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/system/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/sleeptimer/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//util/third_party/crypto/sl_component/sl_protocol_crypto/src" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/service/udelay/inc" -I"C:/Users/Ha Duyen Duc/SimplicityStudio/SDKs/gecko_sdk//platform/emdrv/ustimer/inc" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\autogen" -I"C:\Users\Ha Duyen Duc\SimplicityStudio\v5_workspace\FET_Piltover_Detect_Sensor\config" -Os -Wall -Wextra -fno-builtin -ffunction-sections -fdata-sections -imacrossl_gcc_preinclude.h -mfpu=fpv5-sp-d16 -mfloat-abi=hard -c -fmessage-length=0 -MMD -MP -MF"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_hal_s2.d" -MT"gecko_sdk_4.0.0/platform/service/power_manager/src/sl_power_manager_hal_s2.o" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


