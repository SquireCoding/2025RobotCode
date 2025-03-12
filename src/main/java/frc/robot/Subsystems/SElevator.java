package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import static edu.wpi.first.units.Units.Degree;


public class SElevator {
    public static final SparkMax leftMotor = new SparkMax(31, MotorType.kBrushless);
    public static final SparkMax rightMotor = new SparkMax(32, MotorType.kBrushless);
    public SElevator() {
        leftMotor.getEncoder().setPosition(0);
        rightMotor.getEncoder().setPosition(0);
    }
    public static void liftElevator() {
        leftMotor.set(0.2);
        rightMotor.set(-0.2);
    }
    public static void stopElevator() {
        leftMotor.set(0.05);
        rightMotor.set(-0.05);
    }
    public static void lowerElevator() {
        leftMotor.set(-0.1);
        rightMotor.set(0.1);
    }
}
