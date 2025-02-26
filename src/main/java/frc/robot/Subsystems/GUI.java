package frc.robot.Subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.RobotMap;

public class GUI extends SubsystemBase {

  private static SendableChooser<Integer> drivingType = new SendableChooser<>();
  private static SendableChooser<Double> drivingMotorPower = new SendableChooser<>();
  private static SendableChooser<Double> shootingMotorPower = new SendableChooser<>();
  private static SendableChooser<Double> intakeMotorPower = new SendableChooser<>();
  private static SendableChooser<Double> armMotorPower = new SendableChooser<>();
  private static SendableChooser<Boolean> resettingGyroscope = new SendableChooser<>();
  private static SendableChooser<Integer> autonType = new SendableChooser<>();

  public void startGui() { 
    SmartDashboard.putData("Auton", autonType);
    autonType.setDefaultOption("Left", RobotMap.LEFT_AUTON);
    autonType.addOption("Right", RobotMap.RIGHT_AUTON);

    SmartDashboard.putData("Driving Type", drivingType);
    drivingType.setDefaultOption("Race Car", RobotMap.DOUBLE_VIDEO_GAME_DRIVING);
    drivingType.addOption("Tank", RobotMap.DOUBLE_VIDEO_GAME_DRIVING);

    SmartDashboard.putData("Driving Power", drivingMotorPower);
    drivingMotorPower.addOption("100%", 1.0);
    drivingMotorPower.addOption("90%", 0.9);
    drivingMotorPower.addOption("80%", 0.8);
    drivingMotorPower.setDefaultOption("75%", .75);
    drivingMotorPower.addOption("70%", 0.7);
    drivingMotorPower.addOption("60%", 0.6);
    drivingMotorPower.addOption("50%", 0.5);
    drivingMotorPower.addOption("40%", 0.4);
    drivingMotorPower.addOption("30%", 0.3);
    drivingMotorPower.addOption("20%", 0.2);
    drivingMotorPower.addOption("10%", 0.1);
    drivingMotorPower.addOption("Off", 0.0);

    SmartDashboard.putData("Shooting Power", shootingMotorPower);
    shootingMotorPower.setDefaultOption("100%", 1.0);
    shootingMotorPower.addOption("90%", 0.9);
    shootingMotorPower.addOption("80%", 0.8);
    shootingMotorPower.addOption("70%", 0.7);
    shootingMotorPower.addOption("60%", 0.6);
    shootingMotorPower.addOption("50%", 0.5);
    shootingMotorPower.addOption("40%", 0.4);
    shootingMotorPower.addOption("30%", 0.3);
    shootingMotorPower.addOption("20%", 0.2);
    shootingMotorPower.addOption("10%", 0.1);
    shootingMotorPower.addOption("Off", 0.0);

    SmartDashboard.putData("Intake Power", intakeMotorPower);
    intakeMotorPower.addOption("100%", 1.0);
    intakeMotorPower.addOption("90%", 0.9);
    intakeMotorPower.addOption("80%", 0.8);
    intakeMotorPower.addOption("70%", 0.7);
    intakeMotorPower.addOption("60%", 0.6);
    intakeMotorPower.setDefaultOption("50%", 0.5);
    intakeMotorPower.addOption("40%", 0.4);
    intakeMotorPower.addOption("30%", 0.3);
    intakeMotorPower.addOption("20%", 0.2);
    intakeMotorPower.addOption("10%", 0.1);
    intakeMotorPower.addOption("Off", 0.0);

    SmartDashboard.putData("Arm Power", armMotorPower);
    armMotorPower.addOption("100%", 1.0);
    armMotorPower.addOption("90%", 0.9);
    armMotorPower.addOption("80%", 0.8);
    armMotorPower.setDefaultOption("70%", 0.7);
    armMotorPower.addOption("60%", 0.6);
    armMotorPower.addOption("50%", 0.5);
    armMotorPower.addOption("40%", 0.4);
    armMotorPower.addOption("30%", 0.3);
    armMotorPower.addOption("20%", 0.2);
    armMotorPower.addOption("10%", 0.1);
    armMotorPower.addOption("Off", 0.0);
  }

  
  /** 
   * @return double
   */
  public double getDrivingPower() {
    return drivingMotorPower.getSelected();
  } public double getIntakePower() {
    return intakeMotorPower.getSelected();
  } public double getShootingPower() {
    return shootingMotorPower.getSelected();
  } public double getArmPower() {
    return armMotorPower.getSelected();
  } public int getDrivingType() {
    return drivingType.getSelected();
  } public boolean getResettingGyroscope() {
    return resettingGyroscope.getSelected();
  } public int getAutonType() {
    return autonType.getSelected();
  }

  public void startCamera() {
    CameraServer.startAutomaticCapture();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}