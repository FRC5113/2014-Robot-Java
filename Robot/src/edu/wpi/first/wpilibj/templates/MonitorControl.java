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

    public JoystickButton hardStopEnable;
    public int hardStopEnablePos = 11;

    public JoystickButton resetCamera;
    public int resetCameraPos = 3;

    public JoystickButton enableCameraMovement;
    public int enableCameraMovementPos = 3;

    public JoystickButton armWheelsIn;
    public int armWheelsInPos = 2;

    public JoystickButton armWheelsOut;
    public int armWheelsOutPos = 3;

    private JoystickButton eStop;
    private int eStopPos = 10;

    public MonitorControl(int input) {
        js = new Joystick(input);
        eStop = new JoystickButton(js, eStopPos);
        hardStopEnable = new JoystickButton(js, hardStopEnablePos);
        resetCamera = new JoystickButton(js, resetCameraPos);
        enableCameraMovement = new JoystickButton(js, enableCameraMovementPos);
        armWheelsIn = new JoystickButton(js, armWheelsInPos);
        armWheelsOut = new JoystickButton(js, armWheelsOutPos);
    }

    public Joystick getJoystick() {
        return js;
    }

    public void update() {
        if (eStop.get()) {
            Robot5113.isEmergencyStopped = true;
        }
        //Drive.hardStopsEnabled = !hardStopEnable.get();
        /*if (resetCamera.get()) {
         Robot5113.base.x = 0.5f;
         Robot5113.base.y = 0.5f;
         }*/
    }

}
