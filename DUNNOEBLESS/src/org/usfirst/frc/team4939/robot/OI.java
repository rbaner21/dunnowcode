/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4939.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick DriverController = new Joystick(1);
	public Joystick ShooterController = new Joystick(2);

	private Button BoxIntake = new JoystickButton(DriverController, 1 );
	private Button BoxOuttake = new JoystickButton(DriverController, 4);
	private Button BoxOutTakeSwitch = new JoystickButton(DriverController, 2);
	private Button BoxStopIntake = new JoystickButton(DriverController, 3);
		
	private Button BoxClose = new JoystickButton(DriverController, 6 );
	private Button BoxOpen = new JoystickButton(DriverController, 5);
	private Button PlatformDown = new JoystickButton(ShooterController, 9);
    private Button PlatformUp = new JoystickButton(ShooterController, 10);
	
	
	private Trigger ClimberRaise = new JoystickButton(ShooterController, 6);
	private Trigger ClimberClimb = new JoystickButton(ShooterController, 5);
	
	public OI() {
		this.BoxIntake.whileHeld(new BoxIntake());
		this.BoxOuttake.whenPressed(new BoxOuttake());
		this.BoxOutTakeSwitch.whenPressed(new BoxOutTakeSwitch());
		this.BoxStopIntake.whileHeld(new BoxStopIntake());
		
		this.BoxClose.whileHeld(new BoxClose());
		this.BoxOpen.whileHeld(new BoxOpen());
		
		this.PlatformDown.whileHeld(new PlatformDown());
		this.PlatformUp.whileHeld(new PlatformUp());
		
		this.ClimberRaise.whileActive(new ClimberRaise());
		this.ClimberRaise.whenInactive(new ClimberStop());
		this.ClimberClimb.whileActive(new ClimberClimb());
		this.ClimberClimb.whenInactive(new ClimberStop());
	}
	
	public double left() {
		double leftdrivestick = DriverController.getRawAxis(1);
		if (Math.abs(leftdrivestick) < 0.05)
			return 0.0;
		else
			return leftdrivestick;
	}

	public double right() {
		double rightdrivestick = DriverController.getRawAxis(5);
		if (Math.abs(rightdrivestick) < 0.05)
			return 0.0;
		else
			return rightdrivestick;
	}

	public boolean VisionEngage() {
		return DriverController.getRawButton(1);
	}
}