package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendIntakePivot extends Command {
	private boolean extend;
	
    public ExtendIntakePivot(boolean extend) {
//    	requires(Robot.intake);
    	this.extend = extend;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (extend){
    		Robot.intake.pivotUp();
    	}
    	else{
    		Robot.intake.pivotDown();
    	}
    }

        protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
