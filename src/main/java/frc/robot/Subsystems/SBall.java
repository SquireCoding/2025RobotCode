package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import static edu.wpi.first.units.Units.Degree;


public class SBall {
    public static final SparkMax downward = new SparkMax(33, MotorType.kBrushless);
    public static final SparkMax grabber = new SparkMax(35, MotorType.kBrushless);
    public SBall() {
        
    }
    public static void moveDownward() {
        smallGrab();
        downward.set(-0.1);
    }
    public static void moveBackDownward() {
        downward.set(0.15);
        //release();
    }
    public static void modDownward(double amt) {
        smallGrab();
        downward.set(-0.1-0.2*amt);
    }
    public static void modBackDownward(double amt) {
        downward.set(0.1+0.2*amt);
        //release();
    }
    public static void stopDownward() {
        downward.set(0.0);
        tinyGrab();
    }
    public static void modGrab(double amt) {
        grabber.set(0.5+0.4*amt);
   
    }
    public static void modRelease(double amt) {
        grabber.set(-0.2-0.2*amt);
        
    }
    public static void grab() {
        grabber.set(0.5);
   
    }
    public static void release() {
        grabber.set(-0.2);
        
    }
    public static void stopGrab() {
        grabber.set(0);
        
    }
    public static void smallGrab() {
        grabber.set(0.2);
    }
    public static void tinyGrab() {
        grabber.set(0.02);
    }
    /*
    public static void stopElevator() {
        leftMotor.set(0.05);
        rightMotor.set(-0.05);
    }
    public static void lowerElevator() {
        leftMotor.set(-0.1);
        rightMotor.set(0.1);
    }
    */
}

