package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDStraight extends Command {
	boolean finished;
	int counter = 0;
	double leftKP = 0.8;
	double rightKP = 0.8;
	double leftError;
	double rightError;
	double target;

    public PIDStraight(double dist) {
        requires(Robot.dt);
        target = dist;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetEncoders();
    	 counter = 0;
         finished = false;
     	target = target *1.0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftError = target - Robot.dt.getLeftEncoderDist();
    	rightError = target - Robot.dt.getRightEncoderDist();
    	System.out.println("Running");

    	Robot.dt.runleftsidedrive(leftError*leftKP);
    	Robot.dt.runleftsidedrive(rightError*rightKP);
//    	Robot.dt.runleftsidedrive(0.5);
//    	Robot.dt.runrightsidedrive(0.5);
    	
    	if(leftError<10&rightError<10){
    		counter++;
    	}
    	
    	if(counter<0)
    		finished = true;    	
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
