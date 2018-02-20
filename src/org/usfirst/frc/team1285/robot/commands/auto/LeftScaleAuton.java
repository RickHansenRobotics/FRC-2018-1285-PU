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
    		addSequential(new DrivePath(new Point(0,0),new Point(0,50),new Point(-15,150),new Point(-15,310), 5, 1));
    		addParallel(new DriveTurn(90, 1, 1));
//    		addParallel(new QuinticBezierDrivePath(new Point(0,0),new Point(0,87),new Point(0,141),new Point(0,174),new Point(-16,242),new Point(12,200), 20, 0.05, 5, 0.8));
    	} else {
    		addSequential(new QuinticBezierDrivePath(new Point(0,0),new Point(-38,134),new Point(41,132),new Point(-67,242),new Point(97,201),new Point(272,210), 20, 0.05, 5, 0.8));
    	}
    	addSequential(new RunElevator(NumberConstants.SCALE_TOP,0.8, 3));
    	addSequential(new DriveDistance(20, 0.6, 90, 2));
	    addSequential(new OpenIntakeClamp(true));
	    addSequential(new ExtendIntakePivot(false));
    }
}
