//Code made by Aidan for the 2015 lift robot after the competition

/*
 * Test the new arcade drive
 * Fix the arcade drive / test new code
 * Add camera code when it is on the robot
 * Change the drive to be in drive train class
 * 
 */

package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;


public class Robot extends IterativeRobot {
	
	private Joystick			drive,
								liftControl;
	private Drivetrain			base;
	private Lift				lift;
	private SendableChooser 	driveChooser,
								autonomousChooser;
	
	private DigitalInput        toteSwitch;
	private Encoder				toteLiftEnc;
	
	//Declares all the variables when the robot starts up
    public void robotInit() {
    	
    	drive 				= new Joystick(0);
    	liftControl 		= new Joystick(1);
    	base 				= new Drivetrain(1,0,3,2,drive);
    	lift				= new Lift(4,5);
    	
    	toteSwitch		 	= new DigitalInput(2);
    	toteLiftEnc			= new Encoder(0,1,false,Encoder.EncodingType.k4X);
    	//Driving chooser
    	driveChooser 		= new SendableChooser();
    	driveChooser.addDefault("Mecanum", Integer.valueOf(0));
    	driveChooser.addObject("Arcade", Integer.valueOf(1));
    	SmartDashboard.putData("Pick Drive", driveChooser);
    	
    	//Autonomous mode chooser
    	autonomousChooser   = new SendableChooser();
    	autonomousChooser.addDefault("Don't Move", Integer.valueOf(0));
    	autonomousChooser.addObject("Move", Integer.valueOf(1));
    	SmartDashboard.putData("Autonomous Chooser",autonomousChooser);
    	
    	//Data for autonomous          Time                  					Speed          
    	SmartDashboard.putNumber("Forward Time", 0);SmartDashboard.putNumber("Forward Speed", 0);
    	SmartDashboard.putNumber("Down Time", 2);	SmartDashboard.putNumber("Down Speed", 0.8);
    	SmartDashboard.putNumber("Up Time", 1);		SmartDashboard.putNumber("Up Speed", 0.3);
    	SmartDashboard.putNumber("Turn Time", 0);	SmartDashboard.putNumber("Turn Speed", 0);
    	SmartDashboard.putNumber("End Time", 0);	SmartDashboard.putNumber("End Speed", 0);
    	
    }
    
    //Driver operated control
    public void teleopPeriodic() {
    	int drivemode = ((Integer) driveChooser.getSelected()).intValue();
    	switch (drivemode){
    		case 0:
    			base.update();
    			break;
    		case 1:
    			base.driveUpdate();
    	}
    	lift.setLift(liftControl.getY()); 
    	DashboardManager.limit(toteSwitch);
    	//Encoder code :) 
    	double liftEncoder = toteLiftEnc.get();
    	SmartDashboard.putNumber("Encoder", liftEncoder);
    	//Auto moving lift
    	if(liftControl.getRawButton(2))
    	{
    		double begin = Timer.getFPGATimestamp();
    		while (Timer.getFPGATimestamp()-begin < .5){
    			lift.setLift(-1);
    		}
    		lift.setLift(0);
    		
    	}
    	if(liftControl.getRawButton(3))
    	{
    		double begin = Timer.getFPGATimestamp();
    		while (Timer.getFPGATimestamp()-begin < .5){
    			lift.setLift(1);
    		}
    		lift.setLift(0);
    		
    	}
    }
	
    //Autonomous Mode
    public void autonomousInit() {
    	int automode = ((Integer) autonomousChooser.getSelected()).intValue();
    	
    	switch (automode){
    		case 0:
    			AutoHandler.stay(base);
    			break;
    		case 1:
    			AutoHandler.runPos1(base, lift);
    			break;
    	}
    	DriverStation.reportError("Exiting Autonomous.", false);
    }
    
    //Unused other stuff
    public void disabledPeriodic() {

	}
    public void autonomousPeriodic() {

    }
    public void teleopInit() {

    }
    public void disabledInit(){

    }
    public void testPeriodic() {
    }
}