package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class DashboardManager {
	
	//method for the limit switch to put on the dashboard if on / off
	public static void limit(DigitalInput toteSwitch)
	{
		int switchOn;
		if (toteSwitch.get())
		{
			switchOn = 1;
		}else
		{
			switchOn = 0;
		}
		SmartDashboard.putNumber("Tote?", switchOn);
	}
}
