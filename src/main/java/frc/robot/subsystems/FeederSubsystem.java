// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederConstants;

public class FeederSubsystem extends SubsystemBase {
  private final WPI_VictorSPX leftGuiders = new WPI_VictorSPX(FeederConstants.LEFT_MOTOR);
  private final WPI_VictorSPX rightGuiders = new WPI_VictorSPX(FeederConstants.RIGHT_MOTOR);

  public FeederSubsystem() {
    leftGuiders.configFactoryDefault();
    rightGuiders.configFactoryDefault();
    
    leftGuiders.setNeutralMode(NeutralMode.Brake);
    rightGuiders.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setFeederSpeed(double speed) {
    leftGuiders.set(ControlMode.PercentOutput, speed);
    rightGuiders.set(ControlMode.PercentOutput, -speed);
  }
}
