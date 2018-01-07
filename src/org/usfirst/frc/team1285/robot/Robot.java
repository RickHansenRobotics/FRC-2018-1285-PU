
package org.usfirst.frc.team1285.robot;

import java.io.IOException;

import org.usfirst.frc.team1285.robot.commands.auto.*;
import org.usfirst.frc.team1285.robot.subsystems.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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
	public static Drivetrain drive = new Drivetrain();
	public static Intake intake = new Intake();
	public static OI oi;
//	UDP serverUDP;
//	NetworkTable table;

	public SendableChooser<Command> autoChooser;
	private int autoNumber;
	
//	CameraServer server;
//	
//	 {server = CameraServer.getInstance();
////		 server.setQuality(25);
//		// the camera name (ex "cam0") can be found through the roborio web
//		// interface
//		
//		server.startAutomaticCapture("cam0", 0);
//	    }

	// Command autonomousCommand;
	// private int autoNumber;
	// private Object autoNumber;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drive = new Drivetrain();

		prefs = Preferences.getInstance();

		autoChooser = new SendableChooser<Command>();

//		autoChooser.addDefault("No Auton", new NoAuto());
//		autoChooser.addObject("CROSS BASE", new BaseLineCross());
//		autoChooser.addObject("GearCenter",new GearCenter());
//		autoChooser.addObject("GearCenterBaselineRight", new GearCenterBaselineRight());
//		autoChooser.addObject("GearCenterBaselineLeft", new GearCenterBaselineLeft());
//		autoChooser.addObject("Turn Drive", new DriveTurn(-90,0.8,5));
//		autoChooser.addObject("Drive Distance", new DriveDistance(48,0.8,0,3));
//		autoChooser.addObject("GearCenterBaselineWAIT", new GearCenterWaitBaseline());
//		autoChooser.addObject("BlueBoilerPeg", new BlueBoilerPegL ());
//		autoChooser.addObject("RedBoilerPeg", new RedBoilerPegR ());
//		autoChooser.addObject("BlueLoadingPeg", new BlueLoadingPegR ());
//		autoChooser.addObject("RedLoadingPeg", new RedLoadingPegL ());
		
		
//		autoChooser.addObject("RedLoadingPegNeutral", new RedLoadingPegNeutral ());
//		autoChooser.addObject("BlueLoadingPegNeutral", new BlueLoadingPegNeutral ());
//		autoChooser.addObject("RedBoilerPegNeutral", new RedBoilerPegNeutral ());
//		autoChooser.addObject("BlueBoilerPegNeutral", new BlueBoilerPegNeutral ());
//		autoChooser.addObject("TwoGear", new TwoGearCenter ());
		
		
//		try{
//			serverUDP = new UDP();
//			serverUDP.start();
//		} catch (IOException e){
//			e.printStackTrace();
//		}




		SmartDashboard.putData("AUTO", autoChooser);
		
		//CameraServer.getInstance().startAutomaticCapture();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
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
		 //geartool.resetArmEncoder();
		//autonomousCommand = new GearCenter();
		autonomousCommand = autoChooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	public void teleopInit() {
		// Robot.drive.shiftLow();
		drive.reset();
		 //geartool.resetArmEncoder();
		if (autonomousCommand != null)
			((Command) autonomousCommand).cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void updateSmartDashboard() {
		//gyro Yaw value 
		SmartDashboard.putNumber("GYRO YAW",drive.getYaw());
		
		// DRIVE ENCODERS
		SmartDashboard.putNumber("LEFT DRIVE ENCODER", drive.getLeftEncoderDist());
		SmartDashboard.putNumber("RIGHT DRIVE ENCODER", drive.getRightEncoderDist());
		SmartDashboard.putNumber("AVERAGE DRIVE ENCODER", drive.getAverageDistance());
		
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