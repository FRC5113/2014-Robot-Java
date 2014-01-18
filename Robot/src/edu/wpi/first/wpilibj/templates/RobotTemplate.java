/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
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
    Joystick rightStick = new Joystick(1);
    Joystick leftStick = new Joystick(2);
    Wheels wheels = new Wheels();
    
    int msPerTick = 5;
    long lastTick = 0;
    

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled())
        {           
            
            //Because of the large amount of calculations and relying on mechanical parts,
            //We cannot delay for a specific, set amount of time for each time the while loop runs.
            //Instead, we make sure that the amount of time passed has been great enough.
            if(System.currentTimeMillis() - lastTick >= msPerTick) {              
            
                lastTick = System.currentTimeMillis();
                
                
                wheels.idleLogic(rightStick, leftStick);
            
            }
        }
    }
}