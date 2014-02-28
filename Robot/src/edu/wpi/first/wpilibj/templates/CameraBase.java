/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author MHS
 */
public class CameraBase {

    int xOut = 9;       //the digital I/O ports
    int yOut = 8;       //yup still digital I/O ports

    float x = 0.5f;     //This is gonna be the starting point for the camera
    float y = 0.5f;     //values range from 0.0 to 1.0 so starting in the middle is cool
    float add = 0.005f; //when we adjust the camera, we add this number to x or y on every update()

    Servo servoX = new Servo(xOut);     //those motor things on the camera are called Servos apparantly
    Servo servoY = new Servo(yOut);

    Joystick js = RobotTemplate.monitor.getJoystick();  //this finds out which button we are using to control the camera

    //this is the method that is called in RobotTemplate in the operatorControl() method
    //and is called about every 0.005s 
    public void update() {
        if(Math.abs(js.getX()) > 0.05) 
            x += js.getX() * add;   // this adjusts the values of x and y
        if(Math.abs(js.getY()) > 0.05)
            y -= js.getY() * add;   //x is += cus its wired correctly, y is -= cus its wired backwards on the robot

        //this part just makes sure we don't go over 1.0 or under 0.0 on the servos
        if (x > 1) 
        {
            x = 1;
        }//end "x" if
        else if (x < 0) 
             {
                x = 0;
             }//end "x" else/if
        
        //now adjusting the y value
        if (y > 1) 
        {
            y = 1;
        }//end "y" if 
        else if (y < 0)
             {
               y = 0;
             }//end "y" else/if
        
        //puts those new values into the servos and moves the camera :D
        servoX.set(x);
        servoY.set(y);

    }//end update()
}//end class CameraBase
