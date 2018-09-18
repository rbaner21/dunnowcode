package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;
import org.usfirst.frc.team4939.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
public class TimedCenterAuto extends CommandGroup {
//
    public TimedCenterAuto(char direction) {
    	Robot.dt.resetGyroYaw();
    	addSequential(new DriveCommand(0.40, 0.37, 0.35));
    	addParallel(new PlatformUp());
    	addSequential(new PauseCommand(1));
    	Robot.dt.resetGyroYaw();
        //jerk forward
    	
    	if(direction == 'R'){
    	Robot.dt.resetGyroYaw();
        addSequential(new TurnCommand(49, 0.25, 0.65));
    	addSequential(new DriveCommand (0.40, 0.37, 1.52));
        addSequential(new PauseCommand (0.25));
        Robot.dt.resetGyroYaw();
        addSequential(new TurnCommand(-45, 0.25, 0.65));
        addSequential(new DriveCommand(0.45, 0.42, 1));
    	addSequential (new BoxClose()); // reversed
    	addSequential(new AutoBoxOuttake());
        addSequential(new PauseCommand(1));
        addSequential(new BoxStopIntake());
        //release box 
        //addSequential(new PauseCommand(1));
        addSequential(new DriveCommand (-0.45, -0.42, 1.25));
//        
//        //back up and bring platform down
//        
//        Robot.dt.resetGyroYaw();
//        addSequential(new TurnCommand (90, 0.25, 1.5));
//        addSequential(new DriveCommand (-0.56, -0.53, 0.84));
//        
//        addSequential(new TurnCommand (0, 0.75, 1.3));
        addSequential(new PlatformDown());

     
    	}
    	
    	else if(direction == 'L'){
    	 	Robot.dt.resetGyroYaw();
            addSequential(new TurnCommand(-50, 0.35, 0.65));
        	addSequential(new DriveCommand (0.40, 0.37, 1.64));
            addSequential(new PauseCommand (0.25));
            Robot.dt.resetGyroYaw();
            addSequential(new TurnCommand(45, 0.25, 0.65));
            addSequential(new DriveCommand(0.45, 0.42, 1));
        	addSequential (new BoxClose()); // reversed
        	addSequential(new AutoBoxOuttake());
            addSequential(new PauseCommand(1));
            addSequential(new BoxStopIntake());
            //release box 
            //addSequential(new PauseCommand(1));
            addSequential(new DriveCommand (-0.45, -0.42, 1.25));
//            
//            //back up and bring platform down
//            
//            Robot.dt.resetGyroYaw();
//            addSequential(new TurnCommand (90, 0.25, 1.5));
//            addSequential(new DriveCommand (-0.56, -0.53, 0.84));
//            
//            addSequential(new TurnCommand (0, 0.75, 1.3));
            addSequential(new PlatformDown());
    		
    	}
    }
}
