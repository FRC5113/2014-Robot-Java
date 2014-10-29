 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author MHS
 */
public class Drive {

    int frontLeft = 6;  //motor numbers
    int frontRight = 3;
    int backLeft = 5;
    int backRight = 4;

    int rightEncoder1 = 1;
    int rightEncoder2 = 2;
    Encoder rightEncoder = new Encoder(rightEncoder1, rightEncoder2);

    int leftEncoder1 = 3;
    int leftEncoder2 = 4;
    Encoder leftEncoder = new Encoder(leftEncoder1, leftEncoder2);

    private RobotDrive drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

    boolean useTankDrive = true;

    private double speedDefault = 0.6;
    private double speedHigh = 0.85;
    private double speed100 = 1.0;
    private double speedMin = 0.44;

    private double speedCurrentL = 0;
    private double speedTargetL = 0;
    private double speedCurrentR = 0;
    private double speedTargetR = 0;

    private double maxOutput = 0;

    public static boolean hardStopsEnabled = true;

    public Drive() {
        rightEncoder.start();
        leftEncoder.start();
        rightEncoder.reset();
        leftEncoder.reset();

        SmartDashboard.putNumber("LeftEncoderGet", leftEncoder.get());
        SmartDashboard.putNumber("LeftEncoderGetDistance", leftEncoder.getDistance());
        SmartDashboard.putNumber("LeftEncoderGetRaw", leftEncoder.getRaw());

        SmartDashboard.putNumber("RightEncoderGet", rightEncoder.get());
        SmartDashboard.putNumber("RightEncoderGetDistance", rightEncoder.getDistance());
        SmartDashboard.putNumber("RightEncoderGetRaw", rightEncoder.getRaw());
        SmartDashboard.putNumber("Default Speed", speedDefault);

    }

    private double getIncrement() {
        double inc = 0;
        inc = 0.0045 * (Math.abs(speedTargetL - speedTargetR) * Math.abs(speedTargetL - speedTargetR) * 2 + 1) / maxOutput;
        //Divide by maxOutput so that at min current output it ramps up faster because it is
        //At less risk for tipping, and also so it ramps up at a same real world SPEED, as in m/s.
        //Do less than one because it is from -1 to 1
        return inc;
    }

    /*
     Similar to pow, but only uses integer exponents and
     keeps negative inputs negative.

     Two joysticks are used, one for each set of wheels.
     */
    private void tankDrive(Joystick rightStick, Joystick leftStick) {
        double right = rightStick.getY();
        double left = leftStick.getY();

        left *= 0.99;
        right *= 0.99;

        drive.tankDrive(left, right);
    }

    /*
     Controls simple player control, speed control, motor safety, etc.
     */
    public void speedControl(boolean useSpeedMax, boolean useSpeedMin, boolean useSpeedHigh) {
        drive.setSafetyEnabled(true);

        if (Robot5113.isEmergencyStopped == false) {
            //Set wheel speed
            if (useSpeedMin) {
                maxOutput = speedHigh;
            }//end if 
            else if (useSpeedHigh) {
                maxOutput = speedMin;
            }//end else/if
            else if (useSpeedMax) {
                maxOutput = speed100;
            }//end else/else
            else {
                maxOutput = speedDefault;
            }

        }//end if 
        else {
            maxOutput = 0;
        }//end else

        //If going backwards, set the max speed to the default speed whenever going higher
        //Prevents tipping
        if (speedTargetL > 0 && speedTargetR > 0) {
            if (maxOutput > 0.43f) {
                maxOutput = 0.43f;
            }
            if (!Robot5113.arm.limitSwitchLower.get()) {
                hardStopsEnabled = true;
            } else {
                hardStopsEnabled = false;
            }
        } else {
            hardStopsEnabled = true;
        }

    }

    private void speedRamp() {
        speedTargetR = Robot5113.driveSticks.getRightJoystick().getY() * maxOutput;
        speedTargetL = Robot5113.driveSticks.getLeftJoystick().getY() * maxOutput;
        rampLeft();
        rampRight();
    }

    private void rampLeft() {
        //if ((int) (speedCurrentL * 100) == (int) (speedTargetL * 100)) {
        //If same percentage speed, with a +- 0.99% inaccuracy.
        //} else {
        if (speedCurrentL < speedTargetL) {
            speedCurrentL += getIncrement();
        } else {
            speedCurrentL -= getIncrement();
        }
        //}
    }

    private void rampRight() {
        //if ((int) (speedCurrentR * 100) == (int) (speedTargetR * 100)) {
        //If same percentage speed, with a +- 0.99% inaccuracy.
        //} else {
        if (speedCurrentR < speedTargetR) {
            speedCurrentR += getIncrement();
        } else {
            speedCurrentR -= getIncrement();
        }
        //}
    }

    public void useMotors() {
        if (!hardStopsEnabled) {
            speedRamp();
            drive.tankDrive(speedCurrentL, speedCurrentR);
        } else {

            double right = Robot5113.driveSticks.getRightJoystick().getY();
            double left = Robot5113.driveSticks.getLeftJoystick().getY();

            left *= maxOutput * 0.99f;
            right *= maxOutput * 0.99f;

            drive.tankDrive(left, right);
        }
    }

    public void simpleDrive(double left, double right) {
        drive.setMaxOutput(1.0);
        drive.tankDrive(left, right);
    }
}
