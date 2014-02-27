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

    private int pinGrabberLeft = 1;     //these are the motor numbers
    private int pinGrabberRight = 2;    //these are the motor numbers

    private RobotDrive drive = new RobotDrive(pinGrabberLeft, pinGrabberRight);

    private int direction;
    private float speed = 0.95f;    //speed is not 1.0 cus the joysticks sometimes input above 1.0

    
    public Grabber() {
        SmartDashboard.putNumber("grabberSpeed", speed);        //code for SmartDashboard which we still havent gotten to work
    }
    //update method nuff said
    public void update() {
        drive.setSafetyEnabled(false);          //if the robot stops recieving input, the motors will stop
        if(!RobotTemplate.driveSticks.getLeftJoystick().getRawButton(7) && !RobotTemplate.driveSticks.getRightJoystick().getRawButton(7)) {
        //the above if is the second emergency stop
            //Set wheel speed
           if (MonitorControl.grabberIn.get()) {
             drive.tankDrive(speed, speed);
           }
           else if (MonitorControl.grabberOut.get()) 
                {
                  drive.tankDrive(-speed, -speed);
                }
                else if (DriverJoysticks.grabberInLeft.get())
                {
                    drive.tankDrive(speed, speed);
                }//end if 
                else if (DriverJoysticks.grabberOutRight.get()) 
                     {
                        drive.tankDrive(-speed, -speed);
                     }//end else/if
                     else 
                       { //stopped
                         drive.tankDrive(0, 0);
                       }//end else/else
        }//end emergency if
        else {
            drive.tankDrive(0, 0);
        }//end else
    }//end update()
}//end class Grabber
