package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedLeftAuto extends CommandGroup {

    public TimedLeftAuto(char direction) {
    	//addSequential(new TurnCommand(90, 1, 3));
    	//Robot.dt.resetGyroYaw();
    	addSequential(new DriveCommand(0.50, 0.47, 0.2));
    	addSequential(new PauseCommand(1));
    	addSequential(new PlatformUp());
    	//jerk forward
    	
    	addSequential(new DriveCommand (0.39, 0.36, 1.5));
        //addSequential(new DriveCommand(140, 0.5, 0 , 2.5));
    	addSequential(new PauseCommand (1));
    	//drive forward
    	
    	if(direction == 'L'){
    		addSequential (new BoxClose()); // reversed
    	addSequential(new AutoBoxOuttake());
        addSequential(new PauseCommand(1));
        addSequential(new BoxStopIntake());
        //release box
         
    //    addSequential(new PauseCommand(1));
        addSequential(new DriveCommand (-0.45, -0.43, 1));
        addSequential(new PlatformDown());
        //back up and bring platform down
       // addSequential(new DriveCommand (-0.75, -0.10, 0.50));
      //  addSequential(new PauseCommand (1));
     //   addSequential(new DriveCommand (-0.1, -0.75, 0.85));*/
    	}
    }
}
