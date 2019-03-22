/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.manuallyPivot;

/**
 * Add your docs here.
 */
public class shooterPivotSubSystem extends PIDSubsystem {
  
  private WPI_TalonSRX pivot = new WPI_TalonSRX(1);
  private WPI_TalonSRX pivot_2 = new WPI_TalonSRX(2);
  private int pTimer =0;
  private WPI_TalonSRX Kicker = new  WPI_TalonSRX(6);
  
  /**
   * Add your docs here.
   */
  public shooterPivotSubSystem() {

    // Intert a subsystem name and PID values here
    super("shooterPivotSubSystem", .01, 0, 0);
    pivot_2.follow(pivot);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new manuallyPivot());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    return pivot.getSensorCollection().getPulseWidthPosition();

     // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
  }

  @Override
  protected void usePIDOutput(double output) {
    drive(output);
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
  public void drive(double speed){


    double outspeed=speed;
   
    if(pivot.getSensorCollection().getPulseWidthPosition()<-2400){
      outspeed=Math.max(speed, 0);
    }
    if(pivot.getSensorCollection().getPulseWidthPosition()>3250&&speed>0){
      
      if(pivot.getSensorCollection().getPulseWidthPosition()>3550){
        outspeed=Math.min(speed, 0);
      }else{
        outspeed=speed*.5;
      }
    }
    if(speed>0&&Robot.m_FourBar.getPosition()<RobotMap.fourbarMinTilt&&pivot.getSensorCollection().getPulseWidthPosition()<RobotMap.pivotMaxTilt){
      outspeed=0;
    }
   

    pivot.set(outspeed);
    SmartDashboard.putNumber("pivot Speed",speed);
    

  }
  public void kick(double speed){
    
    Kicker.set(speed);

  }
  public void stop(){
    this.disable();
    Kicker.set(0);
  }
  public void periodic(){
   // if (pTimer == 10){
     //  SmartDashboard.putNumber("pivot Position", pivot.getSensorCollection().getPulseWidthPosition());
   // }
   // pTimer++; 
  }
  public void PIDControl(double position ){
    this.setSetpoint(position);
    this.enable();
  }
  public boolean isAtPos(){
    return this.onTarget();
  }
 
}
