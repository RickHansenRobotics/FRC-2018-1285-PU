package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchTimedAuton extends CommandGroup {

    public CenterSwitchTimedAuton(char direction) {
    	addSequential(new DriveDistance(50, 0.8, 0, 3));
    	addSequential(new TimedTurn(direction, 0.8, 3));
    	addSequential(new DriveDistance(50, 0.8, 0, 3));
    	if (direction == 'L') {
    		addSequential(new TimedTurn('R', 0.8, 3));
    	}
    	else {
    		addSequential(new TimedTurn('L', 0.8, 3));	
    	}
    	addSequential(new DriveDistance(50, 0.8, 0, 3));
    	addSequential(new RunElevator(NumberConstants.SWITCH, 1, 2));
    	addSequential(new OpenIntakeClamp(true));
    	addSequential(new ExtendIntakePivot(false));
    }
}
