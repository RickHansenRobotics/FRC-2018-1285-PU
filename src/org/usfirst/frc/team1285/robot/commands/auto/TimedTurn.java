package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class TimedTurn extends Command {
	private char direction;
	private double speed;
	private double timeOut;

    public TimedTurn(char direction, double speed, double timeOut) {
    	this.direction = direction;
    	this.speed = speed;
    	this.timeOut = timeOut;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction=='L') {
	    	Robot.drive.runLeftDrive(speed);
	    	//taken out at DCMP-- Robot.drive.runRightDrive(-speed);
	    	Robot.drive.runRightDrive(speed);
    	}
    	else if (direction=='R') {
    		Robot.drive.runLeftDrive(speed);
	    	//taken out at DCMP-- Robot.drive.runRightDrive(-speed);
    		Robot.drive.runRightDrive(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drive.drivePIDDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    	Robot.drive.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
    //	Robot.geartool.intake(1);
    	
    }
}