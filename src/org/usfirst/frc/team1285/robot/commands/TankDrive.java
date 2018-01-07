package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;
import org.usfirst.frc.team1285.robot.commands.auto.DriveDistance;
import org.usfirst.frc.team1285.robot.commands.auto.DriveTurn;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Neil Balaskandarajah
 */
	

public class TankDrive extends Command {
	private DriveDistance drivePoint;
	private DriveTurn trackTurn;
	
	public TankDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		drivePoint = new DriveDistance(60, 1, 0, 5);
		
		trackTurn = new DriveTurn (Robot.drive.getYaw() + Robot.vision.anglePhone, 0.5, 5) ;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
//		if (Robot.oi.getDriveAButton()){
//			drivePoint.start();	
//		}
			
		//}
		
		if (Robot.oi.getDriveLeftTrigger()) { //Shift into low gear
			Robot.drive.shiftLow();
		} else if (Robot.oi.getDriveRightTrigger()) { //Shift into high gear
			Robot.drive.shiftHigh();
		} else if (Robot.oi.getDriveRightBumper()) { //half speed
			Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY()*NumberConstants.slowSpeedScale);
			Robot.drive.runRightDrive(-Robot.oi.getDriveRightY()*NumberConstants.slowSpeedScale);
		} else if (Robot.oi.getDriveLeftBumper()) { //juke speed
			Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY()*NumberConstants.jukeSpeedScale);
			Robot.drive.runRightDrive(-Robot.oi.getDriveRightY()*NumberConstants.jukeSpeedScale);
		} else if(Robot.oi.getDriveStartButton()){ //reset encoders
			Robot.drive.resetEncoders();
		} else if(Robot.oi.getDriveXButton()) { //brake drive
			Robot.drive.BrakeDrive();
		} else if(Robot.oi.getDriveYButton()){ //coast drive
			Robot.drive.CoastDrive();
		} else { //full speed
			Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(-Robot.oi.getDriveRightY());
		}
		
		if(Robot.oi.getDriveStartButton()){
			Robot.drive.driveTurn(Robot.drive.getYaw() + Robot.vision.anglePhone, 0.5);
			//trackTurn.start();
		}
}

	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
	}
}