/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import  edu.wpi.first.wpilibj.buttons.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author MHS
 */
public class MonitorControl {
    
    Joystick js;
    
    JoystickButton grabberIn;
    int grabberInPos = 5;
    
    JoystickButton grabberOut;
    int grabberOutPos = 6;
    
    JoystickButton lifterUp;
    int lifterUpPos = 4;
    
    JoystickButton lifterDown;
    int lifterDownPos = 3;
    
    JoystickButton eStop;
    int eStopPos = 9;
    
    JoystickButton eDisable;
    int eDisablePos = 10;
    
    
    public MonitorControl(int input) {
        js = new Joystick(input);
        grabberIn = new JoystickButton(js, grabberInPos);
        grabberOut = new JoystickButton(js,grabberOutPos);
        lifterUp = new JoystickButton(js, lifterUpPos);
        lifterDown = new JoystickButton(js, lifterDownPos);
        eStop = new JoystickButton(js,eStopPos);
        eDisable = new JoystickButton(js, eDisablePos);
    }
    
    public void update() {
        if(grabberIn.get())
            RobotTemplate.grabber.takeInput(1);
        else if(grabberOut.get()) {
            RobotTemplate.grabber.takeInput(-1);
        }
        
        if(eStop.get())
            RobotTemplate.wheels.emergencyStop();
         
    }
    
}
