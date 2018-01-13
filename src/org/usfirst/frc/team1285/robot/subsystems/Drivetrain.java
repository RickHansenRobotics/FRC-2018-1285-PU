package org.usfirst.frc.team1285.robot.subsystems;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.RobotMap;
import org.usfirst.frc.team1285.robot.commands.TankDrive;
import org.usfirst.frc.team1285.robot.utilities.PIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Bryan Kristiono
 */
public class Drivetrain extends Subsystem {

	private WPI_TalonSRX leftFollower;
	private WPI_TalonSRX leftMaster;

	private WPI_TalonSRX rightFollower;
	private WPI_TalonSRX rightMaster;

	//ADXRS450_Gyro gyro;
	AHRS gyro;

	public PIDController drivePID;
	public PIDController gyroPID;

	public Drivetrain() {
		
		try {
			/***********************************************************************
			 * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C, TTL UART)
			 * and USB. - See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and
			 * USB. - See
			 * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
		
		// left back
		leftMaster = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_BACK);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMaster.setInverted(RobotMap.leftInverted);
		
		// left front
		leftFollower = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_FRONT);
		leftFollower.set(ControlMode.Follower, RobotMap.LEFT_DRIVE_BACK);

		// right back
		rightMaster = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_BACK);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.setInverted(RobotMap.rightInverted);
		
		// right front
		rightFollower = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_FRONT);
		rightFollower.set(ControlMode.Follower, RobotMap.LEFT_DRIVE_BACK);
		
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);

//		leftMaster.setPID(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		
		
		//gyro = new ADXRS450_Gyro();
//		@SuppressWarnings("unused")
//		double driveCurrentDraw[] = new double[] { leftDriveBack.getOutputCurrent(),
//				//leftDriveFront.getOutputCurrent(), rightDriveBack.getOutputCurrent(),
//				rightDriveFront.getOutputCurrent() };
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}
	
	public void runLeftDrive(double pwmVal) {
		leftMaster.set(pwmVal);
	}

	public void runRightDrive(double pwmVal) {
		rightMaster.set(pwmVal);
	}
	
	public void turnDrive(double setAngle, double speed) {
		turnDrive(setAngle, speed, 1);
	}
	
	public void turnDrive(double setAngle, double speed, double tolerance) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), 1);
		double min = 0.05;
	
		if(Math.abs(setAngle - getYaw()) < tolerance){	
			runLeftDrive(0);
			runRightDrive(0);
		}
		else if(angle > -min && angle < 0){
			runLeftDrive(-min);
			runRightDrive(-min);
		}
		else if(angle < min && angle > 0){
			runLeftDrive(min);
			runRightDrive(min);
		}
		else{	
			runLeftDrive(-angle * speed);
			runRightDrive(-angle * speed);
		}
	}
	
	public void driveSetpoint(double setPoint, double speed, double setAngle) {
		driveSetpoint(setPoint, speed, setAngle, 1);
	}
	
	public void driveSetpoint(double setPoint, double speed, double setAngle, double tolerance) {
		double output = drivePID.calcPID(setPoint, getAverageDistance(), tolerance);
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);

		runLeftDrive ((-output - angle) * speed);
		runRightDrive ((output - angle) * speed);
	}
	
	public boolean drivePIDDone() {
		return drivePID.isDone();
	}

	public void reset() {
		resetEncoders();
		resetGyro();
	}

	// ************************Encoder Functions************************
	
	public boolean isLeftAlive() {
		return leftMaster.isAlive();
	}
	
	public boolean isRightAlive() {
		return rightMaster.isAlive();
	}
	
	public double getLeftEncoderDist() {
		return leftMaster.getSelectedSensorPosition(0) * RobotMap.ROTATIONS_TO_INCHES;
	}

	public double getRightEncoderDist() {
		return rightMaster.getSelectedSensorPosition(0) * RobotMap.ROTATIONS_TO_INCHES;
	}

	public double getAverageDistance() {
		return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}

	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
	}

	/************************ GYRO FUNCTIONS ************************/

	public boolean gyroConnected() {
		return gyro.isConnected();
	}
	
	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}
	
	public double getYaw() {
		return gyro.getAngle();
	}

	public double getPitch() {
		return gyro.getPitch();
	}
	
	public double getRoll() {
		return gyro.getRoll();
	}

	public void resetGyro() {
		gyro.reset();
	}
	
	public double getCompassHeading() {
		return gyro.getCompassHeading();
	}
	
	public void resetPID(){
		drivePID.resetPID();
		gyroPID.resetPID();
	}
}