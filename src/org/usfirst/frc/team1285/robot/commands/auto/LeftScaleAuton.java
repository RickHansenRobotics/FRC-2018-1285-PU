package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.utilities.Point;
import org.usfirst.frc.team1285.robot.utilities.QuinticBezierDrivePath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAuton extends CommandGroup {

    public LeftScaleAuton(char direction) {
    	if(direction == 'L') {
    		addSequential(new DrivePath(new Point(0,0),new Point(0,50),new Point(-15,150),new Point(-15,300), 3, 1));
    		addParallel(new DriveTurn(70, 1, 1));
    		addSequential(new RunElevator(NumberConstants.SCALE_TOP,1, 3));
        	addSequential(new DriveDistance(30, 0.6, 70, 2));
        	addSequential(new OpenIntakeClamp(true));
    	    addSequential(new ExtendIntakePivot(false));
    	    
    	    addSequential(new DriveDistance(-30, 0.6, 90, 2));
    	    addSequential(new RunElevator(NumberConstants.GROUND,0.8, 3));
//    		addParallel(new QuinticBezierDrivePath(new Point(0,0),new Point(0,87),new Point(0,141),new Point(0,174),new Point(-16,242),new Point(12,200), 20, 0.05, 5, 0.8));
    	} else {
    		//durham
    		//addSequential(new QuinticBezierDrivePath(new Point(0,0),new Point(-25,100),new Point(-55,170),new Point(50,100),new Point(-90,200),new Point(300,180), 20, 0.05, 5, 0.8));
    		//ryerson
    		addSequential(new QuinticBezierDrivePath(new Point(0,0),new Point(-25,70),new Point(-55,130),new Point(50,60),new Point(-90,160),new Point(310,140), 20, 0.05, 5, 0.8));
    		addParallel(new RunElevator(NumberConstants.SWITCH,1, 1));
    		addSequential(new DriveTurn(-10,1,3));
    		addParallel(new RunElevator(NumberConstants.SCALE_TOP,1, 3));
    		addSequential(new DriveDistance(70, 0.5, -15, 3));
        	addSequential(new OpenIntakeClamp(true));
    	    addSequential(new ExtendIntakePivot(false));
    	    
    	    addSequential(new DriveDistance(-40, 0.6, -15, 2));
    	    addSequential(new RunElevator(NumberConstants.GROUND,0.8, 3));
    	}
    	
	    
    	
    }
}
