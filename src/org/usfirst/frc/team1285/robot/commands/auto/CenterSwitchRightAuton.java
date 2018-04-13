package org.usfirst.frc.team1285.robot.commands.auto;

import org.usfirst.frc.team1285.robot.NumberConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 *
 */
public class CenterSwitchRightAuton extends CommandGroup{

	public CenterSwitchRightAuton(char direction) {
    	addParallel(new RunElevator(NumberConstants.SWITCH, 0.8, 5));
		addSequential(new DriveDistance(100, 1, 0, 5));
		if (direction == 'R') {
		   	addSequential(new OpenIntakeClamp(true));
	    	addSequential(new ExtendIntakePivot(false));
		}
    }
}
