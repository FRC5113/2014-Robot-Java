/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class pretty much starts control
 *
 * @author MHS
 */
public class DriverJoysticks {

    private Joystick rightStick;    //Use 2 Joystick Extreme 3D for driving
    private Joystick leftStick;

    private Button fastRight;
    private int fastRightPos = 3;

    private Button fastLeft;
    private int fastLeftPos = 3;

    private Button maxRight;
    private int maxRightPos = 2;

    private Button maxLeft;
    private int maxLeftPos = 2;

    private Button slowRight;
    private int slowRightPos = 4;

    private Button slowLeft;
    private int slowLeftPos = 4;

    private Button stopRight;
    private int stopRightPos = 10;

    private Button stopLeft;
    private int stopLeftPos = 10;

    public static Button grabberInLeft;
    private int grabberInLeftPos = 1;   //since you cant see what button this refers to on the joystick
    //its the trigger (took me a while to figure it out)
    public static Button grabberOutRight;
    private int grabberOutRightPos = 1; //its that sneaky trigger again

    private double speedDefault;
    private double speedMax;
    private double speedMin;

    public Joystick getRightJoystick() {
        return rightStick;
    }

    public Joystick getLeftJoystick() {
        return leftStick;
    }

    public DriverJoysticks(int right, int left) {

        rightStick = new Joystick(right);
        leftStick = new Joystick(left);

        fastRight = new JoystickButton(leftStick, fastRightPos);
        fastLeft = new JoystickButton(rightStick, fastLeftPos);
        maxRight = new JoystickButton(leftStick, maxRightPos);
        maxLeft = new JoystickButton(rightStick, maxLeftPos);
        slowRight = new JoystickButton(leftStick, slowRightPos);
        slowLeft = new JoystickButton(rightStick, slowLeftPos);
        stopRight = new JoystickButton(leftStick, stopRightPos);
        stopLeft = new JoystickButton(rightStick, stopLeftPos);
        grabberInLeft = new JoystickButton(rightStick, grabberInLeftPos);
        grabberOutRight = new JoystickButton(leftStick, grabberOutRightPos);

        SmartDashboard.putNumber("speedMax", speedMin);
        SmartDashboard.putNumber("speedMin", speedMax);         //marks our failed attempts at getting SmartDashboard working
        SmartDashboard.putNumber("speedDefault", speedDefault);

    }

    public void update() {

        //Jake wrote a speed control function so that when the robot moves you can go faster or slower
        //this line does that fancy stuff, well it calls the method that does that fancy stuff
        Robot5113.wheels.speedControl(fastRight.get() || fastLeft.get(), slowRight.get() || slowLeft.get(), maxLeft.get() || maxRight.get());

        //this is where emergency stop is called, I can verify from personal experience that it does work
        if (stopRight.get() || stopLeft.get()) {
            Robot5113.isEmergencyStopped = true;
        }
    }//end based update()
}//end class DriverJoysticks