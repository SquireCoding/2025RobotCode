package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import static edu.wpi.first.units.Units.Degree;

public class SDrivetrain {
    
    public final SparkMax motorFrontRightTurn = new SparkMax(24, MotorType.kBrushless);
    public final SparkMax motorFrontRightDrive = new SparkMax(23, MotorType.kBrushless);
    public final SparkMax motorFrontLeftTurn = new SparkMax(22, MotorType.kBrushless);
    public final SparkMax motorFrontLeftDrive = new SparkMax(21, MotorType.kBrushless);
    public final SparkMax motorBackRightTurn = new SparkMax(28, MotorType.kBrushless);
    public final SparkMax motorBackRightDrive = new SparkMax(27, MotorType.kBrushless);
    public final SparkMax motorBackLeftTurn = new SparkMax(26, MotorType.kBrushless);
    public final SparkMax motorBackLeftDrive = new SparkMax(25, MotorType.kBrushless);
    public final CANcoder sensorFrontLeftDrive = new CANcoder(10);
    public final CANcoder sensorBackLeftDrive = new CANcoder(30);
    public final CANcoder sensorFrontRightDrive = new CANcoder(40);
    public final CANcoder sensorBackRightDrive = new CANcoder(20);
    public final Pigeon2 gyro = new Pigeon2(5,"can0");
    public static int lastDesired = -1;
    public static double frontRightLastDesired = 0.0;
    public static double frontLeftLastDesired = 0.0;
    public static double backLeftLastDesired = 0.0;
    public static double backRightLastDesired = 0.0;
    public final static double startingInc = 0.13;
    public static double fullRotation = -1;
    public static boolean backupRotation = false;


    public static double backLeftInc = startingInc;
    public static double backRightInc=startingInc;
    public static double frontRightInc=startingInc;
    public static double frontLeftInc=startingInc;
    public SDrivetrain() {
        backupRotation=false;
        System.out.println(gyro.getDeviceID());
        fullRotation=getRotation();
    }

    public void resetIncs() {
        backLeftInc=startingInc;
        backRightInc=startingInc;
        frontRightInc=startingInc;
        frontLeftInc=startingInc;
    }

    public void lowerVoltage() {
    }
    
    /** 
     * @param d
     */
    public void driveAmount(double d) {
        motorFrontRightDrive.set(d);
        motorBackRightDrive.set(d);
        motorFrontLeftDrive.set(d);
        motorBackLeftDrive.set(d);
    }
    public void turnAmount(double d) {
        motorFrontRightTurn.set(d);
        motorBackRightTurn.set(d);
        motorFrontLeftTurn.set(d);
        motorBackLeftTurn.set(d);
    }
    public double getAbsFrontLeftRotation() {
        double val = sensorFrontLeftDrive.getAbsolutePosition(true).getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getFrontLeftRotation() {
        double val = sensorFrontLeftDrive.getPosition().getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getAbsBackLeftRotation() {
        double val = sensorBackLeftDrive.getAbsolutePosition(true).getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getBackLeftRotation() {
        double val = sensorBackLeftDrive.getPosition().getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getAbsFrontRightRotation() {
        double val = sensorFrontRightDrive.getAbsolutePosition(true).getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getFrontRightRotation() {
        double val = sensorFrontRightDrive.getPosition().getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getAbsBackRightRotation() {
        double val = sensorBackRightDrive.getAbsolutePosition(true).getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getBackRightRotation() {
        double val = sensorBackRightDrive.getPosition().getValue().in(Degree)+180;
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public void zeroSensors() {
        sensorBackLeftDrive.setPosition(0);
        sensorBackRightDrive.setPosition(0);
        sensorFrontRightDrive.setPosition(0);
        sensorFrontLeftDrive.setPosition(0);
    }
    public double getYaw() {
        return gyro.getYaw().getValueAsDouble();
    }
    public double getRotation() {
        double val = getYaw();
        while (val>360) val-=360;
        while (val<0) val+=360;
        return (val);
    }
    public double getYawSpeed() {
        return gyro.getAngularVelocityZWorld().getValueAsDouble();
    }
    public void rotate() {

    }
    public void stopDrive() {
        driveAmount(0);
    }
    public void driveCertain(M motor, double d) {
        if (motor==M.FRONTRIGHT||motor==M.FRONT||motor==M.RIGHT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTBACKLEFT||motor==M.NOTBACKRIGHT) {
            motorFrontRightDrive.set(d);
        }
        if (motor==M.FRONTLEFT||motor==M.FRONT||motor==M.LEFT||motor==M.ALL||motor==M.NOTFRONTRIGHT||motor==M.NOTBACKLEFT||motor==M.NOTBACKRIGHT) {
            motorFrontLeftDrive.set(d);
        }
        if (motor==M.BACKRIGHT||motor==M.BACK||motor==M.RIGHT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTBACKLEFT||motor==M.NOTFRONTRIGHT) {
            motorBackRightDrive.set(d);
        }
        if (motor==M.BACKLEFT||motor==M.BACK||motor==M.LEFT||motor==M.ALL||motor==M.NOTFRONTLEFT||motor==M.NOTFRONTRIGHT||motor==M.NOTBACKRIGHT) {
            motorBackLeftDrive.set(d);
        }
        
        
        
    }
}
