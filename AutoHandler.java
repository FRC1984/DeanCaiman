package org.usfirst.frc.team1984.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class AutoHandler {	
	public static void runPos1(Drivetrain base, Lift lift){
		//Starts the robot going forward
		double begin = Timer.getFPGATimestamp();
		while (Timer.getFPGATimestamp()-begin < SmartDashboard.getNumber("Forward Time")){
			base.manualUpdate(0, SmartDashboard.getNumber("Forward Speed"), 0);
		}
		//makes the lift go down
		begin = Timer.getFPGATimestamp();
		while (Timer.getFPGATimestamp()-begin < SmartDashboard.getNumber("Down Time")){
			lift.setLift(-SmartDashboard.getNumber("Down Speed"));
		}
		lift.setLift(0);
		//makes the lift go up
		begin = Timer.getFPGATimestamp();
		while (Timer.getFPGATimestamp()-begin < SmartDashboard.getNumber("Up Time")){
			lift.setLift(SmartDashboard.getNumber("Up Speed"));
		}
		lift.setLift(0);
		//Turn the robot 
		begin = Timer.getFPGATimestamp();
		while (Timer.getFPGATimestamp()-begin < SmartDashboard.getNumber("Turn Time")){
			base.manualUpdate(0, 0, SmartDashboard.getNumber("Turn Speed"));
		}
		//Go forward
		begin = Timer.getFPGATimestamp();
		while (Timer.getFPGATimestamp()-begin < SmartDashboard.getNumber("End Time")){
			base.manualUpdate(0, SmartDashboard.getNumber("End Speed"), 0);
		}
		//Stops the robot at the end
		base.manualUpdate(0, 0, 0);
	}
	public static void stay(Drivetrain base){
		base.manualUpdate(0,0,0);
	}
}
