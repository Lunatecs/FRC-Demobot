// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JoystickConstants;

import frc.robot.commands.LooneyDriveCommand;
import frc.robot.commands.RunFeederCommand;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class RobotContainer {
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final FeederSubsystem feeder = new FeederSubsystem();

  private final Joystick driverJoystick = new Joystick(Constants.JoystickConstants.DRIVER_USB);

  public RobotContainer() {
    configureBindings();
    configureDefaultCommands();
  }
  public void configureDefaultCommands() {
    // add keybinds for turnInPlace, fast, and reverse
    drivetrain.setDefaultCommand(new LooneyDriveCommand(drivetrain,
    () -> {return driverJoystick.getRawAxis(Constants.JoystickConstants.LEFT_Y_AXIS);}, 
    () -> {return driverJoystick.getRawAxis(Constants.JoystickConstants.RIGHT_X_AXIS);}, 
    () -> driverJoystick.getRawButton(JoystickConstants.RIGHT_BUMPER), 
    () -> driverJoystick.getRawButton(JoystickConstants.LEFT_BUMPER)));

  }

  private void configureBindings() {

    new Trigger(() -> {return Math.abs(driverJoystick.getRawAxis(JoystickConstants.RIGHT_TRIGGER)) > 0.1;}).onTrue(new ParallelCommandGroup(new RunIntakeCommand(() -> driverJoystick.getRawAxis(JoystickConstants.RIGHT_TRIGGER), intake),
                                                                                                                                            new RunFeederCommand(() -> driverJoystick.getRawAxis(JoystickConstants.RIGHT_TRIGGER), feeder)))
                                                                                                            .onFalse(new ParallelCommandGroup(new InstantCommand(() -> {}, intake),
                                                                                                                                              new InstantCommand(() -> {}, feeder)));
    new Trigger(() -> {return Math.abs(driverJoystick.getRawAxis(JoystickConstants.LEFT_TRIGGER)) > 0.1;}).onTrue(new ParallelCommandGroup(new RunIntakeCommand(() -> -driverJoystick.getRawAxis(JoystickConstants.LEFT_TRIGGER), intake),
                                                                                                                                              new RunFeederCommand(() -> -driverJoystick.getRawAxis(JoystickConstants.LEFT_TRIGGER), feeder)))
                                                                                                              .onFalse(new ParallelCommandGroup(new InstantCommand(() -> {}, intake),
                                                                                                                                                new InstantCommand(() -> {}, feeder)));
  }



}
