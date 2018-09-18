package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.commands.BoxClose;
import org.usfirst.frc.team4939.robot.commands.BoxStopIntake;
import org.usfirst.frc.team4939.robot.commands.PlatformDown;
import org.usfirst.frc.team4939.robot.commands.PlatformUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDLeftSideSwitch extends CommandGroup {
	char direc;

	public PIDLeftSideSwitch(char direction) {
		direc = direction;
		addSequential(new DriveCommand(0.80, 0.47, 0.2));
    	addSequential(new PauseCommand(0.5));
		addSequential(new DriveCommand(0.39, 0.39, 2.8));
		addSequential(new PlatformUp());
	
		if (direc == 'L') {
			addSequential(new TurnCommand(90, 0.65, 1.05));
			addSequential(new PauseCommand(1));
			addSequential(new DriveCommand(0.40, 0.47, 1));

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
