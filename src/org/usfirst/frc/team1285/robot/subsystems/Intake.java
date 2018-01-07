package org.usfirst.frc.team1285.robot.subsystems;

import org.usfirst.frc.team1285.robot.commands.IntakeCommand;
import org.usfirst.frc.team1285.robot.commands.TankDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//	CANTalon rightDriveFront;
//	CANTalon rightDriveBack;
	
	public Intake() {
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    }
}

