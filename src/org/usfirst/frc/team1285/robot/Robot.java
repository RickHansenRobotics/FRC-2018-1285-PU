package org.usfirst.frc.team1285.robot;

import java.io.IOException;

import org.usfirst.frc.team1285.robot.commands.auto.*;
import org.usfirst.frc.team1285.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * 
 * directory.
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	private Command autonomousCommand;
	public static Drivetrain drive;
	public static Intake intake;
	public static OI oi;
//	UDP serverUDP;
//	NetworkTable table;

	public SendableChooser<Command> autoChooser;
	private int autoNumber;

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drive = new Drivetrain();
		intake = new Intake();
		
		prefs = Preferences.getInstance();

		autoChooser = new SendableChooser<Command>();

		SmartDashboard.putData("AUTO", autoChooser);
	}

	public void disabledInit() {
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		autonomousCommand = autoChooser.getSelected();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		drive.reset();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
//		LiveWindow.run();
	}
	
	public void robotPeriodic() {
		updateSmartDashboard();
	}

	public void updateSmartDashboard() {
		//gyro Yaw value 
		SmartDashboard.putNumber("GYRO YAW",drive.getYaw());
		
		// DRIVE ENCODERS
//		SmartDashboard.putNumber("LEFT DRIVE ENCODER", drive.getLeftEncoderDist());
//		SmartDashboard.putNumber("RIGHT DRIVE ENCODER", drive.getRightEncoderDist());
//		SmartDashboard.putNumber("AVERAGE DRIVE ENCODER", drive.getAverageDistance());
		 
		//Drive Preferences
		SmartDashboard.putNumber("pDrive", NumberConstants.pDrive);
		SmartDashboard.putNumber("iDrive", NumberConstants.iDrive);
		SmartDashboard.putNumber("dDrive", NumberConstants.dDrive);
		
		//Gyro PID
		SmartDashboard.putNumber("pGyro", NumberConstants.pGyro);
		SmartDashboard.putNumber("iGyro", NumberConstants.iGyro);
		SmartDashboard.putNumber("dGyro", NumberConstants.dGyro);
	}
}