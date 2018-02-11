package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevateCommand extends Command {

    public ElevateCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elev);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(!(Robot.elev.isGrounded() && Robot.oi.getToolRightY() < 0)) {
    		Robot.elev.runElevator(Robot.oi.getToolRightY());
//    	}
//    	if(Robot.oi.getToolAButton()) {
//    		Robot.elev.setPosition(NumberConstants.GROUND, 1, 1);
//    	}
//    	else if (Robot.oi.getToolBButton()){
//    		Robot.elev.setPosition(NumberConstants.SWITCH, 1, 1);
//    	}
//    	else if (Robot.oi.getToolYButton()) {
//    		Robot.elev.setPosition(NumberConstants.SCALE_MIDDLE, 1, 1);
//    	}
//    	else if (Robot.oi.getToolXButton()){
//    		Robot.elev.setPosition(NumberConstants.SCALE_TOP, 1, 1);
//    	}
//    	else if (Robot.oi.getToolStartButton()){
//    		Robot.elev.setPosition(NumberConstants.HANG, 1, 1);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
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
