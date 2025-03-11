package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import static edu.wpi.first.units.Units.Degree;

public class SCoral {
    public static final SparkMax leftMotor = new SparkMax(34, MotorType.kBrushless);
    public static final SparkMax rightMotor = new SparkMax(36, MotorType.kBrushless);
    public SCoral() {
        
    }
    public static void stopElevator() {
        leftMotor.set(0);
        rightMotor.set(0);
    }
    public static void lowerElevator() {
        leftMotor.set(-0.1);
        rightMotor.set(0.1);
    }
}
