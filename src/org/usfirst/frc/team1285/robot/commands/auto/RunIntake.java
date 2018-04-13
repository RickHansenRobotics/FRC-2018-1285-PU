package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {
	private double speed;
	private double timeOut;
	private boolean ends;
	
    public RunIntake(double speed, double timeOut) {
        this.speed = speed;
        this.timeOut = timeOut;
        this.ends = true;
    }
    
    public RunIntake(double speed) {
    	this.speed = speed;
    	this.timeOut = 1;
    	this.ends = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.runIntake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(ends) {
    		Robot.intake.runIntake(0);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(ends) {
    		Robot.intake.runIntake(0);
    	}
    }
}
