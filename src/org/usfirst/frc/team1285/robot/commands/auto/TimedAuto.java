package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedAuto extends Command {

	double speed;
	double time;
	
    public TimedAuto(double speed, double time) {
      this.speed = speed;
      this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.runLeftDrive(-speed);
    	Robot.drive.runRightDrive(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }
}
