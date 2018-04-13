package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.utilities.Point;
import org.usfirst.frc.team1285.robot.utilities.QuinticBezierDrivePath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPureAuton extends CommandGroup {

    public LeftPureAuton(char first, char second) {
    	if(second == 'L') {
    		addSequential(new DrivePath(new Point(0,0),new Point(0,50),new Point(-15,150),new Point(-15,320), 3, 1));
    		addParallel(new DriveTurn(65, 1, 1));
    		addSequential(new RunElevator(NumberConstants.SCALE_TOP,1, 3));
        	addSequential(new DriveDistance(30, 0.6, 70, 2));
        	addSequential(new OpenIntakeClamp(true));
    	    addSequential(new ExtendIntakePivot(false));
    	    
    	    addSequential(new DriveDistance(-30, 0.6, 90, 2));
    	    addSequential(new RunElevator(NumberConstants.GROUND,0.8, 3));
    	}
    	else if(first == 'L') {
    		addSequential(new DriveDistance(150, 0.8, 0, 2));
    		addParallel(new DriveTurn(90, 1, 1));
    		addSequential(new RunElevator(NumberConstants.SWITCH,1, 3));
        	addSequential(new DriveDistance(40, 0.6, 90, 2));
        	addSequential(new OpenIntakeClamp(true));
    	    addSequential(new ExtendIntakePivot(false));
    	    
    	    addSequential(new DriveDistance(-30, 0.6, 90, 2));
    	    addSequential(new RunElevator(NumberConstants.GROUND,0.8, 3));
    	}
    	else {
    		addSequential(new DrivePath(new Point(0,0),new Point(0,50),new Point(-15,100),new Point(-15,150), 3, 1));
    	}
    	
	    
    	
    }
}
