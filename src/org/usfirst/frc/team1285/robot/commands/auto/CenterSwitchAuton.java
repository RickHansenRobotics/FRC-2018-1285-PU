package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAuton extends CommandGroup {

    public CenterSwitchAuton(char direction) {
    	if (direction == 'L') {
    		addParallel(new DrivePath(new Point(0,0), new Point(-35,32), new Point(-50,22), new Point(-50,130), 3, 0.6));
    	}
    	else {
    		addParallel(new DrivePath(new Point(0,0), new Point(35,32), new Point(40,22), new Point(40,130), 3, 0.6));	
    	}
    	addSequential(new RunElevator(NumberConstants.SWITCH, 1, 2));
    	addSequential(new OpenIntakeClamp(true));
    	addSequential(new ExtendIntakePivot(false));
    }
}
