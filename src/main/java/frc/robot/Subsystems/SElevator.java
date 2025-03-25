package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Robot;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import static edu.wpi.first.units.Units.Degree;


public class SElevator {
    public static final SparkMax leftMotor = new SparkMax(31, MotorType.kBrushless);
    public static final SparkMax rightMotor = new SparkMax(32, MotorType.kBrushless);
    public static double inc = 0.4;
    public static double des = 0.0;
    public static double lastDes =0.0;
    public SElevator() {
        leftMotor.getEncoder().setPosition(0);
        rightMotor.getEncoder().setPosition(0);
        lastDes=-1;
    }
    public static void liftElevator(double amount) {
        leftMotor.set(0.3+0.5*amount);
        rightMotor.set(-0.3-0.5*amount);
    }
    public static void stopElevator() {
        leftMotor.set(0.05);
        rightMotor.set(-0.05);
    }
    public static void lowerElevator(double amount) {
        leftMotor.set(-0.1-0.5*amount);
        rightMotor.set(0.1+0.5*amount);
    }
    public static double getPos() {
        return leftMotor.getEncoder().getPosition();
    }
    public static void featherHeight(double de) {
        des=de;
        if (de!=lastDes) {
            inc=0.4;
            if (getPos()>de) inc=-0.4;
            lastDes=de;
        }
        System.out.println("Desired: "+des+"\nAct: "+getPos());
        if (!Robot.isWithin(leftMotor.getEncoder().getPosition(), des, 2.0)) {
            leftMotor.set(inc);
            rightMotor.set(-inc);
            inc=(Math.abs(des-getPos())*(0.5/15.0));
            System.out.println("Dif f(x): "+(Math.abs(des-getPos())*(0.6/15.0)));
            if (getPos()>des&&inc>0) {
                inc=-inc;
            } else if (getPos()<des&&inc<0) {
                System.out.println("Before: "+inc);
                
                inc=-inc;
                System.out.println("After: "+inc);
            }
        }
    }
    
}
