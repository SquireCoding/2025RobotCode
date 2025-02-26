package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.RobotMap;

public class Controllers extends SubsystemBase {

  int portNum;
  XboxController joystick;

  public Controllers(int portNum) {
    this.portNum = portNum;
    joystick = new XboxController(portNum);
  }

  public void initDefaultCommand() {
  }

  
  /** 
   * @param axis
   * @return double
   */
  public double getDriverAxis(int axis) {
    return joystick.getRawAxis(axis);
  }

  public double getDifferenceInTriggers() {
    return (getDriverAxis(RobotMap.RIGHT_TRIGGER) - getDriverAxis(RobotMap.LEFT_TRIGGER));
  }

  public int booleanToOneOrZero(boolean trueOrFalse) {
    if (trueOrFalse == true) {
      return 1;
    } else {
      return 0;
    }
  }

  public boolean isButtonPressed(int buttonID) {
    return joystick.getRawButton(buttonID);
  }

  public int getIsButtonPressed (int buttonID){
    return booleanToOneOrZero(isButtonPressed(buttonID));
  }

  public double getDifferenceInBumpers() {
    return (booleanToOneOrZero(isButtonPressed(RobotMap.RIGHT_BUMPER)) - booleanToOneOrZero(isButtonPressed(RobotMap.LEFT_BUMPER)));
  }

  @Override
  public void periodic() {

  }
}