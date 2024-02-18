package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class Constants {

    public static final class CANDevices {
        // FIXME: Set these CAN ID values to the those of your robot, or change your CAN ID's to match this convention.

        public static final int imuId = 14;

        public static final int frontLeftCanCoderId = 10;
        public static final int frontLeftSteerMtrId = 8;
        public static final int frontLeftDriveMtrId = 9;

        public static final int frontRightCanCoderId = 11;
        public static final int frontRightSteerMtrId = 2;
        public static final int frontRightDriveMtrId = 3;

        public static final int backLeftCanCoderId = 12;
        public static final int backLeftSteerMtrId = 6;
        public static final int backLeftDriveMtrId = 7;

        public static final int backRightCanCoderId = 13;
        public static final int backRightSteerMtrId = 4;
        public static final int backRightDriveMtrId = 5;

        //Leader Mtr is Right Mtr and Follower Mtr is Left Mtr
        public static final int leaderRollerMtrId = 15;
        public static final int followerRollerMtrId = 16;

        //Leader Mtr is Right Mtr and Follower Mtr is Left Mtr
        public static final int leaderPivotMtrId = 19;
        public static final int followerPivotMtrId = 20;

        //Leader Mtr is Left Mtr and Follower Mtr is Right Mtr
        public static final int followerClimbMtrId = 17;
        public static final int leaderClimbMtrId = 18;
    }

    public static final class ControllerConstants {

        public static final int driverGamepadPort = 0;

        public static final int operatorGamepadPort = 1;

        public static final double joystickDeadband = 0.15;

        public static final double triggerPressedThreshhold = 0.25;

        public static final int driverRightJoystick = 1;
    }
    
    public static final class DriveConstants {
        /**
         * The track width from wheel center to wheel center.
         */
        // FIXME: Make sure to measure from the center of each wheel
        public static final double trackWidth = Units.inchesToMeters(19.6875);

        /**
         * The track length from wheel center to wheel center.
         */
        // FIXME: mature sure to measure from the center of each wheel
        public static final double wheelBase = Units.inchesToMeters(19.6875);

        /**
         * The SwerveDriveKinematics used for control and odometry.
         */
        public static final SwerveDriveKinematics kinematics = 
            new SwerveDriveKinematics(
                new Translation2d(trackWidth / 2.0, wheelBase / 2.0),  // front left
                new Translation2d(trackWidth / 2.0, -wheelBase / 2.0), // front right
                new Translation2d(-trackWidth / 2.0, wheelBase / 2.0), // back left
                new Translation2d(-trackWidth / 2.0, -wheelBase / 2.0) // back right
            );

        /**
         * The gear reduction from the drive motor to the wheel.
         * 
         * The drive gear ratios for the different levels can be found from the chart at
         * swervedrivespecialties.com/products/mk41-swerve-module.
         */
        // FIXME: This is the gear ratio for L3 modules.
        public static final double driveMtrGearReduction = (16.0 / 50.0) * (28.0 / 16.0) * (15.0 / 45.0);

        /**
         * The gear reduction from the steer motor to the wheel.
         */
        public static final double steerMtrGearReduction = (14.0 / 50.0) * (10.0 / 60.0);

        public static final double wheelRadiusMeters = Units.inchesToMeters(2);
        public static final double wheelCircumferenceMeters = 2.0 * wheelRadiusMeters * Math.PI;

        public static final double driveMetersPerEncRev = wheelCircumferenceMeters * driveMtrGearReduction;
        public static final double driveMetersPerSecPerRPM = driveMetersPerEncRev / 60.0;

        public static final double steerRadiansPerEncRev = 2 * Math.PI * DriveConstants.steerMtrGearReduction;

        public static final double kFreeMetersPerSecond = 5600 * driveMetersPerSecPerRPM;

        public static final double steerMtrMaxSpeedRadPerSec = 2.0;
        public static final double steerMtrMaxAccelRadPerSecSq = 1.0;

        public static final double maxDriveSpeedMetersPerSec = 15.0;

        /**
         * The rate the robot will spin with full Rot command.
         */
        public static final double maxTurnRateRadiansPerSec = 2.0 * Math.PI;

        // FIXME: Set line up the swerve modules and set these values.

        // The bolt heads should be pointing to the left. These values are subtracted from the CANCoder reading,
        // so they should be the raw CANCoder value when set straight. These values should be between 0 and 360
        // degrees.

        // FIXME: Don't quote me on that they should be pointing to the left. (I'm almost positive though.) If 
        // the drive base drives 180 off from the commanded direction, flip these readings 180 degrees and change
        // the comment above for future reference.
        public static final Rotation2d frontLeftModOffset = Rotation2d.fromDegrees(125.25); 
        public static final Rotation2d frontRightModOffset = Rotation2d.fromDegrees(215);
        public static final Rotation2d backLeftModOffset = Rotation2d.fromDegrees(146.5);
        public static final Rotation2d backRightModOffset = Rotation2d.fromDegrees(144.75); 

        // FIXME: You may want to change this value.
        public static final int driveCurrentLimitAmps = 80;
        
        // FIXME: These values should be fine, but if the modules start to rattle you may want to play with the steer PID values.
        public static final double drivekP = 0.005;
        public static final double drivekD = 0.0;

        public static final double steerkP = 0.37431;
        public static final double steerkD = 0.27186;

        public static final double ksVolts = 0.667;
        public static final double kvVoltSecsPerMeter = 2.44;
        public static final double kaVoltSecsPerMeterSq = 0.0;

        public static final SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(ksVolts, kvVoltSecsPerMeter, kaVoltSecsPerMeterSq);
    }

    public static final class AutoConstants {
        /**
         * The default maximum speed of the robot in auto. Can be overridden by the FollowTrajectoryCmd Command.
         */
        public static final double maxVelMetersPerSec = 3.25;

        // FIXME: These drive and rotation PID constants most likely need to be tuned for better accuracy.
        public static final double drivekP = 12.8;
        public static final double drivekD = 0.085;

        // public static final PIDConstants driveConstants = new PIDConstants(drivekD, drivekD);

        public static final double rotkP = 1.27;
        public static final double rotkD = 0.5;

        // public static final PIDConstants rotConstants = new PIDConstants(rotkP, rotkD);
    }

    public class RollerConstants {
    
        public static final int maxRollerCurrentAmps = 80;

        public static final double rollerFreeSpeedRPM = 6784.0;

        public static final double manualPower = 1.0;

        public static final double inPower = 1.0;

        public static final double outPower = 1.0;

        public static final double power = 1.0;
    }

    public class PivotConstants {

        public static final int maxPivotCurrentAmps = 40;

        public static final double pivotGearRatio = 45.0 / 1.0;

        public static final double KP = 0.01;
        public static final double KD = 0.01;

        public static final double pivotDegreesPerEncRev = 360.0 / pivotGearRatio;
        public static final double pivotDegPerSecPerRPM = 360.0 / (pivotGearRatio * 60.0);

        public static final double pivotFreeSpeedRPM = 6784.0 / pivotGearRatio;

        public static final double pivotMaxVelDegPerSec = 45.0;

        public static final double pivotMaxAccelDegPerSecSq = 90.0;
    }

    public class ClimberConstants {

        public static final int maxClimberCurrentAmps = 40;

        public static final Double climberPower = 0.42;

        public static final double inchesPerEncRev = 11;

        public static final double rightClimbGearReduction = 12.0 / 1.0;
        public static final double leftClimbGearReduction = 12.0 / 1.0;

        public static final float climberForwardLimit = 80;
        public static final float climberReverseLimit = 0;

        public static final double climberSpeedFactor = 1.0;

        public static final double climberRateLimit = 0.0;
        public static final double limitVariability = 0.1;
    }
}
