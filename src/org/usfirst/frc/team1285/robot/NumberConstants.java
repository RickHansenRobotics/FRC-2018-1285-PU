package org.usfirst.frc.team1285.robot;

public class NumberConstants {
	
	//::::::::::::::::::::::::::::::::::::::::::PID VALUES:::::::::::::::::::::::::::::::::::::::::::::::::::::\\
	
	public static  double pDrive = 0.0013;
	public static  double iDrive = 0.001;
	public static  double dDrive = 0.0;

	public static  double pGyro = 0.001;
	public static  double iGyro = 0.0;
	public static  double dGyro = 0.004;
	
	public static double pElev = 0.02;
	public static double iElev = 0.0;
	public static double dElev = 0.08;
	public static double fElev = 0.10;
	
	

	//Drive Speed Scalar
	public static double jukeSpeedScale = 0.85;
	public static double slowSpeedScale = 0.5;
	public static double speedScale = 1;
	
	//Elevator Constants
	public static final double maxElevSpeed = 0;
	
	public static double elev_kforward = -0.0813;
	
	public static double HANG 			= 62;
	public static double SCALE_TOP 		= 80;
	public static double SWITCH 		= 30;
	public static double GROUND 		= 0;
}

