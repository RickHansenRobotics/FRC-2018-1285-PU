package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.utilities.Point;
import org.usfirst.frc.team1285.robot.utilities.QuinticBezierDrivePath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleSwitchAuton extends CommandGroup {

    public LeftScaleSwitchAuton(char first, char second) {
    	if(second == 'L') {
    		addSequential(new DrivePath(new Point(0,0),new Point(0,50),new Point(-15,150),new Point(-15,300), 3, 1));
    		addParallel(new DriveTurn(70, 1, 1));
    		addSequential(new RunElevator(NumberConstants.SCALE_TOP,1, 3));
        	addSequential(new DriveDistance(30, 0.6, 70, 2));
        	addSequential(new OpenIntakeClamp(true));
    	    addSequential(new ExtendIntakePivot(false));
    	    
    	    addSequential(new DriveDistance(-30, 0.6, 90, 2));
    	    addParallel(new RunElevator(NumberConstants.GROUND,0.8, 3));
    	    
    	    addSequential(new DriveTurn(160, 0.8, 3));
    	    addParallel(new RunIntake(1));
    	    addSequential(new DriveDistance(90, 0.8, 160, 3));
    	    addSequential(new OpenIntakeClamp(false));
    	    
    	} else {
    		addSequential(new QuinticBezierDrivePath(new Point(0,0),new Point(-25,100),new Point(-55,170),new Point(50,100),new Point(-90,200),new Point(300,180), 20, 0.05, 5, 0.8));
    		addParallel(new RunElevator(NumberConstants.SWITCH,1, 1));
    		addSequential(new DriveTurn(-10,1,3));
//    		addParallel(new RunElevator(NumberConstants.SCALE_TOP,1, 3));
//    		addSequential(new DriveDistance(70, 0.5, -15, 3));
//        	addSequential(new OpenIntakeClamp(true));
//    	    addSequential(new ExtendIntakePivot(false));
//    	    
//    	    addSequential(new DriveDistance(-40, 0.6, -15, 2));
//    	    addSequential(new RunElevator(NumberConstants.GROUND,0.8, 3));
    	}
    	
	    
    	
    }
}
