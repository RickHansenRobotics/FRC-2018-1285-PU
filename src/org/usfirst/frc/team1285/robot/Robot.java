package org.usfirst.frc.team1285.robot;

import java.io.IOException;

import org.usfirst.frc.team1285.robot.commands.auto.*;
import org.usfirst.frc.team1285.robot.subsystems.*;

import edu.wpi.first.wpilibj.DriverStation;
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
	public static Elevator elev;
	public static OI oi;
	
	private int autoNum;
//	UDP serverUDP;
//	NetworkTable table;

	public SendableChooser<Integer> autoChooser;
	private int autoNumber;

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drive = new Drivetrain();
		intake = new Intake();
		elev = new Elevator();
		
		prefs = Preferences.getInstance();

		autoChooser = new SendableChooser<Integer>();
		
		autoChooser.addObject("NoAuto", 0);
		autoChooser.addObject("AutoLine", 1);
		autoChooser.addDefault("Center Switch", 2);
		autoChooser.addObject("Left Scale", 3);
		autoChooser.addObject("Center Right Switch", 4);
		autoChooser.addObject("Left Scale Switch", 5);
		autoChooser.addObject("Left Pure", 6);
		autoChooser.addObject("Test Turn", 7);
		SmartDashboard.putData("AUTON", autoChooser);
		
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
		autoNum = autoChooser.getSelected();
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
		drive.reset();
		drive.brakeMode();
		//elev.resetEncoder();
		intake.closeClamp();
		intake.pivotUp();
		
		String data = "";
		while(data.length()<3) {
			data = DriverStation.getInstance().getGameSpecificMessage();
		}
		SmartDashboard.putString("game data", data);
		autoNum = autoChooser.getSelected();
		switch(autoNum) {
		case 0:
			autonomousCommand = new NoAuto();
			break;
		case 1:
			autonomousCommand = new BaseLineCross();
			break;
		case 2:
			autonomousCommand = new CenterSwitchAuton(data.charAt(0));
			break;
		case 3:
			autonomousCommand = new LeftScaleAuton(data.charAt(1));
			break;
		case 4:
			autonomousCommand = new CenterSwitchRightAuton(data.charAt(0));
			break;
		case 5:
			autonomousCommand = new LeftScaleSwitchAuton(data.charAt(0), data.charAt(1));
			break;
		case 6:
			autonomousCommand = new LeftPureAuton(data.charAt(0), data.charAt(1));
			break;
		case 7:
			autonomousCommand = new DriveTurn(90, 0.8, 3);
			break;
		default:
			autonomousCommand = new TimedAuto(0.8, 2.5);
		}
//		autonomousCommand = new TurnCommand(90, 0.8, 3);
		
		drive.updatePIDs();
		
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
		drive.coastMode();
		elev.updatePIDs();
		
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
		SmartDashboard.putNumber("Left", oi.getDriveLeftY());
		SmartDashboard.putNumber("Right", oi.getDriveRightY());
		SmartDashboard.putBoolean("Is Grounded", elev.isGrounded());
		SmartDashboard.putNumber("Yaw", drive.getYaw());
		
		 //DRIVE ENCODERS
		SmartDashboard.putNumber("LEFT DRIVE ENCODER", drive.getLeftEncoderDist());
		SmartDashboard.putNumber("RIGHT DRIVE ENCODER", drive.getRightEncoderDist());
		SmartDashboard.putNumber("LEFT DRIVE ENCODER RAW", drive.getLeftEncoderRaw());
		SmartDashboard.putNumber("RIGHT DRIVE ENCODER RAW", drive.getRightEncoderRaw());
		SmartDashboard.putNumber("AVERAGE DRIVE ENCODER", drive.getAverageDistance());
		SmartDashboard.putNumber("ELEV RAW ENCODER", elev.getRawEncoder());
		SmartDashboard.putNumber("ELEV ENCODER", elev.getDistance());
		SmartDashboard.putNumber("ELEV Velocity", elev.getVelocity());
		
		//Drive Preferences
		SmartDashboard.putNumber("pDrive", NumberConstants.pDrive);
		SmartDashboard.putNumber("iDrive", NumberConstants.iDrive);
		SmartDashboard.putNumber("dDrive", NumberConstants.dDrive);
		
		//Gyro PID
		SmartDashboard.putNumber("pGyro", NumberConstants.pGyro);
		SmartDashboard.putNumber("iGyro", NumberConstants.iGyro);
		SmartDashboard.putNumber("dGyro", NumberConstants.dGyro);
		
		//Elevator
		//SmartDashboard.putBoolean("Grounded", elev.isGrounded());
		
		NumberConstants.pElev = prefs.getDouble("pElev", 0.0);
		NumberConstants.iElev = prefs.getDouble("iElev", 0.0);
		NumberConstants.dElev = prefs.getDouble("dElev", 0.0);

		NumberConstants.pDrive = prefs.getDouble("pDrive", 0.0);
		NumberConstants.iDrive = prefs.getDouble("iDrive", 0.0);
		NumberConstants.dDrive = prefs.getDouble("dDrive", 0.0);
		
		NumberConstants.pGyro = prefs.getDouble("pGyro", 0.0);
		NumberConstants.iGyro = prefs.getDouble("iGyro", 0.0);
		NumberConstants.dGyro = prefs.getDouble("dGyro", 0.0);
		
		SmartDashboard.putNumber("autoNum", autoNum);
		SmartDashboard.putData("Auton Chooser", autoChooser);

	}
}