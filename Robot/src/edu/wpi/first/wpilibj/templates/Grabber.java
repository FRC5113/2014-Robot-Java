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
    private float speed = 0.95f;    //speed of the 

    
    public Grabber() {
        SmartDashboard.putNumber("grabberSpeed", speed);        //code for SmartDashboard which we still havent gotten to work
    }
    
    public void takeInput(int direction) {
        //DriverJoysticks may have brought you here.
        //Otherwise go to DriverJoysticks.update() and find the line that calls this method
        //this sets the object variable direction to whatever the joystick input was.
        //I think you can just get rid of this and check the joystick input in Grabber.update(), 
        //but I'm not bold enough to change the code without being able to test the robot.
        this.direction = direction;
    }

    public void update() {
        drive.setSafetyEnabled(false);          //guess we arent safe now. not sure why we have to do this but hey it doesnt
        //the if below tests to see if emergency stop is enabled.
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
