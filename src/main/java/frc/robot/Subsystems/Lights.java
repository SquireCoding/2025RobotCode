// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.RobotMap;

public class Lights extends SubsystemBase {

  private static Spark led = new Spark(RobotMap.LED_PORT);

  public Lights() {}

  
  /** 
   * @param colorPattern
   */
  public void setLED(double colorPattern) {
    led.set(colorPattern);
  }

  @Override
  public void periodic() {
    
  }
}