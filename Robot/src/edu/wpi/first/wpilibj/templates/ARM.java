/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * Aerial Reaching Mechanism
 * The ARM. 
 *
 * @author MHS
 */
public class ARM {

    float deadzone = 0.08f;
    DigitalInput limitSwitchUpper;
    DigitalInput limitSwitchLower;

    Talon wheels;
    Talon arm;

    public void setup() {
        limitSwitchUpper = new DigitalInput(9);
        limitSwitchLower = new DigitalInput(8);

        wheels = new Talon(8);
        arm = new Talon(7);
        System.out.println("I'm ALIVE!!!");
    }

    public void moveArm(float in) {

        float speed = in;

        if (limitSwitchUpper.get() && speed > 0) {
            speed = 0;
        }
        if (limitSwitchLower.get() && speed < 0) {
            speed = 0;
        }
        if (Robot5113.isEmergencyStopped) {
            speed = 0;
        }
        arm.set(speed);
    }

    public void moveWheels(float speed) {

        if (Robot5113.isEmergencyStopped == false && !Robot5113.monitor.enableCameraMovement.get()) {
            wheels.set(speed);
        } else {
            wheels.set(0);
        }
    }

    public void update() {
        if (Robot5113.driveSticks.grabberInLeft.get()) {
            moveWheels(-1);
        } else if (Robot5113.driveSticks.grabberOutRight.get()) {
            moveWheels(1);
        } else {
            moveWheels(0);
        }

        //If no camera movement enabled, use the joystick movement as arm movement.
        if (!Robot5113.monitor.enableCameraMovement.get()) {
            Joystick js = Robot5113.monitor.getJoystick();
            if (Math.abs(js.getY()) > deadzone) {
                moveArm((float) -js.getY());
            } else {
                moveArm(0);
            }
        }
         else {
                moveArm(0);
            }
    }
}