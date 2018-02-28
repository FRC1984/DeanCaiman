package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

public class Drivetrain {
	
	private Talon 		frontRight,
						frontLeft,
						rearLeft,
						rearRight;
	private RobotDrive 	base;
	private Joystick	stick;
	
	public Drivetrain(int fl, int rl, int fr, int rr, Joystick stick){
		frontLeft  = new Talon(fl);
		rearLeft   = new Talon(rl);
		frontRight = new Talon(fr);
		rearRight  = new Talon(rr);
		base = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		base.setInvertedMotor(MotorType.kFrontLeft, true);
		
		this.stick = stick;
	}
	
	public void update(){
		//base.mecanumDrive_Polar(stick.getMagnitude(), stick.getDirectionDegrees(), stick.getTwist());
		base.mecanumDrive_Cartesian(0, stick.getY(), stick.getZ(), 0);//Test this new way to drive without x axis
	}
	
	public void manualUpdate(double magnitude, double degrees, double twist){
		//base.mecanumDrive_Polar(magnitude, degrees, twist);
		base.mecanumDrive_Cartesian(magnitude,degrees,twist,0);
	}
	public void driveUpdate(){
		//base.arcadeDrive(stick);//axis are backwards
		//frontLeft.set(stick.getX());
		//frontRight.set(stick.getX());
		//rearLeft.set((stick.getY()+stick.getX())/2);
		//rearRight.set((stick.getY()-stick.getX())/2);
		base.mecanumDrive_Cartesian(0, stick.getY(), stick.getX(), 0);
		
	}
}
