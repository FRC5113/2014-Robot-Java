/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

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

    private RobotDrive drive = new RobotDrive(3, 4);
    
    double speedDefault = 0.4;
    double speedMax = 0.7;
    double speedMin = 0.25;
    

    boolean useTankDrive = true;

    /*
     Similar to pow, but only uses integer exponents and
     keeps negative inputs negative.
     */
    private double powJoystick(double a, int b) {
        double f;
        f = Math.abs(a);
        for (int c = 1; c < b; c++) {
            f *= f;
        }
        if (a < 0) {
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
        
        SmartDashboard.putNumber("speedMax", speedMax);
        SmartDashboard.putNumber("speedMin", speedMin);
        SmartDashboard.putNumber("speedDefault", speedDefault);

        right = powJoystick(right, 1);
        left = powJoystick(left, 1);

        left *= -0.95;
        right *= -0.95;

        drive.tankDrive(left, right);
    }

   

    /*
     Controls simple player control, speed control, motor safety, etc.
     */
    public void update(Joystick rightStick, Joystick leftStick) {
        drive.setSafetyEnabled(true);
        
        Button turboRight = new JoystickButton(rightStick, 3);
        Button turboLeft = new JoystickButton(leftStick, 3);
        Button stopRight = new JoystickButton(rightStick, 6);
        Button stopLeft = new JoystickButton(leftStick, 6);
       
        //Set wheel speed
        if (rightStick.getTop() || leftStick.getTop()) {
            drive.setMaxOutput(speedMin);
        } else if (turboRight.get() || turboLeft.get()) {
            drive.setMaxOutput(speedMax);
        } else if (stopRight.get() && stopLeft.get())  {
            drive.setMaxOutput(0.0f);
        } else  
            drive.setMaxOutput(speedDefault);
         
              
    
        //Pick drive method
        if (useTankDrive) {
            tankDrive(rightStick, leftStick, true);
        } else {
            drive(rightStick, true, true);
        }

    }
    
    public void simpleDrive(float left, float right) {
        drive.tankDrive(left, right);
    }
    
    

}
