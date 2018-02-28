package org.usfirst.frc.team1984.robot;

import edu.wpi.first.wpilibj.*;

public class Lift {
	private Jaguar		lift1,
						lift2;
	public Lift(int one, int two){
		lift1 = new Jaguar(one);
		lift2 = new Jaguar(two);

	}
	//moves the lift
	public void setLift(double magnitude){
		lift1.set(-magnitude);
		lift2.set(magnitude);
	}

}
