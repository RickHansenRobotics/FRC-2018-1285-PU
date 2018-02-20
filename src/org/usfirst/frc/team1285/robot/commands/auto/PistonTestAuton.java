package org.usfirst.frc.team1285.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PistonTestAuton extends CommandGroup {

    public PistonTestAuton() {
        addSequential(new ExtendIntakePivot(false));
        addSequential(new OpenIntakeClamp(false));
        addParallel(new WaitCommand(3));
        addParallel(new ExtendIntakePivot(true));
        addSequential(new OpenIntakeClamp(true));
    }
}
