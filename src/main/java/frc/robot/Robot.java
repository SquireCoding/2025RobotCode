// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Subsystems.*;

public class Robot extends TimedRobot {
  RS rotateStyle= RS.SMASH;
  /**
   * the primary driver controller
   */
  private final XboxController controllerOne = new XboxController(RobotMap.DRIVER_CONTROLLER1);
  // private final Controllers controllerTwo = new Controllers(RobotMap.DRIVER_CONTROLLER2);
  /**
   * the drivetrain for the robot.
   */
  private final SDrivetrain sigma = new SDrivetrain();//
  static double desiredRotation = -1; //the desired rotation is held as a field because otherwise we would lose the reference object
  static int multSpeed = 10;//the speed 
  static double lastDesired = -1;//holds what the last desired rotation of the motors was
  static boolean rotating = false; //controls whether the robot's kept rotational value should be changed or nah
  public int turnDesired = -1;
  //still trying why!!!!!!!!!!!
  
  public void testPeriodic() {
    
    if (controllerOne.getStartButton()&&controllerOne.getBackButton()) {//if you hold the back and start at the same time, you calibrate the wheels to 180 degrees
      sigma.zeroSensors();
    }

    
      if (controllerOne.getBButton()) {//dev mode to get some diagnostic information
        System.out.println("\n\nRotational Speed: "+sigma.getYawSpeed()+"\nWanted Rotation: "+ SDrivetrain.fullRotation+"\nActual Rotation: "+sigma.getRotation());
        System.out.println("Front right diff: "+(sigma.getAbsFrontRightRotation()+","+sigma.getFrontRightRotation()));
        System.out.println("Front left: "+(sigma.getAbsFrontLeftRotation()+","+sigma.getFrontLeftRotation()));
        System.out.println("Back left: "+(sigma.getAbsBackLeftRotation()+","+sigma.getBackLeftRotation()));
        System.out.println("Back right: "+(sigma.getAbsBackRightRotation()+","+sigma.getBackRightRotation()));
      }
      //still trying to
      if (controllerOne.getRightX()>0.2||controllerOne.getRightX()<-0.2) {//if the right stick on the controller is being reasonaly held down, then we rotate
        
        if (rotateStyle==RS.SMASH) {
          double thresh = 10;//the threshhold for how close to the desired rotations the wheels have to be at to rotate
          if (!controllerOne.getRightBumperButton()&&isWithin(sigma.getFrontRightRotation(),315.0,thresh)&&isWithin(sigma.getBackLeftRotation(),135.0,thresh)&&isWithin(sigma.getBackRightRotation(),225.0,thresh)&&isWithin(sigma.getFrontLeftRotation(),45.0,thresh)) {
            double roter = 6-(4*controllerOne.getRightTriggerAxis());//press zr to make it go faster, and zl to make it go slower
            sigma.driveAmount(-controllerOne.getRightX()/(roter+20*controllerOne.getLeftTriggerAxis()));
            featherRotation(315, M.FRONTRIGHT);
            featherRotation(135, M.BACKLEFT);
            featherRotation(45, M.FRONTLEFT);
            featherRotation(225, M.BACKRIGHT);
          } else {
            sigma.stopDrive();//if we are not yet at the desired rotations of the wheels, do not drive
            featherRotation(315, M.FRONTRIGHT);
            featherRotation(135, M.BACKLEFT);
            featherRotation(45, M.FRONTLEFT);
            featherRotation(225, M.BACKRIGHT);
          }
          rotating=true;
        }
        if (rotateStyle==RS.TANK) {
          int amountToMove = 10;
          if (turnDesired==-1) {
            turnDesired=SDrivetrain.lastDesired;
          }
          double thresh = 10;//the threshhold for how close to the desired rotations the wheels have to be at to rotate
          if (!controllerOne.getRightBumperButton()&&isWithin(sigma.getFrontRightRotation(),turnDesired+amountToMove,thresh)&&isWithin(sigma.getBackLeftRotation(),turnDesired+amountToMove,thresh)&&isWithin(sigma.getBackRightRotation(),turnDesired-amountToMove,thresh)&&isWithin(sigma.getFrontLeftRotation(),turnDesired-amountToMove,thresh)) {
            double roter = 6-(4*controllerOne.getRightTriggerAxis());//press zr to make it go faster, and zl to make it go slower
            // sigma.driveAmount(-controllerOne.getRightX()/(roter+20*controllerOne.getLeftTriggerAxis()));
            sigma.driveCertain(M.RIGHT, -controllerOne.getRightX()/(roter+20*controllerOne.getLeftTriggerAxis()));
            sigma.driveCertain(M.LEFT, -controllerOne.getRightX()/(roter+20*controllerOne.getLeftTriggerAxis()));
            featherRotation(turnDesired+amountToMove, M.FRONTRIGHT);
            featherRotation(turnDesired+amountToMove, M.BACKLEFT);
            featherRotation(turnDesired-amountToMove, M.FRONTLEFT);
            featherRotation(turnDesired-amountToMove, M.BACKRIGHT);
          } else {
            sigma.stopDrive();//if we are not yet at the desired rotations of the wheels, do not drive
            featherRotation(turnDesired+amountToMove, M.FRONTRIGHT);
            featherRotation(turnDesired+amountToMove, M.BACKLEFT);
            featherRotation(turnDesired-amountToMove, M.FRONTLEFT);
            featherRotation(turnDesired-amountToMove, M.BACKRIGHT);
          }
          rotating=true;
        }
      } else if (controllerOne.getLeftX()>0.2||controllerOne.getLeftX()<-0.2||controllerOne.getLeftY()>0.2||controllerOne.getLeftY()<-0.2) {
        if (rotating) {//if we are rotating, update the held rotational value
          SDrivetrain.fullRotation=sigma.getRotation();
          rotating=false;
        }
        double y = controllerOne.getLeftY();
        double x = controllerOne.getLeftX();//get postion of the left joystick on the controller
        desiredRotation=Math.toDegrees(Math.atan2(y,x));//after finding the left and right values of the joystick, get the rotation in degrees by tan^-1(y/x)
        double speed=Math.sqrt(Math.pow(y,2)+Math.pow(x,2));//find the speed, by pythagorzing the x and y values aka john
        double desired=desiredRotation;//put the desired rotation somewhere else for some reason
        desired=desired<0?desired+360:desired; //if the desired value is less than 360, bump it up until it is
        desired=Math.abs(360-desired);//reverse the rotation so that it lines up with a unit circle
        if (Math.abs(Math.abs(SDrivetrain.lastDesired)-Math.abs((int)desired))>5) { //if the feathering rotation is more than the previous one, reset the increments of change of the rotation of the wheels 
          sigma.resetIncs();
        }
        if (controllerOne.getBackButton()) {//if you hold the back button, the wheels go to 180
          desired=180;
        }

        sigma.lastDesired=(int)desired;//update the last desired as a int to find the threshold of change
        if (controllerOne.getXButton()) {
          System.out.println("Desired: "+desired);
        }

        if (!isWithin(SDrivetrain.fullRotation,sigma.getRotation(),2)&&controllerOne.getLeftBumperButton()&&false) {//if we're outside of the rotation, update the rotation by turning the back right wheel proportionally
          if (sigma.getRotation()>SDrivetrain.fullRotation) {
            featherRotation(desired+Math.abs(calculateDistanceValue(sigma.getRotation(),SDrivetrain.fullRotation)), M.BACKRIGHT);
            featherRotation(desired, M.NOTBACKRIGHT);
          }
          if (sigma.getRotation()<SDrivetrain.fullRotation) {
            featherRotation(desired-Math.abs(calculateDistanceValue(sigma.getRotation(),SDrivetrain.fullRotation)), M.BACKRIGHT);
            featherRotation(desired, M.NOTBACKRIGHT);
          }
        } else if (controllerOne.getRightX()>0.2||controllerOne.getRightX()<-0.2 ){
           
        } else {
          System.out.println("POV: "+controllerOne.getPOV());
          featherRotation(desired, M.ALL);//otherwise, turn em all
        }
        
        multSpeed=10-(int)(controllerOne.getRightTriggerAxis()*10);//figure out how fast to go based on how much the zr button is held
        if (controllerOne.getRightBumperButton()) {//if you hold r it will not drive, just turn
          sigma.driveAmount(0);
        } else sigma.driveAmount(-speed/(multSpeed+10*controllerOne.getLeftTriggerAxis())); //now it will drive the amount you gave it
    } else {
      if (rotating||Math.abs(sigma.getYawSpeed())>1) {//if the robot is probably rotating on purpose, we want to hold its rotational value.
        SDrivetrain.fullRotation=sigma.getRotation();
        rotating=false;
      }
      sigma.turnAmount(0);//if we're not moving it on purpose, we should have it stop.
      sigma.driveAmount(0);
    }
  }

  /**
   * Takes a motor and an angular position, and feathers that rotational value on said motor
   * @param desired
   * @param motor the M enum that tells which motor to turn; @see M.java
   */
  public void featherRotation(double desired, M motor) {
    if (controllerOne.getLeftBumperButton()) {
      return;
    }
    if (motor==M.FRONTRIGHT||motor==M.FRONT||motor==M.RIGHT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTBACKLEFT||motor==M.NOTBACKRIGHT) {
      if (!isWithin(SDrivetrain.frontRightLastDesired,desired,3)){
        SDrivetrain.frontRightInc=SDrivetrain.startingInc;
        SDrivetrain.frontRightLastDesired=desired;
      }
      if ((Math.abs(Math.abs(desired-sigma.getFrontRightRotation()))<180&&(int)(sigma.getFrontRightRotation()/10)>(int)(desired/10)||(Math.abs(desired-sigma.getFrontRightRotation())>180&&(int)(sigma.getFrontRightRotation()/10)<(int)(desired/10)))) {
        if (SDrivetrain.frontRightInc<0) {
          SDrivetrain.frontRightInc= -SDrivetrain.frontRightInc;
          SDrivetrain.frontRightInc/=4;
        }
        sigma.motorFrontRightTurn.set(SDrivetrain.frontRightInc);
      }
      if ((Math.abs(desired-sigma.getFrontRightRotation())<180&&(int)(sigma.getFrontRightRotation()/10)<(int)(desired/10))||(Math.abs(Math.abs(desired-sigma.getFrontRightRotation()))>180&&(int)(sigma.getFrontRightRotation()/10)>(int)(desired/10))) {
        if (SDrivetrain.frontRightInc>0) {
          
          if (SDrivetrain.frontRightInc!=SDrivetrain.startingInc) {
            SDrivetrain.frontRightInc/=4;
          }
          SDrivetrain.frontRightInc= -SDrivetrain.frontRightInc;
        }
        sigma.motorFrontRightTurn.set(SDrivetrain.frontRightInc);
      }
    }
    if (motor==M.FRONTLEFT||motor==M.FRONT||motor==M.LEFT||motor==M.ALL||motor==M.NOTFRONTRIGHT||motor==M.NOTBACKLEFT||motor==M.NOTBACKRIGHT) {
      if (!isWithin(SDrivetrain.frontLeftLastDesired,desired,3)){
        SDrivetrain.frontLeftInc=SDrivetrain.startingInc;
        SDrivetrain.frontLeftLastDesired=desired;
      }
      if ((Math.abs(desired-sigma.getFrontLeftRotation())<180&&(int)(sigma.getFrontLeftRotation()/10)>(int)(desired/10)||(Math.abs(desired-sigma.getFrontLeftRotation())>180&&(int)(sigma.getFrontLeftRotation()/10)<(int)(desired/10)))) {
        if (SDrivetrain.frontLeftInc<0) {
          SDrivetrain.frontLeftInc= -SDrivetrain.frontLeftInc;
          SDrivetrain.frontLeftInc/=4;
        }
        sigma.motorFrontLeftTurn.set(SDrivetrain.frontLeftInc);
      }
      if ((Math.abs(desired-sigma.getFrontLeftRotation())<180&&(int)(sigma.getFrontLeftRotation()/10)<(int)(desired/10))||(Math.abs(desired-sigma.getFrontLeftRotation())>180&&(int)(sigma.getFrontLeftRotation()/10)>(int)(desired/10))) {
        if (SDrivetrain.frontLeftInc>0) {
          
          if (SDrivetrain.frontLeftInc!=SDrivetrain.startingInc) {
            SDrivetrain.frontLeftInc/=4;
          }
          SDrivetrain.frontLeftInc= -SDrivetrain.frontLeftInc;
        }
        sigma.motorFrontLeftTurn.set(SDrivetrain.frontLeftInc);
      }//done
    }
    if (motor==M.BACKRIGHT||motor==M.BACK||motor==M.RIGHT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTBACKLEFT||motor==M.NOTFRONTRIGHT) {
      if (!isWithin(SDrivetrain.backRightLastDesired,desired,3)){
        SDrivetrain.backRightInc=SDrivetrain.startingInc;
        SDrivetrain.backRightLastDesired=desired;
      }
      if ((Math.abs(desired-sigma.getBackRightRotation())<180&&(int)(sigma.getBackRightRotation()/10)>(int)(desired/10)||(Math.abs(desired-sigma.getBackRightRotation())>180&&(int)(sigma.getBackRightRotation()/10)<(int)(desired/10)))) {
        if (SDrivetrain.backRightInc<0) {
          SDrivetrain.backRightInc= -SDrivetrain.backRightInc;
          SDrivetrain.backRightInc/=4;
        }
        sigma.motorBackRightTurn.set(SDrivetrain.backRightInc);
      }
      if ((Math.abs(desired-sigma.getBackRightRotation())<180&&(int)(sigma.getBackRightRotation()/10)<(int)(desired/10))||(Math.abs(desired-sigma.getBackRightRotation())>180&&(int)(sigma.getBackRightRotation()/10)>(int)(desired/10))) {
        if (SDrivetrain.backRightInc>0) {
          
          if (SDrivetrain.backRightInc!=SDrivetrain.startingInc) {
            SDrivetrain.backRightInc/=4;
          }
          SDrivetrain.backRightInc= -SDrivetrain.backRightInc;
        }
        sigma.motorBackRightTurn.set(SDrivetrain.backRightInc);
      }
    }
    




    if (motor==M.BACKLEFT||motor==M.BACK||motor==M.LEFT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTFRONTRIGHT||motor==M.NOTBACKRIGHT) {
      if (!isWithin(SDrivetrain.backLeftLastDesired,desired,3)){
        SDrivetrain.backLeftInc=SDrivetrain.startingInc;
        SDrivetrain.backLeftLastDesired=desired;
      }
      if ((Math.abs(desired-sigma.getBackLeftRotation())<180&&(int)(sigma.getBackLeftRotation()/10)>(int)(desired/10)||(Math.abs(desired-sigma.getBackLeftRotation())>180&&(int)(sigma.getBackLeftRotation()/10)<(int)(desired/10)))) {
        if (SDrivetrain.backLeftInc<0) {
          SDrivetrain.backLeftInc= -SDrivetrain.backLeftInc;
          SDrivetrain.backLeftInc/=4;
        }
        sigma.motorBackLeftTurn.set(SDrivetrain.backLeftInc);
      }
      if ((Math.abs(desired-sigma.getBackLeftRotation())<180&&(int)(sigma.getBackLeftRotation()/10)<(int)(desired/10))||(Math.abs(desired-sigma.getBackLeftRotation())>180&&(int)(sigma.getBackLeftRotation()/10)>(int)(desired/10))) {
        if (SDrivetrain.backLeftInc>0) {
          
          if (SDrivetrain.backLeftInc!=SDrivetrain.startingInc) {
            SDrivetrain.backLeftInc/=4;
          }
          SDrivetrain.backLeftInc= -SDrivetrain.backLeftInc;
        }
        sigma.motorBackLeftTurn.set(SDrivetrain.backLeftInc);
      }
  }
  }
  
  /** 
   * this is a numeric method that checks if one number is within another.
   * @param toCheck the original number to check
   * @param toCompareTo the number you want to compare to
   * @param threshold the threshold value
   * @return boolean
   */
  public static boolean isWithin(double toCheck, double toCompareTo, double threshold) {
      return toCheck >= (toCompareTo - threshold) && toCheck <= (toCompareTo + threshold);
  }

  /**
   * This a method that calculates how far the wheels should turn when accounting for the difference between held rotational yaw value and the actual rotational yaw value.
   * @param input1 One of the rotations
   * @param input2 The other of the rotations
   * @return the value of how far the wheel should turn
   */
  public static double calculateDistanceValue(double input1, double input2) {
    // Calculate the raw difference between the two angles
    double diff = Math.abs(input1 - input2);
    
    double result = 0.00347222*Math.pow(diff,2);
    
    return result;
}
  @Override
  public void autonomousPeriodic() {
    // driveWithJoystick(false);
  }

  @Override
  public void teleopPeriodic() {
    /*
     *if (controllerOne.getXButton()) System.out.println(sigma.getBackRightRotation());
    if (controllerOne.getAButton()) sigma.lowerVoltage();
    if (controllerOne.getBButton()) sigma.motorBackRightTurn.getEncoder().setPosition(0);
    if (!controllerOne.getYButton()) {
      sigma.turnAmount(controllerOne.getRawAxis(4)/5.0);
      sigma.driveAmount(controllerOne.getRawAxis(1)/5.0);
    } else {
      double val = sigma.getBackRightRotation();
      while (val>=21.5) {
        val-=21.5;
      }
      sigma.motorBackRightTurn.getEncoder().setPosition(val);
      if (sigma.getBackRightRotation()>1) {
        sigma.motorBackRightTurn.set(-0.7);
      } else
      if (sigma.getBackRightRotation()<-1) {
        sigma.motorBackRightTurn.set(0.7);
      } else 
      if (sigma.getBackRightRotation()>0.5) {
        sigma.motorBackRightTurn.set(-0.1);
      } else
      if (sigma.getBackRightRotation()<-0.5) {
        sigma.motorBackRightTurn.set(0.1);
      } else 
      if (sigma.getBackRightRotation()>0.05) {
        sigma.motorBackRightTurn.set(-0.05);
      } else
      if (sigma.getBackRightRotation()<-0.05) {
        sigma.motorBackRightTurn.set(0.05);
      }
    } 
     */
    
    // driveWithJoystick(true);
 }
/*
21.45ss
  private void driveWithJoystick(boolean fieldRelative) {
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    final var xSpeed =
        -m_xspeedLimiter.calculate(MathUtil.applyDeadband(controllerOne.getDriverAxis(RobotMap.RIGHT_STICK_X), 0.02))
            * Drivetrain.kMaxSpeed;

    // Get the y speed or sideways/strafe speed. We are inverting this because
    // we want a positive value when we pull to the left. Xbox controllers
    // return positive values when you pull to the right by default.
    final var ySpeed =
        -m_yspeedLimiter.calculate(MathUtil.applyDeadband(controllerOne.getDriverAxis(RobotMap.RIGHT_STICK_Y), 0.02))
            * Drivetrain.kMaxSpeed;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot =
        -m_rotLimiter.calculate(MathUtil.applyDeadband(controllerOne.getDriverAxis(RobotMap.RIGHT_STICK_X), 0.02))
            * Drivetrain.kMaxAngularSpeed;

    m_swerve.drive(xSpeed, ySpeed, rot, fieldRelative, getPeriod());
  }
    */
}
