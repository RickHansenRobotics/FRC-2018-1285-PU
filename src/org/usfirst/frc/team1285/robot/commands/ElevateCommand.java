package org.usfirst.frc.team1285.robot.commands;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.Robot;
import org.usfirst.frc.team1285.robot.commands.auto.RunElevator;
import org.usfirst.frc.team1285.robot.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevateCommand extends Command {
	private RunElevator ground;
	private RunElevator reachSwitch;
	private RunElevator highScale;
	private RunElevator hang;
	
	//private ToggleBoolean wratchetState;
	
	
    public ElevateCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elev);
        this.ground = new RunElevator(NumberConstants.GROUND, 0.8, 5);
        this.reachSwitch = new RunElevator(NumberConstants.SWITCH, 0.8, 5);
        this.highScale = new RunElevator(NumberConstants.SCALE_TOP, 0.8, 5);
        this.hang = new RunElevator(NumberConstants.HANG, 0.8, 5);
        
        //wratchetState = new ToggleBoolean();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!DriverStation.getInstance().isAutonomous()) {
    		if(Math.abs(Robot.oi.getToolRightY()) == 0 || 
    				(Robot.elev.isGrounded() && Robot.oi.getToolRightY() > 0)) {
	    		this.cancelCommand();
	    		Robot.elev.runElevator(NumberConstants.elev_kforward);
	    	}
    		else if (Robot.elev.getDistance() < 15 && Robot.oi.getToolRightY() > 0) {
	    		this.cancelCommand();
	    		Robot.elev.runElevator(Robot.oi.getToolRightY()*0.2);
	    	}
	    	else if(Math.abs(Robot.oi.getToolRightY()) > 0) {
	    		this.cancelCommand();
	    		Robot.elev.runElevator(Robot.oi.getToolRightY());
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
