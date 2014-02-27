/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
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

    private double speedDefault = 0.5;
    private double speedMax = 0.7;
    private double speedMin = 0.25;

    private boolean isEmergencyStopped = false;

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

    /*
     Similar to pow, but only uses integer exponents and
     keeps negative inputs negative.

     Two joysticks are used, one for each set of wheels.
     */
    private void tankDrive(Joystick rightStick, Joystick leftStick) {
        double right = rightStick.getY();
        double left = leftStick.getY();

        left *= 0.95;
        right *= 0.95;

        drive.tankDrive(left, right);
    }

    /*
     Controls simple player control, speed control, motor safety, etc.
     */
    public void speedControl(boolean useSpeedMax, boolean useSpeedMin) {
        drive.setSafetyEnabled(true);

        if (isEmergencyStopped == false) {
            //Set wheel speed
            if (useSpeedMin) 
            {
                drive.setMaxOutput(speedMin);
            }//end if 
            else if (useSpeedMax) 
                 {
                   drive.setMaxOutput(speedMax);
                 }//end else/if
                 else 
                 {
                   drive.setMaxOutput(speedDefault);
                 }//end else/else

        }//end if 
        else 
        {
            drive.setMaxOutput(0.0);
            isEmergencyStopped = false;
        }//end else
    }
    /*
     Stops motors from moving, but does not "Disable".
     */

    public void emergencyStop() 
    {
        isEmergencyStopped = true;
    }

    public void useMotors() {
        tankDrive(RobotTemplate.driveSticks.getRightJoystick(), RobotTemplate.driveSticks.getLeftJoystick());
    }    
    public void simpleDrive(float left, float right) {
        drive.setMaxOutput(1.0);
        drive.tankDrive(left, right);
    }

}
