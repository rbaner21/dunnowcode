package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurn extends Command {
	
	boolean finished;
	double current;
	int counter;
	double target;
	double kP = 0.0095;
	double prev=0.0; 
	double kD= 0.0;
	

    public PIDTurn(double angle) {
   requires(Robot.dt);
   target = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    	finished = false;
    	Robot.dt.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double current = Robot.dt.getGyroYaw();
    	double error = target-current;
    	if(counter==1){
    		finished = true;
    		System.out.println("Turn Finished");
    	}
    	if(Math.abs(error)<40){
    		counter= 1;
    	} else {
    	
    	Robot.dt.runleftsidedrive(error*kP);
    	Robot.dt.runrightsidedrive(error*kP);
    	
    }
//    	Robot.dt.runleftsidedrive(0.5);
//    	Robot.dt.runrightsidedrive(0.5);
    	
    	prev = error;
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
