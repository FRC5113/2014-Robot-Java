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

    int xOut = 8;
    int yOut = 9;

    float x;
    float y;
    float add = 0.0001f;

    Servo servoX = new Servo(xOut);
    Servo servoY = new Servo(yOut);

    Joystick js = RobotTemplate.monitor.getJoystick();

    public void update() {

        x += js.getX() * add;
        y += js.getY() * add;

        if (x > 1) {
            x = 1;
        } else if (x < 0) {
            x = 0;
        }
        if (y > 1) {
            y = 1;
        } else if (y < 0) {
            y = 0;
        }

        servoX.set(x);
        servoY.set(y);

    }

}
