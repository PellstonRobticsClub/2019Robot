/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Drive2Ball;
import frc.robot.commands.kickWithButton;
import frc.robot.commands.liftDown;
import frc.robot.commands.liftUp;
import frc.robot.commands.shooterPushOut;
import frc.robot.commands.shooterTakeIn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
 
  public Joystick stick = new Joystick(0);
  public Joystick stick_2 = new Joystick(1);
  public JoystickButton triggerbutton = new JoystickButton(stick, 1);
  public JoystickButton buttonA = new JoystickButton(stick_2,1);
  public JoystickButton buttonB = new JoystickButton(stick_2,2);
  public JoystickButton buttonX = new JoystickButton(stick_2, 3);
  public JoystickButton buttonY = new JoystickButton(stick_2, 4);
  public JoystickButton buttonRB = new JoystickButton(stick_2, 6);
  public OI () {
    stick.getPOV();
    triggerbutton.whenPressed(new Drive2Ball());
    buttonA.whileHeld(new liftDown());
    buttonB.whileHeld(new liftUp());
    buttonX.whileHeld(new shooterTakeIn());
    buttonY.whileHeld(new shooterPushOut());
    buttonRB.whenPressed(new kickWithButton());
  }
 

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
