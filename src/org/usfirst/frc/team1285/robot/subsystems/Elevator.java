package org.usfirst.frc.team1285.robot.subsystems;

import org.usfirst.frc.team1285.robot.NumberConstants;
import org.usfirst.frc.team1285.robot.RobotMap;
import org.usfirst.frc.team1285.robot.commands.ElevateCommand;
import org.usfirst.frc.team1285.robot.utilities.PIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	//RightElevator//
	private WPI_VictorSPX rightElevator;
	private WPI_TalonSRX leftElevator;
	private DigitalInput leftSwitch;
	private DigitalInput rightSwitch;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public PIDController elevPID;
	
	public Elevator() {
		rightElevator = new WPI_VictorSPX(RobotMap.RIGHT_ELEVATOR);
	    leftElevator = new WPI_TalonSRX (RobotMap.LEFT_ELEVATOR);
	    leftElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	    rightElevator.set(ControlMode.Follower, RobotMap.LEFT_ELEVATOR);
	    
	    
	    elevPID = new PIDController(NumberConstants.pElev, NumberConstants.iElev, NumberConstants.dElev);
	    leftSwitch = new DigitalInput(RobotMap.LEFT_BUMPER_SWITCH);
	    rightSwitch = new DigitalInput(RobotMap.RIGHT_BUMPER_SWITCH);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevateCommand());
    }
    
    public void runElevator(double pwmVal) {
    	rightElevator.set(pwmVal);
    	leftElevator.set(pwmVal);
    }
    
    public double getDistance(){
		return leftElevator.getSelectedSensorPosition(0) * RobotMap.ELEV_ROTATIONS_TO_INCHES;
	}
    
    public void setPosition(double setPoint, double speed, double tolerance) {
    	double output = elevPID.calcPID(setPoint, getDistance(), tolerance);
		runElevator ((-output) * speed);
    }
    
    public boolean isGrounded(){
    	return leftSwitch.get() || rightSwitch.get();
    }
	
	public void resetEncoder (){
		rightElevator.setSelectedSensorPosition(0, 0, 0);
		leftElevator.setSelectedSensorPosition(0, 0, 0);
	}
	
	
    
    
}

