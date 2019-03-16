/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.driveWithJoystick;

/**
 * Add your docs here.
 */
public class DriveTrainSubSystem extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  CANSparkMax FrontLeftMotor = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax FrontRightMotor = new CANSparkMax(12, MotorType.kBrushless);
  CANSparkMax RearLeftMotor = new CANSparkMax(13, MotorType.kBrushless);
  CANSparkMax RearRightMotor = new CANSparkMax(14, MotorType.kBrushless);
  MecanumDrive MecDrive = new MecanumDrive(FrontLeftMotor, RearLeftMotor, FrontRightMotor, RearRightMotor);
  CANEncoder FLencoder =new CANEncoder(FrontLeftMotor);
  private int ptimer = 0;
  public AHRS gyro = new AHRS(Port.kMXP);
  

  
  public void Drive (double xSpeed,double ySpeed,double zTwist){
    if(Math.abs(ySpeed)<0.1){
      ySpeed =0;
    }
    MecDrive.driveCartesian(xSpeed, -ySpeed, zTwist,-gyro.getYaw());
  }
   @Override
  public void initDefaultCommand() {
    setCoast();
     setDefaultCommand(new driveWithJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
public void stop() {
  MecDrive.driveCartesian(0, 0, 0);
}
public void periodic(){
  if (ptimer == 10){
    ptimer = 0;
    SmartDashboard.putNumber("FLdrive ticks", FrontLeftMotor.getEncoder().getPosition()*1494);
    SmartDashboard.putNumber("FRdrive ticks", FrontRightMotor.getEncoder().getPosition()*1494);
    SmartDashboard.putNumber("RLdrive ticks", RearLeftMotor.getEncoder().getPosition()*1494);
    SmartDashboard.putNumber("RRdrive ticks", RearRightMotor.getEncoder().getPosition()*1494);
  }
  ptimer++;
//  
}
public double getPosition(){
return FrontLeftMotor.getEncoder().getPosition()*1494;
}
public void setBrake(){
  FrontLeftMotor.setIdleMode(IdleMode.kBrake);
  FrontRightMotor.setIdleMode(IdleMode.kBrake);
  RearLeftMotor.setIdleMode(IdleMode.kBrake);
  RearRightMotor.setIdleMode(IdleMode.kBrake);
}
public void setCoast(){
  FrontLeftMotor.setIdleMode(IdleMode.kCoast);
  FrontRightMotor.setIdleMode(IdleMode.kCoast);
  RearLeftMotor.setIdleMode(IdleMode.kCoast);
  RearRightMotor.setIdleMode(IdleMode.kCoast);
}
public void resetEnc(){
  FrontLeftMotor.getEncoder().setPosition(0);
  FrontRightMotor.getEncoder().setPosition(0);
  RearLeftMotor.getEncoder().setPosition(0);
  RearRightMotor.getEncoder().setPosition(0);
}
}
