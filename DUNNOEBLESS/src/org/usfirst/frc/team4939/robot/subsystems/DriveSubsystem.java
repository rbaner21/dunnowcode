
package org.usfirst.frc.team4939.robot.subsystems;

import org.usfirst.frc.team4939.robot.RobotMap;
import org.usfirst.frc.team4939.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import org.usfirst.frc.team4939.robot.ElectricalConstants;
import org.usfirst.frc.team4939.robot.NumberConstants;
import org.usfirst.frc.team4939.robot.PIDController;
import com.kauailabs.navx.AHRSProtocol;
import com.kauailabs.navx.AHRSProtocol.AHRSPosUpdate;
import com.kauailabs.navx.AHRSProtocol.BoardID;
import com.kauailabs.navx.IMUProtocol.YPRUpdate;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	//public static WPI_TalonSRX leftsidedrivetop = new WPI_TalonSRX(RobotMap.leftDriveTop);
	//public static WPI_TalonSRX rightsidedrivetop = new WPI_TalonSRX(RobotMap.rightDriveTop);
public static WPI_TalonSRX leftsidedrivefront = new WPI_TalonSRX(RobotMap.leftDriveFront);
	//public static WPI_TalonSRX leftsidedriveback = new WPI_TalonSRX(RobotMap.leftDriveBack);
	public static WPI_TalonSRX rightsidedrivefront = new WPI_TalonSRX(RobotMap.rightDriveFront);
	//public static WPI_TalonSRX rightsidedriveback = new WPI_TalonSRX(RobotMap.rightDriveBack);
	/** Encoders on the drive */
	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;

	/** The drive PID controller. */
	public PIDController drivePID;

	/** The gyro PID controller. */
	public PIDController gyroPID;

	Compressor c;
	// public WPI_TalonSRX lights = new WPI_TalonSRX(RobotMap.lights);

	// double kp = 0.008;
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;

	/* This tuning parameter indicates how close to "on target" the */
	/* PID Controller will attempt to get. */

	static final double kToleranceDegrees = 2.0f;

	private AHRS ahrs;
	double rotateToAngleRate;

	public DriveSubsystem() {
		try {
			/***********************************************************************
			 * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C, TTL UART)
			 * and USB. - See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and
			 * USB. - See
			 * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/

			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}

		c = new Compressor(0);
		c.setClosedLoopControl(true);
		// Put methods for controlling this subsystem
		// here. Call these from Commands.
		// Initialize Encoders
		leftDriveEncoder = new Encoder(ElectricalConstants.LEFT_DRIVE_ENCODER_A,
				ElectricalConstants.LEFT_DRIVE_ENCODER_B, ElectricalConstants.leftDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);
		leftDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);
		
		rightDriveEncoder = new Encoder(ElectricalConstants.RIGHT_DRIVE_ENCODER_A,
				ElectricalConstants.RIGHT_DRIVE_ENCODER_B, ElectricalConstants.rightDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);

		rightDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);

		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
	}

	/**
	 * Sets the command TankDrive as the default command for this subsystem.
	 */
	public void runleftsidedrive(double leftdrivestick) {
		leftsidedrivefront.set(leftdrivestick);
		//leftsidedriveback.set(leftdrivestick);
		//leftsidedrivetop.set(leftdrivestick);
	}

	public void runrightsidedrive(double rightdrivestick) {
		//rightsidedriveback.set(rightdrivestick);
		rightsidedrivefront.set(rightdrivestick);
		//rightsidedrivetop.set(rightdrivestick);
	}

	public double angle() {
		return ahrs.getAngle();
	}

	public double rate() {
		return ahrs.getRate();
	}

	// public double getAverageDistance() {
	// return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	// }

	/**
	 * Using both PID controllers (drive & gyro), the drivetrain will move to
	 * target at given speed and angle
	 *
	 * @param setPoint
	 *            The set point in inches
	 * @param speed
	 *            The speed (0.0 to 1.0)
	 * @param setAngle
	 *            The set angle in degrees
	 * @param epsilon
	 *            How close robot should be to target to consider reached
	 */
	// only need setpoint and tolerance to drive straight a distance

	// public void driveStraightEncoders(double setPoint, double epsilon, double
	// const_multiplier) {
	// double output = drivePID.calcPIDDrive(setPoint, getAverageDistance(),
	// epsilon);
	//
	// double angle = gyroPID.calcPID(setAngle, getGyroYaw(), epsilon); // don't
	// // need this to drive straight
	// //
	// runleftsidedrive((output - (angle() * kP)) * const_multiplier);
	// runrightsidedrive((output + (angle() * kP)) * const_multiplier);
	// }
	//

	public double getAverageDistance() {
		return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}

	public void driveStraight(double setpoint, double speed, double setAngle, double epsilon) {
		double output = drivePID.calcPID(setpoint, getAverageDistance(), epsilon);
		double angle = gyroPID.calcPID(setAngle, getGyroYaw(), epsilon);

		runleftsidedrive((output + angle) * speed);
		runrightsidedrive((-output + angle) * speed);
	}

	/**
	 * Used to move robot without a drive PID controller at a given speed, while
	 * at the angle given.
	 *
	 * @param setAngle
	 *            The set angle in degrees
	 * @param speed
	 *            The speed (-1.0 - 1.0)
	 */

	public void setAngle(double setAngle, double multiplier) {
		double outputcontroller = gyroPID.calcPID(setAngle, getGyroYaw(), 1);

		runleftsidedrive((outputcontroller) * multiplier);
		runrightsidedrive((outputcontroller * -1) * multiplier);
	}

	public void driveSetpoint(double setpoint, double speed, double angle, double epsilon) {
		double outputcontroller = gyroPID.calcPID(angle, getGyroYaw(), 1);

		runleftsidedrive((speed) + outputcontroller);
		runrightsidedrive((-speed) - outputcontroller);
	}

	/**
	 * Using a PID controller, turns the robot to given angle with the given
	 * speed.
	 *
	 * @param setAngle
	 *            The set angle in degrees
	 * @param speed
	 *            The speed (0.0 - 1.0)
	 * @param epsilon
	 *            How close robot should be to target to consider reached
	 */

	public void driveStraightWithoutSensors(double leftPower, double rightPower, double time) {
		{
			runleftsidedrive(leftPower);
			runrightsidedrive(-(rightPower));
		}
		Timer.delay(time);
	}

	public void stop() {
		this.runleftsidedrive(0);
		this.runrightsidedrive(0);
	}

	public void pause(double time) {
		{
			this.runleftsidedrive(0);
			this.runrightsidedrive(0);
		}
		Timer.delay(time);
	}

	public void reset() {
		// resetEncoders();
		resetGyroYaw();
	}

	//public static double getLeftCurrent() {
		//return leftsidedrivefront.getOutputCurrent() + leftsidedriveback.getOutputCurrent();
	//}

	//public static double getRightCurrent() {
		//return rightsidedrivefront.getOutputCurrent() + rightsidedriveback.getOutputCurrent();
	//}

	/**
	 * This function returns the distance traveled from the left encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */

	public double getLeftEncoderDist() {
		return leftDriveEncoder.getDistance();
	}

	/**
	 * This function returns the distance traveled from the right encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */

	public double getRightEncoderDist() {
		return rightDriveEncoder.getDistance();
	}

	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	public AHRS getAhrs() {
		return ahrs;
	}

	public double getGyroYaw() {
		return ahrs.getAngle();
	}

	public void resetGyroYaw() {
		ahrs.reset();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new TankDrive());
	}

	public void updatePID() {
		gyroPID.changePIDGains(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
		drivePID.changePIDGains(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
	}

	public void turnDrive(double setPoint, double speed, int tolerance) {
		double angle = gyroPID.calcPID(setPoint, getGyroYaw(), tolerance);

		runleftsidedrive(-speed + angle);
		runrightsidedrive(speed + angle);

	}

}
