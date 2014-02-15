/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author MHS
 */
public class Grabber {

    private int pinGrabberLeft = 1;
    private int pinGrabberRight = 2;

    private RobotDrive drive = new RobotDrive(pinGrabberLeft, pinGrabberRight);

    private int direction;
    private float speed = 0.95f;

    
    public Grabber() {
        SmartDashboard.putNumber("grabberSpeed", speed);
    }
    
    public void takeInput(int direction) {
        this.direction = direction;
    }

    public void update() {
        drive.setSafetyEnabled(false);

        if(!RobotTemplate.driveSticks.getLeftJoystick().getRawButton(7) && !RobotTemplate.driveSticks.getRightJoystick().getRawButton(7)) {

            //Set wheel speed
            if (direction > 0) {
                drive.tankDrive(speed, speed);
            } else if (direction < 0) {
                drive.tankDrive(-speed, -speed);
            } else {
                //stopped
                drive.tankDrive(0, 0);
            }
        
        }
        else {
            drive.tankDrive(0, 0);
        }

        direction = 0;

    }
}
