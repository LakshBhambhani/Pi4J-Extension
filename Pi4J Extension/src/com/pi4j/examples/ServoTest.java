package com.pi4j.examples;

import java.io.IOException;
import java.math.BigDecimal;

import com.pi4j.component.servo.ServoBase;
import com.pi4j.component.servo.impl.GenericServo;
import com.pi4j.component.servo.impl.PCA9685GpioServoDriver;
import com.pi4j.component.servo.impl.PCA9685GpioServoProvider;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class ServoTest {
	
	private static PCA9685GpioProvider gpioProvider;
//    private static PCA9685GpioServoProvider gpioServoProvider;
    private static PCA9685GpioServoDriver gpioServoDriver;
//
//    private static Servo[] servos;
    private static GenericServo servo;

	public static void main(String[] args) {
		try {
			new ServoTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ServoTest() throws Exception{
		gpioProvider = createProvider();

        // Define outputs in use for this example
        provisionPwmOutputs(gpioProvider);
        
//        gpioProvider.setPwm(PCA9685Pin.PWM_00, 1024);
//        Thread.sleep(1000);
        gpioServoDriver = new PCA9685GpioServoDriver(gpioProvider, PCA9685Pin.PWM_00);
        servo = new GenericServo(gpioServoDriver, "servo");

        while(true) {
//        	System.out.println(2);
//            gpioProvider.setPwm(PCA9685Pin.PWM_00, 450);
//            Thread.sleep(1000);
//            System.out.println(5);
//            gpioProvider.setPwm(PCA9685Pin.PWM_00, 1074);
//            Thread.sleep(1000);
//            System.out.println(7);
//            gpioProvider.setPwm(PCA9685Pin.PWM_00, 2048);
//            Thread.sleep(1000);
            
            System.out.println(2);
            servo.setPosition(0);            
            Thread.sleep(100);
            System.out.println(5);
            servo.setPosition(90);            
            Thread.sleep(100);
            System.out.println(7);
            servo.setPosition(180);            
            Thread.sleep(100);
            servo.setReversed(!servo.isReverse());
            System.out.println("reversed");
        }

	}
	
	private PCA9685GpioProvider createProvider() throws UnsupportedBusNumberException, IOException {
        BigDecimal frequency = PCA9685GpioProvider.ANALOG_SERVO_FREQUENCY;
        BigDecimal frequencyCorrectionFactor = new BigDecimal("1.0578");

        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        return new PCA9685GpioProvider(bus, 0x40, frequency, frequencyCorrectionFactor);
    }

    private GpioPinPwmOutput[] provisionPwmOutputs(final PCA9685GpioProvider gpioProvider) {
        GpioController gpio = GpioFactory.getInstance();
        GpioPinPwmOutput myOutputs[] = {
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_00, "Servo 00"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_01, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_02, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_03, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_04, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_05, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_06, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_07, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_08, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_09, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_10, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_11, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_12, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_13, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_14, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_15, "not used")};
        return myOutputs;
    }


}
