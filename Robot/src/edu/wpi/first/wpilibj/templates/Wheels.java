/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author MHS
 */
public class Wheels {
     private RobotDrive drive = new RobotDrive(1, 2);
     
     boolean useTankDrive = false;

    /*
     Similar to pow, but only uses integer exponents and
     keeps negative inputs negative.
     */
     private double powJoystick(double a, int b) {
        double f;
        f = Math.abs(a);
        for(int c = 1; c < b; c++) {
            f *= f;
        }
        if(a < 0) {
            f = -f;
        }
        return f;
    }
    
     /*
     Two joysticks are used, one for each set of wheels.
     */
    private void tankDrive(Joystick rightStick, Joystick leftStick, boolean squareForwards) {
        double right = rightStick.getY();
        double left = leftStick.getY();
        
        right = powJoystick(right, 3);
        left = powJoystick(left, 3);
        
        left *= 0.95;
        right *= 0.95;
        
        drive.tankDrive(left, right);
    }
    
    /*
    "Arcade Drive", where only one joystick is used.
    */
    public void drive(Joystick rightStick, boolean squareForwards, boolean proportional) {
        double x = rightStick.getX();
        double y = rightStick.getY();
        
        if(squareForwards) {
            y = -powJoystick(y, 3);
        }
        
        double right;
        double left;
        
        right = y;
        left = y;
        
        right += x;
        left -= x;
        
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
    
    /*
    Controls simple player control, speed control, motor safety, etc.
    */
    public void idleLogic(Joystick rightStick, Joystick leftStick) {            
            drive.setSafetyEnabled(true);

            //Set wheel speed
            if(rightStick.getTop() || leftStick.getTop()) {
                drive.setMaxOutput(0.25f);
            }
            else if(rightStick.getTrigger() || leftStick.getTrigger()) {
                drive.setMaxOutput(0.7f);        
            }
            else {
                drive.setMaxOutput(0.4f);
            }
                   
            
            //Pick drive method
            if(useTankDrive) {
                tankDrive(rightStick, leftStick, true);
            }
            else {
                drive(rightStick, true, true);
            }

            
    }
    
}