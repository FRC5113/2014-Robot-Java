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

    public JoystickButton hardStopEnable;
    public int hardStopEnablePos = 1;

    public JoystickButton resetCamera;
    public int resetCameraPos = 3;

    public JoystickButton enableCameraMovement;
    public int enableCameraMovementPos = 2;

    private JoystickButton eStop;
    private int eStopPos = 7;

    public MonitorControl(int input) {
        js = new Joystick(input);
        grabberIn = new JoystickButton(js, grabberInPos);
        grabberOut = new JoystickButton(js, grabberOutPos);
        eStop = new JoystickButton(js, eStopPos);
        hardStopEnable = new JoystickButton(js, hardStopEnablePos);
        resetCamera = new JoystickButton(js, resetCameraPos);
        enableCameraMovement = new JoystickButton(js, enableCameraMovementPos);

    }

    public Joystick getJoystick() {
        return js;
    }

    public void update() {
        if (eStop.get()) {
            RobotTemplate.wheels.emergencyStop();
        }
        if (!hardStopEnable.get()) {
            Drive.hardStopsEnabled = true;
        } else {
            Drive.hardStopsEnabled = false;
        }
        if (resetCamera.get()) {
            RobotTemplate.base.x = 0.5f;
            RobotTemplate.base.y = 0.5f;
        }
    }

}
