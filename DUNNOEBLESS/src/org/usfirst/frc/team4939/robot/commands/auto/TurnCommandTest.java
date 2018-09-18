package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class TurnCommandTest extends CommandGroup {

    public TurnCommandTest(char direction) {
    	//left turn tolerance 0.65
    	Robot.dt.resetGyroYaw();
    	addSequential(new TurnCommand (90, 0.25, 0.65));
    	
    }
}
//    	//addSequential(new TurnCommand(90, 1, 3));
//    	Robot.dt.resetGyroYaw();
//    	addSequential(new DriveCommand(0.80, 0.77, 0.2));
//    	addSequential(new PauseCommand(1));
//    	addSequential(new PlatformUp());
//    	//jerk forward
//    	
//    	addSequential(new DriveCommand (0.39, 0.36, 1.5));
//        //addSequential(new DriveCommand(140, 0.5, 0 , 2.5));
//    	addSequential(new PauseCommand (1));
//    	//drive forward
//    	
//    	if(direction == 'L'){
//    	addSequential (new BoxClose()); // reversed
//    	addSequential(new AutoBoxOuttake());
//        addSequential(new PauseCommand(1));
//        addSequential(new BoxStopIntake());
//        //release box
//
//        //Two Cube Part
//        addSequential(new DriveCommand (-0.45, -0.42, 1.5));
//        addSequential(new PlatformDown());
//        addSequential(new TurnCommand(35, 0.60, 1));
//        addSequential(new PauseCommand(1));
//        addSequential(new DriveCommand (0.45,0.42, 1.3));
//        addParallel (new AutoBoxIntake());
//        addSequential(new BoxOpen()); // reversed, it closes it
//        addSequential(new BoxStopIntake());
//        addSequential(new DriveCommand (-0.45, -0.42, 1.3));
//        addSequential(new TurnCommand(-35, 0.60, 1));
//        addSequential(new PlatformUp());
//        addSequential(new DriveCommand (0.45, 0.42, 1.5));
//        addSequential (new BoxClose()); // reversed, it opens it
//    	addSequential(new AutoBoxOuttake());
//        addSequential(new PauseCommand(1));
//        addSequential(new BoxStopIntake());
//        addSequential(new DriveCommand (-0.45, -0.42, 1.5));
//        addSequential(new PlatformDown());
//        
//        
//
//    	}
//    }
//}
