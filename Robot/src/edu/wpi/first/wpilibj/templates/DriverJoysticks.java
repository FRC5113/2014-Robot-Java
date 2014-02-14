/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author MHS
 */
public class DriverJoysticks {
    
    Joystick rightStick;
    Joystick leftStick;
   
    Button turboRight;
    Button turboLeft;
    Button stopRight;
    Button stopLeft;
    double speedDefault;
    double speedMax;
    double speedMin;
    
    public DriverJoysticks(int right, int left) {
        

        turboRight = new JoystickButton(rightStick, 3);
        turboLeft = new JoystickButton(leftStick, 3);
        stopRight = new JoystickButton(rightStick, 6);
        stopLeft = new JoystickButton(leftStick, 6);
        SmartDashboard.putNumber("speedMax", speedMax);
        SmartDashboard.putNumber("speedMin", speedMin);
        SmartDashboard.putNumber("speedDefault", speedDefault);
        
        rightStick = new Joystick(right);
        leftStick = new Joystick(left);
    }
    
    public void update() {
        
        //Do speed control calling tomorrow/ whenever we meet next :)
        
    }
    
}
