package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command {

	// Variables to hold parameter information
	private double angle;
	private double speed;
	@SuppressWarnings("unused")
	private double timeOut;

	/**
	 * Instantiates a new turn command.
	 *
	 * @param angle
	 *            Angle the robot will turn to (-180 <-> 180)
	 * @param speed
	 *            The speed the robot will turn at (0.0 - 1.0)
	 * @param timeOut
	 *            The time out in seconds
	 */
	public TurnCommand(double angle, double speed, double timeOut) {
		this.angle = angle;
		this.speed = speed;
		this.timeOut = timeOut;
		requires(Robot.dt);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(timeOut);
		//Robot.dt.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.dt.turnDrive(angle, speed, 1);
	}

	// Command is finished when timed out
	protected boolean isFinished() {
		return isTimedOut();
		
	}

	// Called once after isFinished returns true, once done will stop robot from
	// moving.
	protected void end() {
		Robot.dt.runleftsidedrive(0);
		Robot.dt.runrightsidedrive(0);
//		Robot.dt.drivePID.resetPID();
//		Robot.dt.gyroPID.resetPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, once done will stop robot from moving.
	protected void interrupted() {
		Robot.dt.runleftsidedrive(0);
		Robot.dt.runrightsidedrive(0);
		Robot.dt.drivePID.resetPID();
		Robot.dt.gyroPID.resetPID();
	}
}

