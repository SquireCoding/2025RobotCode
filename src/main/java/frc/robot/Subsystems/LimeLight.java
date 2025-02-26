// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.RobotMap;

public class LimeLight extends SubsystemBase {
  
  public LimeLight() {}

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry brightness = table.getEntry("ledMode");
  NetworkTableEntry seeTag = table.getEntry("tv");

  
  /** 
   * @return double
   */
  public double getXValue() {
    return tx.getDouble(3);
  } public double getYValue() {
    return ty.getDouble(0.0);
  } public double getArea() {
    return ta.getDouble(0.0);
  } public double seeTag() {
    return seeTag.getDouble(0.0);
  }

  @Override
  public void periodic() {
  }

  public boolean doWeSeeTag(){
    if (seeTag() > 0) {
      return true;
    }
    else {
      return false;
    }
  }

  public void turnOffLights(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }
  public void turnOnLights(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
  }

  public double estimateDistanceToGoal() {
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = RobotMap.MOUNTING_ANGLE; 

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = RobotMap.LENS_HEIGHT_INCHES; 

    // distance from the target to the floor
    double goalHeightInches = 57; 

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }

  public double findArmHeight() {
    return -(Math.exp(4.02824 - 0.006044* estimateDistanceToGoal()));
  }
}