package org.usfirst.frc.team1285.robot;
/**
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//**************************************************************************
	//*****************************DRIVE MOTORS*********************************
	//**************************************************************************        
	
	public static final int RIGHT_DRIVE_FRONT                               = 2; 
	public static final int RIGHT_DRIVE_BACK                                = 3;
	
	public static final int LEFT_DRIVE_FRONT                                = 1;
	public static final int LEFT_DRIVE_BACK                                 = 4;

	//**************************************************************************
	//******************************* INTAKE ***********************************
	//**************************************************************************        
		
//	public static final int RIGHT_ARM                                       = 5; 
//	public static final int LEFT_ARM                                        = 6;
//		
//	public static final int RIGHT_INTAKE                                    = 7;
//	public static final int LEFT_INTAKE                                     = 8;
//	
//	public static final int JACK_POT	= 0;
	
	//**************************************************************************
	//************************** GEARTOOL SENSORS ******************************
	//**************************************************************************
	
//	public static final int ARM_PIVOT_ENCODER_A								= 3;
//	public static final int ARM_PIVOT_ENCODER_B								= 4;


	//**************************************************************************
	//*************************** PNEUMATICS ***********************************
	//**************************************************************************
//	//clamps
//	public static final int INTAKE_SOLENOID_A								= 1;
//	public static final int INTAKE_SOLENOID_B							 	= 0;
//	//drive gearboxes
//	public static final int DRIVE_SOLENOID_A							 	= 2;
//	public static final int DRIVE_SOLENOID_B							 	= 3;
//	//right fork
//	public static final int CLIMBER_SOLENOID_A							 	= 4;
//	public static final int CLIMBER_SOLENOID_B							 	= 5;
//	//left fork
//	public static final int CLIMBER_SOLENOID_C								= 6;
//	public static final int CLIMBER_SOLENOID_D 								= 7;
	
	//**************************************************************************
    //********************* DRIVE ENCODER CONSTANTS ****************************
	//**************************************************************************
	public static final int driveWheelRadius = 2;//wheel radius in inches
	public static final double driveGearRatio = 1/1; //ratio between wheel and encoder
	public static final double ROTATIONS_TO_INCHES 	= (2*Math.PI*driveWheelRadius*driveGearRatio);
	
	//**************************************************************************
    //********************* INTAKE ENCODER CONSTANTS ***************************
	//**************************************************************************
	
	public static final boolean ArmEncoderReverse = false;
	public static final int pulsesPerRev = 128; //pulses per revolution of arm
	public static final double armGearRatio = 1.4; //ratio between wheel and encoder
	//public static final double ROTATIONS_TO_DEGREES = 1; //degrees per tick
	
	
}