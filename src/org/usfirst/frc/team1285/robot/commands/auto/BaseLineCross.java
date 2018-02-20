package org.usfirst.frc.team1285.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 *
 */
public class BaseLineCross extends CommandGroup{

	public BaseLineCross() {
    	addSequential(new DriveDistance(120, 1, 0, 5));
    }
}
