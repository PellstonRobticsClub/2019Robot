/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.shooterStop;

/**
 * Add your docs here.
 */ 
public class ShooterIntakeSubSystem extends Subsystem {
  private WPI_TalonSRX shooterMotor = new WPI_TalonSRX(4);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new shooterStop());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void stop(){
    shooterMotor.set(0);
  }
  public void spin(double speed){
    shooterMotor.set(speed);
  }
  public void periodic(){
    
  }
}
