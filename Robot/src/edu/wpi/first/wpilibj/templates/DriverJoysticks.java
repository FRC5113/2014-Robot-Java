/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author MHS
 */
public class DriverJoysticks {

    Joystick rightStick;
    Joystick leftStick;

    Button turboRight;
    int turboRightPos = 3;

    Button turboLeft;
    int turboLeftPos = 3;

    Button slowRight;
    int slowRightPos = 2;

    Button slowLeft;
    int slowLeftPos = 2;

    Button stopRight;
    int stopRightPos = 6;

    Button stopLeft;
    int stopLeftPos = 6;

    Button grabberInLeft;
    int grabberInLeftPos = 1;

    Button grabberOutRight;
    int grabberOutRightPos = 1;

    double speedDefault;
    double speedMax;
    double speedMin;

    public DriverJoysticks(int right, int left) {

        turboRight = new JoystickButton(rightStick, turboRightPos);
        turboLeft = new JoystickButton(leftStick, turboLeftPos);
        slowRight = new JoystickButton(rightStick, slowRightPos);
        slowLeft = new JoystickButton(leftStick, slowLeftPos);
        stopRight = new JoystickButton(rightStick, stopRightPos);
        stopLeft = new JoystickButton(leftStick, stopLeftPos);
        grabberInLeft = new JoystickButton(leftStick, grabberInLeftPos);
        grabberOutRight = new JoystickButton(rightStick, grabberOutRightPos);

        SmartDashboard.putNumber("speedMax", speedMax);
        SmartDashboard.putNumber("speedMin", speedMin);
        SmartDashboard.putNumber("speedDefault", speedDefault);

        rightStick = new Joystick(right);
        leftStick = new Joystick(left);
    }

    public void update() {

        RobotTemplate.wheels.speedControl(turboRight.get() || turboLeft.get(), slowRight.get() || slowLeft.get());

        if (stopRight.get() || stopLeft.get()) {
            RobotTemplate.wheels.emergencyStop();
        }
        
        if(grabberInLeft.get()) {
            RobotTemplate.grabber.takeInput(1);
        }
        else if(grabberOutRight.get()) {
            RobotTemplate.grabber.takeInput(-1);
        }   
    }
}
