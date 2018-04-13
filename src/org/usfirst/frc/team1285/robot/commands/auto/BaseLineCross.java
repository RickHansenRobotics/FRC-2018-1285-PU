package org.usfirst.frc.team1285.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 *
 */
public class BaseLineCross extends CommandGroup{

	public BaseLineCross() {
    	addSequential(new DriveCommand(150, 0.8, 0, 2.5));

    	//addSequential(new TimedAuto(0.8, 2.5));
    }
}
