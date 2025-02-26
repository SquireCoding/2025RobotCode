package frc.robot.Subsystems;


public class Motor {
    
    /** 
     * @param motor
     * @return String
     */
    public String toString(M motor) {
        if (motor==M.FRONTRIGHT) {
            return "Front Right";
        }
        if (motor==M.FRONTLEFT) {
            return "Front Left";
        }
        if (motor==M.BACKRIGHT) {
            return "Back Right";
        }
        if (motor==M.BACKLEFT) {
            return "Back Left";
        }
        return "null";
    }
}
