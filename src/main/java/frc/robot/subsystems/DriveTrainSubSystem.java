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
  public AHRS gyro = new AHRS(Port.kMXP);

  
  public void Drive (double ySpeed,double xSpeed,double zTwist){
    MecDrive.driveCartesian(ySpeed, -xSpeed, zTwist,-gyro.getYaw());

  }
   @Override
  public void initDefaultCommand() {
     setDefaultCommand(new driveWithJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
public void stop() {
  MecDrive.driveCartesian(0, 0, 0);
}
public void periodic(){
  SmartDashboard.putNumber("drive ticks", FrontLeftMotor.getEncoder().getPosition()*1494);
}
public double getPosition(){
return FrontLeftMotor.getEncoder().getPosition()*1494;
}
}
