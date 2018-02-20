package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.Robot;
import org.usfirst.frc.team1285.robot.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The Default command for the intake subsystem. Contains code to control the
 * intake and arms
 * 
 * @author Bryan Kristiono
 * @since 2016-02-01
 */
public class IntakeCommand extends Command {

	/**
	 * Instantiates a new intake command.
	 */
	
	private ToggleBoolean pivotState;
	private ToggleBoolean clampState;
	
	public IntakeCommand() {
		// Makes sure no other intake commands are running at the same time
		requires(Robot.intake);
		pivotState = new ToggleBoolean();
		clampState = new ToggleBoolean();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getToolRightBumper()) {
			Robot.intake.intake();
		}
		else if (Robot.oi.getToolLeftBumper()){
			Robot.intake.outtake();
		}
		else {
			Robot.intake.runIntake(0);
		}
		
		if (!DriverStation.getInstance().isAutonomous()) {
			pivotState.set(Robot.oi.getToolLeftTrigger());
			if(pivotState.get()) {
				Robot.intake.pivotDown();
			}
			else {
				Robot.intake.pivotUp();
			}
			
			if(Robot.oi.getToolRightTrigger()){
				Robot.intake.openClamp();
			}
			else{
				Robot.intake.closeClamp();
			}
		}
	}

	// Set to false to make sure command does not end
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}