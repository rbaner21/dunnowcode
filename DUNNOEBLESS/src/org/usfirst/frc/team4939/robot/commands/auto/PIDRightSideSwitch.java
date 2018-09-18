package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDRightSideSwitch extends CommandGroup {
	char direc;

	public PIDRightSideSwitch(char direction) {
		direc = direction;
		addSequential(new DriveCommand(0.50, 0.47, 0.2));
    	addSequential(new PauseCommand(0.5));
		addSequential(new DriveCommand(0.39, 0.36, 2.8));
		addSequential(new PlatformUp());
	
		if (direc == 'R') {
			addSequential(new TurnCommand(-90, 0.70, 1.05));
			addSequential(new DriveCommand(0.5, 0.47, 0.125));

//			addSequential(new BoxClose()); // reversed
			addSequential(new AutoBoxOuttake());
			addSequential(new PauseCommand(1));
			addSequential(new BoxStopIntake());

			 addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(-0.45, -0.43, 0.5));
			addSequential(new PlatformDown());

		}

		}
	}
