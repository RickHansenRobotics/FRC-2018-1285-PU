package org.usfirst.frc.team1285.robot.subsystems;

import org.usfirst.frc.team1285.robot.RobotMap;
import org.usfirst.frc.team1285.robot.commands.IntakeCommand;
import org.usfirst.frc.team1285.robot.commands.TankDrive;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private WPI_VictorSPX rightIntakeMotor;
	private WPI_VictorSPX leftIntakeMotor;
	private DoubleSolenoid intakeClamp;
	private DoubleSolenoid intakePivot;
	
	public Intake() {
		
		rightIntakeMotor = new WPI_VictorSPX(RobotMap.RIGHT_INTAKE);
		leftIntakeMotor = new WPI_VictorSPX(RobotMap.LEFT_INTAKE);
		
		rightIntakeMotor.setInverted(RobotMap.rightIntakeInverted);
		leftIntakeMotor.setInverted(RobotMap.leftIntakeInverted);
		
		intakeClamp = new DoubleSolenoid(RobotMap.CLAMP_SOLENOID_A, RobotMap.CLAMP_SOLENOID_B);
		intakePivot = new DoubleSolenoid (RobotMap.PIVOT_SOLENOID_A, RobotMap.PIVOT_SOLENOID_B);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCommand());
    }
    
    public void intake(){
    	rightIntakeMotor.set(1);
    	leftIntakeMotor.set(1);
    }
    
    public void outtake(){
    	rightIntakeMotor.set(-1);
    	leftIntakeMotor.set(-1);
    }
    
    public void openClamp() {
    	intakeClamp.set(DoubleSolenoid.Value.kForward);
    }
    
    public void closeClamp() {
    	intakeClamp.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pivotDown() {
    	intakePivot.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pivotUp() {
    	intakePivot.set(DoubleSolenoid.Value.kReverse);
    }
}

