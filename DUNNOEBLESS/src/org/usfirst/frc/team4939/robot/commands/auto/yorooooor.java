package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class yorooooor extends CommandGroup {
	char direc;

	public yorooooor(char direction) {
		direc = direction;
		addSequential(new DriveCommand(0.5, 0.5, 0.2));
    	addSequential(new PauseCommand(0.5));
		addSequential(new DriveCommand(0.4, 0.4, 2.8));
		addSequential(new PlatformUp());
	
		if (direc == 'R') {
			addSequential(new PIDTurn(-90));
			addSequential(new DriveCommand(0.5, 0.5, 0.125));

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
