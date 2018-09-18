package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class yolocenter extends CommandGroup {
	char direc;

	public yolocenter(char direction) {
		direc = direction;
		addSequential(new DriveCommand(0.5, 0.5, 0.2));
    	addSequential(new PauseCommand(0.5));
		addSequential(new DriveCommand(0.4, 0.4, 0.8));
		addSequential(new PlatformUp());
	
		if (direc == 'R') {
			addSequential(new PIDTurn(-90));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(0.5, 0.5, 1));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new PIDTurn(90));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(0.5, 0.5, 1));


//			addSequential(new BoxClose()); // reversed
			addSequential(new AutoBoxOuttake());
			addSequential(new PauseCommand(1));
			addSequential(new BoxStopIntake());

			 addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(-0.45, -0.43, 0.5));
			addSequential(new PlatformDown());

		}
		if (direc == 'L') {
			addSequential(new PIDTurn(90));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(0.5, 0.5, 1));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new PIDTurn(-90));
	    	addSequential(new PauseCommand(0.5));
			addSequential(new DriveCommand(0.5, 0.5, 1));


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
