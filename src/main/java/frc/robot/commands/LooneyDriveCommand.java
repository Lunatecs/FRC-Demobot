// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class LooneyDriveCommand extends CommandBase {
  DrivetrainSubsystem drivetrain;
  DoubleSupplier speedSupplier;
  DoubleSupplier rotationSupplier;
  BooleanSupplier turnInPlace;
  BooleanSupplier slow;
  
  public LooneyDriveCommand(DrivetrainSubsystem drivetrain, DoubleSupplier speedSupplier, DoubleSupplier rotationSupplier, BooleanSupplier turnInPlace, BooleanSupplier slow) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.speedSupplier = speedSupplier;
    this.rotationSupplier = rotationSupplier;
    this.turnInPlace = turnInPlace;
    this.slow = slow;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double speedMulti = .90;
    double rotationMulti = 1;
    
    if (turnInPlace.getAsBoolean()){
      rotationMulti = rotationMulti * 0.5;
    }
    
    if (slow.getAsBoolean()) {
      speedMulti = .3;
      rotationMulti = .3;
    }

    drivetrain.curvatureDrive(speedSupplier.getAsDouble()*speedMulti, rotationSupplier.getAsDouble()*rotationMulti, turnInPlace.getAsBoolean());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}