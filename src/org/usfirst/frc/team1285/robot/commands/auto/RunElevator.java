package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunElevator extends Command {
	private double setPoint;
	private double speed;
	private double timeOut;
	
    public RunElevator(double setPoint, double speed, double timeOut) {
        this.setPoint = setPoint;
        this.speed = speed;
        this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elev.setPosition(setPoint, speed, 5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elev.runElevator(NumberConstants.elev_kforward);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elev.runElevator(NumberConstants.elev_kforward);
    }
}
