
package org.usfirst.frc.team1285.robot;

import java.io.IOException;

import org.usfirst.frc.team1285.robot.commands.Pivot;
import org.usfirst.frc.team1285.robot.commands.auto.*;
import org.usfirst.frc.team1285.robot.subsystems.Climber;
import org.usfirst.frc.team1285.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1285.robot.subsystems.GearTool;
import org.usfirst.frc.team1285.robot.subsystems.UDP;
import org.usfirst.frc.team1285.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

	public static PowerDistributionPanel pdp;
	
	Preferences prefs;
	private Command autonomousCommand;
	public static Drivetrain drive;// = new Drivetrain();
	public static GearTool geartool = new GearTool();
	public static Climber climber = new Climber();
	public static Vision vision = new Vision();
	public static OI oi;
	UDP serverUDP;
	NetworkTable table;

	public SendableChooser<Command> autoChooser;
	private int autoNumber;
	
	CameraServer server;
	
	 {server = CameraServer.getInstance();
//		 server.setQuality(25);
		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		
		server.startAutomaticCapture("cam0", 0);
	    }

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

		autoChooser.addDefault("No Auton", new NoAuto());
		autoChooser.addObject("CROSS BASE", new BaseLineCross());
		autoChooser.addObject("GearCenter",new GearCenter());
		autoChooser.addObject("GearCenterBaselineRight", new GearCenterBaselineRight());
		autoChooser.addObject("GearCenterBaselineLeft", new GearCenterBaselineLeft());
		autoChooser.addObject("Turn Drive", new DriveTurn(-90,0.8,5));
		autoChooser.addObject("Drive Distance", new DriveDistance(48,0.8,0,3));
		autoChooser.addObject("GearCenterBaselineWAIT", new GearCenterWaitBaseline());
		autoChooser.addObject("BlueBoilerPeg", new BlueBoilerPegL ());
		autoChooser.addObject("RedBoilerPeg", new RedBoilerPegR ());
		autoChooser.addObject("BlueLoadingPeg", new BlueLoadingPegR ());
		autoChooser.addObject("RedLoadingPeg", new RedLoadingPegL ());
		
		
		autoChooser.addObject("RedLoadingPegNeutral", new RedLoadingPegNeutral ());
		autoChooser.addObject("BlueLoadingPegNeutral", new BlueLoadingPegNeutral ());
		autoChooser.addObject("RedBoilerPegNeutral", new RedBoilerPegNeutral ());
		autoChooser.addObject("BlueBoilerPegNeutral", new BlueBoilerPegNeutral ());
		autoChooser.addObject("TwoGear", new TwoGearCenter ());
		
		
		try{
			serverUDP = new UDP();
			serverUDP.start();
		} catch (IOException e){
			e.printStackTrace();
		}




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


		/*
		 * switch(autoNumber) { case 0: autonomousCommand = (Command) new
		 * NoAuto(); break; case 1: autonomousCommand = (Command) new
		 * BaseLineCross(); break; case 2: autonomousCommand = (Command) new
		 * DriveBlast(100, 5); break; }
		 */


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


		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		geartool.openClamp();
		drive.reset();
		 //geartool.resetArmEncoder();
		geartool.arm(0.2);
		//new Pivot(940, 0.65, 15);
//		new DriveTurn(-90, 0.6, 1.5).start();
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
		geartool.arm(0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();

		// PREFERENCES
		NumberConstants.jukeSpeedScale = prefs.getDouble("DRIVE SPEED SCALE", 1); // default
																				// scalar
																				// for
																				// drive
																				// speed
		NumberConstants.slowSpeedScale = prefs.getDouble("DRIVE SLOW SPEED SCALE", 0.5);
		NumberConstants.armScale = prefs.getDouble("ARM SPEED SCALE", 1); // default
																			// scalar
																			// for
																			// arm
																			// speed
		NumberConstants.climbScale = prefs.getDouble("CLIMB SPEED SCALE", 1.0);
		
//		NumberConstants.pArm = prefs.getDouble("kp", 0.1);
//		NumberConstants.iArm = prefs.getDouble("ki", 0);
//		NumberConstants.dArm = prefs.getDouble("kd", 0);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void updateSmartDashboard() {
		//gyro pitch value 
		SmartDashboard.putNumber("GYRO YAW",drive.getYaw());
		vision.updateUDP();
		// WINCH CURRENT DRAW
//		SmartDashboard.putNumber("LEFT WINCH CURRENT DRAW", climber.getLeftWinchCurrent());
//		SmartDashboard.putNumber("RIGHT WINCH CURRENT DRAW", climber.getRightWinchCurrent());

		// PIVOT CURRENT DRAW
//		SmartDashboard.putNumber("LEFT PIVOT CURRENT DRAW", geartool.getLeftPivotCurrent());
//		SmartDashboard.putNumber("RIGHT PIVOT CURRENT DRAW", geartool.getRightPivotCurrent());

		// DRIVE CURRENT DRAW
//		SmartDashboard.putNumber("LEFT BACK DRIVE CURRENT DRAW", drive.driveCurrentDraw[0]);
//		SmartDashboard.putNumber("LEFT FRONT DRIVE CURRENT DRAW", drive.driveCurrentDraw[1]);
//		SmartDashboard.putNumber("RIGHT BACK DRIVE CURRENT DRAW", drive.driveCurrentDraw[2]);
//		SmartDashboard.putNumber("RIGHT FRONT DRIVE CURRENT DRAW", drive.driveCurrentDraw[3]);
		SmartDashboard.putNumber("WHERE ARE ALL THE LUCKY CHARMS?", geartool.getJackPos());


		// DRIVE GEAR
		SmartDashboard.putBoolean("DRIVE GEAR", drive.getDriveGearState());
		
		//DRIVE MOTOR STATE
		SmartDashboard.putBoolean("DRIVE MOTOR", drive.getDriveMotorState());

		// GET ARM POSITION
		//SmartDashboard.putNumber("ARM DEGREES", geartool.armPosGet());

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
		
		//Intake Preferences
		SmartDashboard.putNumber("pArm", NumberConstants.pArm);
		SmartDashboard.putNumber("iArm", NumberConstants.iArm);
		SmartDashboard.putNumber("dArm", NumberConstants.dArm);
		
		SmartDashboard.putNumber("Arm Power", -Robot.oi.getToolRightY()*0.65);
		
		SmartDashboard.putBoolean("Can I See You Asians", geartool.opticalIsDetected());
		
		SmartDashboard.putNumber("ArmCurrent", geartool.armPower());
		SmartDashboard.putNumber("UDP ANGLE", Vision.getAngle());
	
		
		//System.out.println(drive.getYaw());
		// AUTO
		// SmartDashboard.putNumber(key, value)
	}
}