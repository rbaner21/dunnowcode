/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4939.robot;

import org.usfirst.frc.team4939.robot.commands.auto.CenterSwitchEncoder;
import org.usfirst.frc.team4939.robot.commands.auto.ForwardLeft;
import org.usfirst.frc.team4939.robot.commands.auto.ForwardRight;
import org.usfirst.frc.team4939.robot.commands.auto.PIDLeftSideSwitch;
import org.usfirst.frc.team4939.robot.commands.auto.PIDRightSideSwitch;
import org.usfirst.frc.team4939.robot.commands.auto.ReachBaselineLeft;
import org.usfirst.frc.team4939.robot.commands.auto.ReachBaselineRight;
//import org.usfirst.frc.team4939.robot.commands.auto.ReachBaselineLeft;
//import org.usfirst.frc.team4939.robot.commands.auto.ReachBaselineRight;
//import org.usfirst.frc.team4939.robot.commands.auto.TimedAutoLeftTwoCube;
import org.usfirst.frc.team4939.robot.commands.auto.TimedCenterAuto;
import org.usfirst.frc.team4939.robot.commands.auto.PIDRightSideSwitch;
import org.usfirst.frc.team4939.robot.commands.auto.TimedLeftAuto;
import org.usfirst.frc.team4939.robot.commands.auto.TimedRighttAuto;
import org.usfirst.frc.team4939.robot.commands.auto.TurnCommandTest;
import org.usfirst.frc.team4939.robot.commands.auto.yolocenter;
import org.usfirst.frc.team4939.robot.commands.auto.yoloooool;
import org.usfirst.frc.team4939.robot.commands.auto.yorooooor;
//import org.usfirst.frc.team4939.robot.commands.auto.TimedLeftAuto;
//import org.usfirst.frc.team4939.robot.commands.auto.TimedRighttAuto;
//import org.usfirst.frc.team4939.robot.commands.auto.TurnCommand;
import org.usfirst.frc.team4939.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team4939.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team4939.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4939.robot.subsystems.PlatformSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static final DriveSubsystem dt = new DriveSubsystem();
	public static final PlatformSubsystem platform = new PlatformSubsystem();
	public static final IntakeSubsystem intake = new IntakeSubsystem();
	public static final ClimbSubsystem climber = new ClimbSubsystem();
//	public static final DigitalInput limitSwitch = new DigitalInput(1);
	public static OI m_oi;
	
	
	//
	public static Compressor compressor;
	public String gameData;
	public char direction;
	
	Preferences pref;

	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		pref= Preferences.getInstance();
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(160, 120);
		
		m_chooser.addDefault("LeftSwitchAuto", 0);
		m_chooser.addObject("RightSwitchAuto", 1);
		m_chooser.addObject("CenterAuto", 2);
		m_chooser.addObject("TurnCommandTest", 3);
		m_chooser.addObject("Reach Baseline Left", 4);
		m_chooser.addObject("Reach Baseline Right", 5);
		m_chooser.addObject("Left Side Staright Forward Switch", 6);
		m_chooser.addObject("Right Side Straight Forward Switch", 7);
		m_chooser.addObject("LeftSidePIDAuto", 8);
		m_chooser.addObject("RightSidePIDAuto", 9);
		m_chooser.addObject("Yoloooool", 10);
		m_chooser.addObject("Yorooooor", 11);
		m_chooser.addObject("Yolocenter", 12);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("Auto mode(s)", m_chooser);
		updateSmartDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */

	int automode = 0;
	@Override
	public void autonomousInit() {
		//while(gameData.length() < 3) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		//}
		direction = gameData.charAt(0);
		//direction = 'L';

	//	Robot.dt.resetGyroYaw();
		//automode = 6;
automode = (int) m_chooser.getSelected();
		switch(automode){
			case 0:
				m_autonomousCommand = new PIDLeftSideSwitch(direction);
				break;
			case 1:
//				m_autonomousCommand = new TimedRighttAuto(direction);
				m_autonomousCommand = new PIDRightSideSwitch(direction);

				break;
			case 2:
				m_autonomousCommand = new TimedCenterAuto(direction);
				break;
			case 3:
				m_autonomousCommand = new TurnCommandTest(direction);
				break;
			case 4:
				m_autonomousCommand = new ReachBaselineLeft(direction);
				break;
			case 5:
				m_autonomousCommand = new ReachBaselineRight(direction);
				break;
			case 6:
				m_autonomousCommand = new ForwardLeft(direction);
				break;
			case 7:
				m_autonomousCommand = new ForwardRight(direction);
				break;
			case 8:
				m_autonomousCommand = new PIDLeftSideSwitch(direction);
				break;
			case 9:
				m_autonomousCommand = new PIDRightSideSwitch(direction);
				break;
			case 10:
				m_autonomousCommand = new yoloooool(direction);
			case 11:
				m_autonomousCommand = new yorooooor(direction);
			case 12:
				m_autonomousCommand = new yolocenter(direction);
	}
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {

		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.putBoolean("Limit switch", limitSwitch.get());
		updateSmartDashboard();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		updateSmartDashboard();
	}
	
	@Override
	public void robotPeriodic() {
		updateSmartDashboard();
	}
	
	public void updateSmartDashboard(){
		automode = m_chooser.getSelected();
		SmartDashboard.putNumber("angle", Robot.dt.getGyroYaw());
		SmartDashboard.putNumber("autoNum", automode);
		NumberConstants.pGyro = pref.getDouble("pGyro", 0.0);
		NumberConstants.iGyro = pref.getDouble("iGyro", 0.0);
		NumberConstants.dGyro = pref.getDouble("dGyro", 0.0);
		NumberConstants.pDrive = pref.getDouble("pDrive", 0.0);
		NumberConstants.iDrive = pref.getDouble("iDrive", 0.0);
		NumberConstants.dDrive = pref.getDouble("dDrive", 0.0);
		Robot.dt.updatePID();
		
		SmartDashboard.putNumber("Left Encoder", dt.getLeftEncoderDist());
		SmartDashboard.putNumber("Right Encoder", dt.getRightEncoderDist());
	}
}
