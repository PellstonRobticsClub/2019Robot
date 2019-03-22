/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSubSystem;

public class ResetLift extends Command {
  public ResetLift() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires( Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_lift.liftDownSlow();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.m_lift.downSwitch.get());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_lift.stop();
    Robot.m_lift.resetEncoder();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_lift.stop();
  }
}