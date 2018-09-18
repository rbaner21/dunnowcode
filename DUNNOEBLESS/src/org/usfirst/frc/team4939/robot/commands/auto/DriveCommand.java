package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	// Variables used to store parameter information
	//private double distance;
	//private double const_multiplier;
//	private double angle;
	private double timeOut;
	//private double epsilon;
	
	private double leftPower;
	private double rightPower;
	private double time;

	/**
	 * Instantiates a new drive command.
	 *
	 * @param setPoint
	 *            The set point in inches
	 * @param speed
	 *            The speed the robot will move at (0.0 - 1.0)
	 * @param angle
	 *            The angle the robot will travel at in degrees
	 * @param timeOut
	 *            The time out in seconds
	 */
	/*
	public DriveCommand(double setPoint, double const_multiplier, double angle, double timeOut) {
		this(setPoint, const_multiplier, angle, timeOut, 1);
	}

	/**
	 * Instantiates a new drive command.
	 *
	 * @param setPoint
	 *            The set point in inches
	 * @param speed
	 *            The speed the robot will move at (0.0 - 1.0)
	 * @param angle
	 *            The angle the robot will travel at in degrees
	 * @param timeOut
	 *            The time out in seconds
	 * @param epsilon
	 *            How close robot should be to target to consider reached
	 */
	/*
	public DriveCommand(double setPoint, double const_multiplier, double angle, double timeOut, double epsilon) {
		this.distance = setPoint;
		this.const_multiplier = const_multiplier;
//		this.angle = angle;
		this.timeOut = timeOut;
		this.epsilon = epsilon;
		requires(Robot.dt);
	}
	*/
	
	public DriveCommand(double leftPower, double rightPower, double time){
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		this.time = time;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		//Robot.dt.resetEncoders();
		setTimeout(timeOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.dt.driveStraight(distance, epsilon, const_multiplier);
		Robot.dt.driveStraightWithoutSensors(leftPower, rightPower, time);
	}

	// Command will finish when it is timed out
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true, reset all PID controllers and
	// stop drive.
	protected void end() {
		Robot.dt.runleftsidedrive(0);
		Robot.dt.runrightsidedrive(0);
	//	Robot.dt.drivePID.resetPID();
	//	Robot.dt.gyroPID.resetPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, reset all PID controllers and stop drive.
	protected void interrupted() {
		Robot.dt.runleftsidedrive(0);
		Robot.dt.runrightsidedrive(0);
	//	Robot.dt.drivePID.resetPID();
		//Robot.dt.gyroPID.resetPID();
	}
}

