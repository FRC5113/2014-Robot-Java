/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author MHS
 */
public class MonitorControl {

    private Joystick js;

    private JoystickButton grabberIn;
    private int grabberInPos = 5;

    private JoystickButton grabberOut;
    private int grabberOutPos = 6;

    private JoystickButton lifterUp;
    private int lifterUpPos = 3;

    private JoystickButton lifterDown;
    private int lifterDownPos = 4;

    private JoystickButton eStop;
    private int eStopPos = 9;

    public MonitorControl(int input) {
        js = new Joystick(input);
        grabberIn = new JoystickButton(js, grabberInPos);
        grabberOut = new JoystickButton(js, grabberOutPos);
        lifterUp = new JoystickButton(js, lifterUpPos);
        lifterDown = new JoystickButton(js, lifterDownPos);
        eStop = new JoystickButton(js, eStopPos);
    }

    public void update() {
        if (grabberIn.get()) {
            RobotTemplate.grabber.takeInput(1);
        } else if (grabberOut.get()) {
            RobotTemplate.grabber.takeInput(-1);
        }

        if (eStop.get()) {
            RobotTemplate.wheels.emergencyStop();
        }
    }

}
