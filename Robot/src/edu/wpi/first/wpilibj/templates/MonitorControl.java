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

    private Joystick js;    //Use Attack 3 Joystick for monitor control

    public static JoystickButton grabberIn;
    private int grabberInPos = 4;

    public static JoystickButton grabberOut;
    private int grabberOutPos = 5;

    private JoystickButton lifterUp;
    private int lifterUpPos = 10;

    private JoystickButton lifterDown;
    private int lifterDownPos = 11;

    private JoystickButton eStop;
    private int eStopPos = 7;

    public MonitorControl(int input) {
        js = new Joystick(input);
        grabberIn = new JoystickButton(js, grabberInPos);
        grabberOut = new JoystickButton(js, grabberOutPos);
        lifterUp = new JoystickButton(js, lifterUpPos);
        lifterDown = new JoystickButton(js, lifterDownPos);
        eStop = new JoystickButton(js, eStopPos);
    }

    public Joystick getJoystick() {
        return js;
    }

    public void update() {
        if (eStop.get()) {
            RobotTemplate.wheels.emergencyStop();
        }
    }

}
