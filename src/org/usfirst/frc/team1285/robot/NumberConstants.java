package org.usfirst.frc.team1285.robot;

public class NumberConstants {
	
	//::::::::::::::::::::::::::::::::::::::::::PID VALUES:::::::::::::::::::::::::::::::::::::::::::::::::::::\\
	
	public static final double pDrive = 0.03;
	public static final double iDrive = 0.0;
	public static final double dDrive = 0.0;

	public static final double pGyro = 0.02;
	public static final double iGyro = 0.0;
	public static final double dGyro = 0.08;
	
	//up +, down -
	public static double pArm = 0.021;
	public static double iArm = 0.0;
	public static double dArm = 0.0;

	//Drive Speed Scalar
	public static double jukeSpeedScale = 0.85;
	public static double slowSpeedScale = 0.5;
	public static double speedScale = 1;
	
	//Intake Speed Scalar
	public static double armScale = 0.25;
	
	//Climb Speed Scalar
	public static double climbScale = 1;
	
	//Arms
	public static double topPivot = 11.5;
	public static double keepUpPow = 0.2;
}

