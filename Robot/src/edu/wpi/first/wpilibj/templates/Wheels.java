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
     RobotDrive drive = new RobotDrive(1, 2);

    
     private double powJoystick(double a, int b) {
        double f = a;
        for(int c = 1; c < b; c++) {
            f *= f;
        }
        if(a < 0) {
            f = -f;
        }
        return f;
    }
    
    private void tankDrive(Joystick rightStick, Joystick leftStick, boolean squareForwards) {
        double right = rightStick.getY();
        double left = leftStick.getY();
        
        right = powJoystick(right, 3);
        left = powJoystick(left, 3);
        
        left *= 0.95;
        right *= 0.95;
        
        drive.tankDrive(left, right);
    }
    
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
    
    public void idleLogic(Joystick rightStick, Joystick leftStick) {
                 drive.setMaxOutput(0.4f);

            
            //drive.setSafetyEnabled(true);
            if(rightStick.getTrigger() || leftStick.getTrigger()) {
                drive.setMaxOutput(0.7f);
            }
            if(rightStick.getTop() || leftStick.getTop()) {
                drive.setMaxOutput(0.25f);
            }
                        /*
            if(rightStick.getX() == 0 && rightStick.getY() == 0) {
                drive.setMaxOutput(0);
                drive.stopMotor();
            }
                    
                    */
            
             drive(rightStick, true, true);

            
    }
    
}