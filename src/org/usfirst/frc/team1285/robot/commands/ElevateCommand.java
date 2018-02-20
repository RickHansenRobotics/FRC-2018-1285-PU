package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;
import org.usfirst.frc.team1285.robot.commands.auto.RunElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevateCommand extends Command {
	private RunElevator ground;
	private RunElevator reachSwitch;
	private RunElevator highScale;
	private RunElevator hang;
	
	
    public ElevateCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elev);
        this.ground = new RunElevator(NumberConstants.GROUND, 0.8, 5);
        this.reachSwitch = new RunElevator(NumberConstants.SWITCH, 0.8, 5);
        this.highScale = new RunElevator(NumberConstants.SCALE_TOP, 0.8, 5);
        this.hang = new RunElevator(NumberConstants.HANG, 0.8, 5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(!(Robot.elev.isGrounded() && Robot.oi.getToolRightY() 0)) {
    	
    	if(Math.abs(Robot.oi.getToolRightY()) > 0) {
    		this.cancelCommand();
    		Robot.elev.runElevator(NumberConstants.elev_kforward+Robot.oi.getToolRightY());
    	}
    	else if(Robot.oi.getToolLeftY() > 0) {
    		this.cancelCommand();
    		Robot.elev.runElevator(Robot.oi.getToolLeftY());
    	}
    		
    	if(Robot.oi.getToolAButton()) {
    		this.cancelCommand();
    		this.ground.start();
    	}
    	else if (Robot.oi.getToolXButton()){
    		this.cancelCommand();
    		this.reachSwitch.start();
    	}
    	else if (Robot.oi.getToolYButton()) {
    		this.cancelCommand();
    		this.hang.start();
    	}
    	else if (Robot.oi.getToolBButton()){
    		this.cancelCommand();
    		this.highScale.start();
    	}
    	if (Robot.elev.isGrounded()){
    		Robot.elev.resetEncoder();
    	}
    }

    protected void cancelCommand() {
    	this.ground.cancel();
    	this.reachSwitch.cancel();
    	this.highScale.cancel();
    	this.hang.cancel();
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
