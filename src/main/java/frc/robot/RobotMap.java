/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public  class RobotMap {
  public static double cargoTopFourBar=0;
  public static double cargoTopPivot=0;
  public static double cargoTopLift=0;
 
  public static double cargoMidFourBar=0;
  public static double cargoMidPivot=0;
  public static double cargoMidLift=0;

  public static double cargoBotFourBar=0;
  public static double cargoBotPivot=0;
  public static double cargoBotLift=0;

  public static double cargoFloorFourBar=0;
  public static double cargoFloorPivot=0;
  public static double cargoFloorLift=0;

  public static double hatchTopFourBar=0;
  public static double hatchTopPivot=0;
  public static double hatchTopLift=0;
 
  public static double hatchMidFourBar=0;
  public static double hatchMidPivot=0;
  public static double hatchMidLift=0;

  public static double hatchBotFourBar=0;
  public static double hatchBotPivot=0;
  public static double hatchBotLift=0;

  public static double hatchFloorFourBar=0;
  public static double hatchFloorPivot=0;
  public static double hatchFloorLift=0;
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static static int leftMotor = 1;
  // public static static int rightMotor = 2;
public static double pivotMaxTilt;
public static double fourbarMinTilt;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static static int rangefinderPort = 1;
  // public static static int rangefinderModule = 1;
}
