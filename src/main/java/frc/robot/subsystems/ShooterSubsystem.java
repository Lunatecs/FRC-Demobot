// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private final CANSparkMax leftFlywheel = new CANSparkMax(0, MotorType.kBrushless);
  private final CANSparkMax rightFlywheel = new CANSparkMax(0, MotorType.kBrushless);
  
  public ShooterSubsystem() {
    leftFlywheel.restoreFactoryDefaults();
    rightFlywheel.restoreFactoryDefaults();

    leftFlywheel.setIdleMode(IdleMode.kCoast);
    rightFlywheel.setIdleMode(IdleMode.kCoast);

    leftFlywheel.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setFlywheelSpeed(double speed) {
    leftFlywheel.set(speed);
    rightFlywheel.set(speed);
  }
}
