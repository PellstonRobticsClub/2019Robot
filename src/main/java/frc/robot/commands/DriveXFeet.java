/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveXFeet extends PIDCommand {
  private double target;
  private double maxSpeed;
  private double prevSpeed =0;
  private double rampRate = .03;
  public DriveXFeet(double distance, double mSpeed) {
    super("DriveXFeet",.000016,0,0,.020);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_DriveTrain);
    maxSpeed = mSpeed;
    target = (distance*12000);
    
    getPIDController().setSetpoint(target);
    SmartDashboard.putNumber("target", target);
    setTimeout(3);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    while (Robot.m_DriveTrain.getPosition() != 0){
      Robot.m_DriveTrain.resetEnc();
    }
    
    getPIDController().enable();
    
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.m_DriveTrain.getPosition()-target)<4000 || isTimedOut());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_DriveTrain.setCoast();
    getPIDController().disable();
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.m_DriveTrain.getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    int sign =(int)Math.signum(output);
      double minSpeed=.1;
      double RampedSpeed;
      if (Math.abs(output)>prevSpeed+rampRate){
        RampedSpeed = prevSpeed+rampRate;
      } else {
        RampedSpeed = Math.abs(output);
      }
    	double finaloutput=sign*Math.min(maxSpeed, Math.max(minSpeed, RampedSpeed));

    Robot.m_DriveTrain.Drive(0, -finaloutput, 0);
    prevSpeed = Math.abs(finaloutput);
  }
}
