/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    RobotDrive drive = new RobotDrive(1, 2);
    Joystick rightStick = new Joystick(1);
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (true && isOperatorControl() && isEnabled())
        {
                        drive.setMaxOutput(0.3f);

            
            //drive.setSafetyEnabled(true);
            if(rightStick.getTrigger()) {
                drive.setMaxOutput(1f);
            }
            if(rightStick.getTop()) {
                drive.setMaxOutput(0.25f);
            }
                        /*
            if(rightStick.getX() == 0 && rightStick.getY() == 0) {
                drive.setMaxOutput(0);
                drive.stopMotor();
            }
                    
                    */
            //drive.arcadeDrive(pow(-rightStick.getY(), 2), pow(-rightStick.getX(), 2));
            drive(true, true);
//drive.arcadeDrive(rightStick, true); // drive with joysticks
            Timer.delay(0.005);
        }
    }
    
    private double pow(double a, int b) {
        double f = a;
        for(int c = 0; c < b; c++) {
            f *= f;
        }
        if(a < 0) {
            f = -f;
        }
        return f;
    }
    
    private void drive(boolean squareForwards, boolean proportional) {
        //double x = rightStick.getX();
        double y = rightStick.getY();
        
        if(squareForwards) {
            y = -pow(y, 2);
        }
        
        double right;
        double left;
        
        right = y;
        left = y;
        
        //right += x;
        //left -= x;
        
        //If proportional, we treat the joystick as a virtual circle and use proportions instead of regular x and y.
        if(proportional) {
            if(right > 1) {
                left *= (1 / right);
                right = 1;
            }
            else if(right < -1) {
                left *= (-1 / right);
                right = -1;
            }
            
            if(left > 1) {
                right *= (1 / left);
                left = 1;
            }
            else if(left < -1) {
                right *= (-1 / left);
                left = -1;
            }
        }
        
        //Prevent motor from doing more than 100%.
        if(left > 1) {
            left = 1;
        }
        if(left < -1) {
            left = -1;
        }
        if(right > 1) {
            right = 1;
        }
        if(right < -1) {
            right = -1;
        }
        
        //Prevent motor from doing 100%.
        right *= 0.99f;
        left *= 0.99f;
        
        
            
        drive.tankDrive(right, left);
               
    }
}
