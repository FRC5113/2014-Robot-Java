/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {

    public static Drive wheels = new Drive();
    public static Grabber grabber = new Grabber();
    public static CameraBase base = new CameraBase();   
    public static MonitorControl monitor = new MonitorControl(3);
    public static DriverJoysticks driveSticks = new DriverJoysticks(1, 2);
    public static Autonomous auto = new Autonomous();

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        while(isAutonomous() && isEnabled()) {
            auto.update();
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */

    public void operatorControl() {
        
        System.err.println("Starting Operator Control...");
        
        while (isOperatorControl() && isEnabled()) {
            
            driveSticks.update();
            monitor.update();
            
            grabber.update();
            wheels.useMotors();

            
            Timer.delay(0.005f);
        }
    }
}
