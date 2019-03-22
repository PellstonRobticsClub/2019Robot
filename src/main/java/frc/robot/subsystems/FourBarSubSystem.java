/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.manualFourBar;

/**
 * Add your docs here.
 */
public class FourBarSubSystem extends PIDSubsystem {
  private WPI_TalonSRX FourBarMotor = new WPI_TalonSRX(3);
  private AnalogInput pot = new AnalogInput(0);
  /**
   * Add your docs here.
   */
  public FourBarSubSystem() {
    // Intert a subsystem name and PID values here
    super("FourBarSubSystem", .3333, 0, 0);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new manualFourBar());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return pot.getAverageVoltage();
  }

  @Override
  protected void usePIDOutput(double output) {
    FourBarMotor.set(output);
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
  public void driveFourBar(double speed){
    //this.disable();
    double outspeed=speed;
    SmartDashboard.putNumber("FourBar Speed", speed);
    if (pot.getAverageVoltage()<1){
      outspeed=Math.min(speed, 0);   
   }
    if(pot.getAverageVoltage()>4.5){
      outspeed=Math.max(speed, 0);
   }
   if(speed>0&&Robot.m_shooterPivot.getPosition()<RobotMap.pivotMaxTilt&&pot.getAverageVoltage()>RobotMap.fourbarMinTilt){
     outspeed=0;
   }
    FourBarMotor.set(outspeed);
    
  }
  public void PIDControl(double position ){
    this.setSetpoint(position);
    this.enable();
  }
  public boolean isAtPos(){
    return this.onTarget();
  }
  public void periodic (){
    SmartDashboard.putNumber("fourBar Position", pot.getAverageVoltage());

  }
  public double getPot(){
    return pot.getAverageVoltage();
  }
  public void disablePid(){
    this.disable();
  }
  public void Stop(){
FourBarMotor.set(0);
  }
}
