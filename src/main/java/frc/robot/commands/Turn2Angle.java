/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class Turn2Angle extends PIDCommand {
  double angle;
  public Turn2Angle(double desiredAngle){ 
    super("Turn2Angle",.5,0,.002,.02);
    getPIDController().setInputRange(-180.0, 180.0);
    getPIDController().setContinuous(true);
    getPIDController().setAbsoluteTolerance(1.5);
    getPIDController().setOutputRange(-.5, .5);
    angle = desiredAngle;
    requires(Robot.m_DriveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.setTimeout(3);
    getPIDController().setSetpoint(angle);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut()||getPIDController().onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  Robot.m_DriveTrain.stop();    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  @Override
  protected double returnPIDInput() {
    return Robot.m_DriveTrain.gyro.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    int sign = ( int )Math.signum(output);
    double minSpeed =.2;
    double finaloutput = sign*Math.max(minSpeed, Math.abs(output));
    Robot.m_DriveTrain.Drive(0, 0, finaloutput);


  }
}
