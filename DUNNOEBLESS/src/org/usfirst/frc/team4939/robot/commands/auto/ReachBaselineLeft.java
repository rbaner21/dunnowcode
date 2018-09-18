package org.usfirst.frc.team4939.robot.commands.auto;

import org.usfirst.frc.team4939.robot.commands.BoxStopIntake;
import org.usfirst.frc.team4939.robot.commands.PlatformUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReachBaselineLeft extends CommandGroup {

    public ReachBaselineLeft(char direction) {
        addSequential(new PlatformUp());
        addSequential(new DriveCommand(0.5,0.5,2.6));
		}
}
// made by rishav