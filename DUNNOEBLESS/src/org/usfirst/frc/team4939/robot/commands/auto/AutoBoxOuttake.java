package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoBoxOuttake extends Command {

    public AutoBoxOuttake() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

 // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.useIntake(0.95);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    //	Robot.intake.useIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	cancel();
    }
}