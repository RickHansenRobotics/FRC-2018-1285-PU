package org.usfirst.frc.team1285.robot;

public class NumberConstants {
	
	//::::::::::::::::::::::::::::::::::::::::::PID VALUES:::::::::::::::::::::::::::::::::::::::::::::::::::::\\
	
	public static final double pDrive = 0.03;
	public static final double iDrive = 0.0;
	public static final double dDrive = 0.0;

	public static final double pGyro = 0.02;
	public static final double iGyro = 0.0;
	public static final double dGyro = 0.08;
	
	public static final double pElev = 0.02;
	public static final double iElev = 0.0;
	public static final double dElev = 0.08;

	//Drive Speed Scalar
	public static double jukeSpeedScale = 0.85;
	public static double slowSpeedScale = 0.5;
	public static double speedScale = 1;
	
	//Elevator Constants
	public static double elev_kforward = -0.0813;
	
	public static double HANG 			= 120;
	public static double SCALE_TOP 		= 100;
	public static double SCALE_MIDDLE 	= 90;
	public static double SWITCH 		= 40;
	public static double GROUND 		= 0;
}

