package frc.robot.Subsystems;

public class RobotMap {
    //
    public static final int FRONT_LEFT_MODULE_ID = 24;
    public static final int FRONT_RIGHT_MODULE_ID = 1;
    public static final int REAR_RIGHT_MODULE_ID = 2;
    public static final int REAR_LEFT_MODULE_ID = 3;
    //
    
    public static final double LENS_HEIGHT_INCHES = 8.5;
    public static final double MOUNTING_ANGLE = 33;
    
    public static final double kTurnP = 0;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;
    public static final double kMaxTurnAccelerationDegPerSSquared = 0;
    public static final double ARM_TOLERANCE = 2;
    public static final double kMaxTurnRateDegPerS = 0;
    public static final double kTurnToleranceDeg = 0;
    public static final double kTurnRateToleranceDegPerS = 0;

    public static final double ARM_SPEED = 0.2;
    public static final int DESIRED_PICTURE_HEIGHT = 640;
    public static final int DESIRED_PICTURE_WIDTH = 360;
    public static final int DESIRED_FRAMES_PER_SECOND = 60;

    public static final int RIGHT_REAR_DRIVE = 3;
    public static final int LEFT_FRONT_DRIVE = 7;
    public static final int RIGHT_FRONT_DRIVE = 4;
    public static final int LEFT_REAR_DRIVE = 9;
    public static final int RIGHT_SHOOTER = 1;
    public static final int LEFT_SHOOTER = 5;
    public static final int RIGHT_ARM = 6;
    public static final int LEFT_ARM = 2;
    public static final int INTAKE = 8;
    
    public static final int DRIVER_CONTROLLER1 = 0;
    public static final int DRIVER_CONTROLLER2 = 1;
    
    public static final int CAMERA_PORT = 0;
    public static final int LED_PORT = 0;

    public static final int LEFT_STICK_Y = 1;
    public static final int RIGHT_STICK_Y = 5;
    public static final int LEFT_STICK_X = 0;
    public static final int RIGHT_TRIGGER = 3;
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_STICK_X = 4;

    public static final int RIGHT_BUMPER = 6;
    public static final int LEFT_BUMPER = 5;
    public static final int Y_BUTTON = 4;
    public static final int X_BUTTON = 3;
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int LEFT_STICK_BUTTON = 9;
    public static final int RIGHT_STICK_BUTTON = 10;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;

    public static final int TANK_DRIVE_DRIVING = 1;
    public static final int DOUBLE_TANK_DRIVING = 2;
    public static final int VIDEO_GAME_DRIVING = 3;
    public static final int DOUBLE_VIDEO_GAME_DRIVING = 4;

    public static final double ARM_FLOOR = 0;
    public static final double ARM_CEILING = 45;
    public static final double RED_FLOOR = 0;
    public static final double RED_CEILING = 0;
    public static final double GREEN_FLOOR = 0;
    public static final double GREEN_CEILING = 0;
    public static final double BLUE_CEILING = 0;
    public static final double BLUE_FLOOR = 0;

    public static final double RAINBOW_PALETTE = -0.99;
    public static final double RAINBOW_PARTY_PALETTE = -0.97;
    public static final double RAINBOW_OCEAN_PALETTE = -0.95;
    public static final double RAINBOW_LAVE_PALETTE = -0.93;
    public static final double RAINBOW_FOREST_PALETTE = -0.91;
    public static final double RAINBOW_GLITTER = -0.89;
    public static final double CONFETTI = -0.87;
    public static final double SHOT_RED = -0.85;
    public static final double SHOT_BLUE = -0.83;
    public static final double SHOT_WHITE = -0.81;
    public static final double SINELON_RAINBOW = -0.79;
    public static final double SINELON_PARTY = -0.77;
    public static final double SINELON_OCEAN = -0.75;
    public static final double SINELON_LAVA = -0.73;
    public static final double SINELON_FOREST = -0.71;
    public static final double BEATS_RAINBOW = -0.69;
    public static final double BEATS_PARTY = -0.67;
    public static final double BEATS_OCEAN = -0.65;
    public static final double BEATS_LAVA = -0.63;
    public static final double BEATS_FOREST = -0.61;
    public static final double FIRE_MEDIUM = -0.59;
    public static final double FIRE_LARGE = -0.57;
    public static final double TWINKLES_RAINBOW = -0.55;
    public static final double TWINKLES_PARTY = -0.53;
    public static final double TWINKLES_OCEAN = -0.51;
    public static final double TWINKLES_LAVA = -0.49;
    public static final double TWINKLES_FOREST = -0.47;
    public static final double WAVES_RAINBOW = -0.45;
    public static final double WAVES_PARTY = -0.43;
    public static final double WAVES_OCEAN = -0.41;
    public static final double WAVES_LAVA = -0.39;
    public static final double WAVES_FOREST = -0.37;
    public static final double LARSON_RED = -0.35;
    public static final double LARSON_GRAY = -0.33;
    public static final double CHASE_RED = -0.31;
    public static final double CHASE_BLUE = -0.29;
    public static final double CHASE_GRAY = -0.27;
    public static final double HEARTBEAT_RED = -0.25;
    public static final double HEARTBEAT_BLUE = -0.23;
    public static final double HEARTBEAT_WHITE = -0.21;
    public static final double HEARTBEAT_GRAY = -0.19;
    public static final double BREATH_RED = -0.17;
    public static final double BREATH_BLUE = -0.15;
    public static final double BREATH_GRAY = -0.13;
    public static final double STROBE_RED = -0.11;
    public static final double STROBE_BLUE = -0.09;
    public static final double STROBE_GOLD = -0.07;
    public static final double STROBE_WHITE = -0.05;
    public static final double COLOR1_BLEND_TO_BLACK = -0.03;
    public static final double COLOR1_LARSON_SCANNER = -0.01;
    public static final double COLOR1_LIGHT_CHASE = 0.01;
    public static final double COLOR1_HEARTBEAT_SLOW = 0.03;
    public static final double COLOR1_HEARTBEAT_MEDIUM = 0.05;
    public static final double COLOR1_HEARTBEAT_FAST = 0.07;
    public static final double COLOR1_BREATH_SLOW = 0.09;
    public static final double COLOR1_BREATH_FAST = 0.11;
    public static final double COLOR1_SHOT = 0.13;
    public static final double COLOR1_STROBE = 0.15;
    public static final double COLOR2_BLEND_TO_BLACK = 0.17;
    public static final double COLOR2_LARSON_SCANNER = 0.19;
    public static final double COLOR2_LIGHT_CHASE = 0.21;
    public static final double COLOR2_HEARTBEAT_SLOW = 0.23;
    public static final double COLOR2_HEARTBEAT_MEDIUM = 0.25;
    public static final double COLOR2_HEARTBEAT_FAST = 0.27;
    public static final double COLOR2_BREATH_SLOW = 0.29;
    public static final double COLOR2_BREATH_FAST = 0.31;
    public static final double COLOR2_SHOT = 0.33;
    public static final double COLOR2_STROBE = 0.35;
    public static final double SPARKLE_COLOR1_ON_COLOR2 = 0.37;
    public static final double SPARKLE_COLOR2_ON_COLOR1 = 0.39;
    public static final double GRADIENT_COLOR1_AND_COLOR2 = 0.41;
    public static final double BPM_COLOR1_AND_COLOR2 = 0.43;
    public static final double BLEND_COLOR1_TO_COLOR2 = 0.45;
    public static final double BLEND_COLOR2_TO_COLOR1 = 0.47;
    public static final double COLOR1_AND_COLOR2 = 0.49;
    public static final double TWINKLES_COLOR1_AND_COLOR2 = 0.51;
    public static final double WAVES_COLOR1_AND_COLOR2 = 0.53;
    public static final double SINELON_COLOR1_AND_COLOR2 = 0.55;
    public static final double HOT_PINK = 0.57;
    public static final double DARK_RED = 0.59;
    public static final double RED = 0.61;
    public static final double RED_ORANGE = 0.63;
    public static final double ORANGE = 0.65;
    public static final double GOLD = 0.67;
    public static final double YELLOW = 0.69;
    public static final double LAWN_GREEN = 0.71;
    public static final double LIME = 0.73;
    public static final double DARK_GREEN = 0.75;
    public static final double GREEN = 0.77;
    public static final double BLUE_GREEN = 0.79;
    public static final double AQUA = 0.81;
    public static final double SKY_BLUE = 0.83;
    public static final double DARK_BLUE = 0.85;
    public static final double BLUE = 0.87;
    public static final double BLUE_VIOLET = 0.89;
    public static final double VIOLET = 0.91;
    public static final double WHITE = 0.93;
    public static final double GRAY = 0.95;
    public static final double DARK_GRAY = 0.97;
    public static final double BLACK = 0.99;

    public static final Integer LEFT_AUTON = 1;
    public static final Integer RIGHT_AUTON = 3;

}