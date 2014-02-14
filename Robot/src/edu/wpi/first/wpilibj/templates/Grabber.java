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
public class Grabber {

    private RobotDrive drive = new RobotDrive(1, 2);
    
    int direction;
    float speed = 0.95f;
    
    
    public void takeInput(int direction) {
        this.direction = direction;
    }

    public void update() {
        drive.setSafetyEnabled(false);
        
        
        //Set wheel speed
        if (direction > 0) {
            drive.tankDrive(speed, -speed);
        } else if (direction < 0) {
            drive.tankDrive(-speed, speed);
        } else {
            //stopped
            drive.tankDrive(0, 0);
        }
        
        direction = 0;
                

    }
}
