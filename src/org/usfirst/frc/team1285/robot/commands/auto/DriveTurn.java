package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveTurn extends Command {
	private double speed;
	private double angle;
	private double timeOut;
	//private double tolerance;

	public DriveTurn(double angle, double speed, double timeOut) {
		this(angle, speed, timeOut, 1);
	}

    public DriveTurn(double angle, double speed, double timeOut, double tolerance) {
    	this.speed = speed;
    	this.angle = angle;
    	this.timeOut = timeOut;
    	//this.tolerance = tolerance;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.turnDrive(angle, speed);
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