/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.liftStop;

/**
 * Add your docs here.
 */
public class LiftSubSystem extends PIDSubsystem {
  private WPI_TalonSRX liftMotor = new WPI_TalonSRX(5);
  public DigitalInput downSwitch = new DigitalInput(0);
  /**
   * Add your docs here.
   */
  public LiftSubSystem() {
    // Intert a subsystem name and PID values here
    super("LiftSubSystem", .01, 0, 0);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new liftStop());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return liftMotor.getSensorCollection().getPulseWidthPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    lift(output);
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
  public void lift(double speed){
    double outspeed=speed;
    SmartDashboard.putNumber("Lift Speed", speed);
    if(liftMotor.getSensorCollection().getPulseWidthPosition()<-14700){
      outspeed=Math.min(speed, 0);
    }
    if(liftMotor.getSensorCollection().getPulseWidthPosition()>-200){
      outspeed=Math.max(speed, 0);
    }
    liftMotor.set(outspeed);
  }
  public void stop(){
    this.disable();
    lift(0);
  }
  public void periodic(){
   SmartDashboard.putNumber("Lift Position", liftMotor.getSensorCollection().getPulseWidthPosition());

  }
public void PIDControl(double position ){
    this.setSetpoint(position);
    this.enable();
  }
  public boolean isAtPos(){
    return this.onTarget();
  }
  public void resetEncoder(){
        liftMotor.getSensorCollection().setPulseWidthPosition(0, 10);
  }
  public void liftDownSlow(){
    liftMotor.set(.25);
  }

}
