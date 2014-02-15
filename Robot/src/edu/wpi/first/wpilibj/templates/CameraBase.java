/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author MHS
 */
public class CameraBase {
    
    int x = 8;
    int y = 9;
    Servo servoX = new Servo(x);
    Servo servoY = new Servo(y);

    Joystick js;// = RobotTemplate.monitor.getJoystick();    
    
    
    public void update() {
        //servoX.startLiveWindowMode();
        System.out.println("works?");
        System.out.println(js.getX() + ", " + js.getY() + ", " + servoX.getRaw());
        servoX.set(Math.abs(js.getX()));
        servoY.set(Math.abs(js.getY()));

        //servoX.updateTable();
        
        
    }
    
}
