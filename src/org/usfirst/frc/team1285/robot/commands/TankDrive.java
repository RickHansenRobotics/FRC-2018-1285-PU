package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// TODO: Auto-generated Javadoc
/**
 *	This class is used to set a default command for the Drivetrain subsystem. This command allows the driver to
 *	control the robot using tank drive. 
 *
 * @author Bryan Kristiono
 * @since 2016-01-10
 */
public class TankDrive extends Command {

	/**
	 * Instantiates a new tank drive Command.
	 */
	public TankDrive() {
		// Makes sure that no other commands use the drivetrain at the same time
		requires(Robot.drive);
	}

	protected void initialize() {
	}

	/**
	 * This method will run as long as isFinished() returns true
	 * In this method values from the joystick are sent to the corresponding drives to make the robot move.
	 */
	protected void execute() {		
		// Runs drive at jotstick input, if right bumper is pressed drive speed is halved
		Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY());
		Robot.drive.runRightDrive(Robot.oi.getDriveRightY());
	}
	
	// isFinished() always returns false, as we don't want TankDrive to stop by default
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {   
	}
	
}