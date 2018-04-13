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
	
	public static final int RIGHT_DRIVE_FRONT                               = 8; 
	public static final int RIGHT_DRIVE_MIDDLE 								= 6;
	public static final int RIGHT_DRIVE_BACK                                = 3;
	
	public static final int LEFT_DRIVE_FRONT                                = 1;
	public static final int LEFT_DRIVE_MIDDLE                               = 5;
	public static final int LEFT_DRIVE_BACK                                 = 2;

	//**************************************************************************
	//******************************* INTAKE ***********************************
	//**************************************************************************        
		
	public static final int RIGHT_INTAKE                                    = 11;
	public static final int LEFT_INTAKE                                     = 12;
	
	//**************************************************************************
	//******************************* Elevator ***********************************
	//**************************************************************************        
		
	public static final int RIGHT_ELEVATOR                                   = 7; 
	public static final int RIGHT_ELEVATOR_FOLLOWER                          = 9; 

	public static final int LEFT_ELEVATOR                                    = 4;
	public static final int LEFT_ELEVATOR_MASTER                             = 10;
	
	public static final int LEFT_BUMPER_SWITCH								 = 9;
	public static final int RIGHT_BUMPER_SWITCH								 = 8;
	
	//**************************************************************************
	//*************************** PNEUMATICS ***********************************
	//**************************************************************************
	//Clamps
	public static final int CLAMP_SOLENOID_A								= 4;
	public static final int CLAMP_SOLENOID_B							 	= 6;
	//Pivot
	public static final int PIVOT_SOLENOID_A							 	= 0;
	public static final int PIVOT_SOLENOID_B							 	= 2;
	
//	public static final int WRATCHET_SOLENOID_A								= 5;
//	public static final int WRATCHET_SOLENOID_B								= 3;
//	^^							Recheck to make sure ^^
	//**************************************************************************
    //********************* DRIVE ENCODER CONSTANTS ****************************
	//**************************************************************************
	public static final int driveWheelRadius = 3;//wheel radius in inches
	public static final double driveGearRatio = 5.0/6.0; //ratio between wheel and encoder
	public static final double DRIVE_ROTATIONS_TO_INCHES 	= (2*Math.PI*driveWheelRadius*driveGearRatio);
	public static final double DRIVE_RAW_TO_INCHES = 1/350.0;
	
	public static final boolean leftInverted = true;
	public static final boolean rightInverted = false;
	
	//**************************************************************************
    //********************* INTAKE ENCODER CONSTANTS ***************************
	//**************************************************************************
	
	public static final boolean leftIntakeInverted = true;
	public static final boolean rightIntakeInverted = false;
	
	//**************************************************************************
    //********************* Elevator ENCODER CONSTANTS ***************************
	//**************************************************************************
	
	public static final double elevGearRatio = 3.0/8.0; //ratio between wheel and encoder
	public static final double elevRadius = 1.79/4;
	public static final double ticks = 1024;
	
	public static final double ELEV_ROTATIONS_TO_INCHES 	= (2*Math.PI*elevRadius*elevGearRatio);
	public static final double ELEV_TICKS_TO_INCHES = ELEV_ROTATIONS_TO_INCHES / ticks;	
	
}