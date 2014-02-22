/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

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

    public static Drive wheels;
    public static Grabber grabber;
    public static MonitorControl monitor;
    public static DriverJoysticks driveSticks;
    public static Autonomous auto;
    public static CameraBase base;

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        wheels.leftEncoder.reset();
        wheels.rightEncoder.reset();
        while (isAutonomous() && isEnabled()) {
            auto.update();
            Timer.delay(0.005f);
        }//end while
    }//end autonomous()

    protected void robotInit() {
        
        wheels = new Drive();
        grabber = new Grabber();
        monitor = new MonitorControl(3);
        driveSticks = new DriverJoysticks(1, 2);
        auto = new Autonomous();
        base = new CameraBase();
    }//end robotInit()

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        
        //this is here just incase something goes horribly wrong.   (I just say this cus I dont know why we did this)
                                                                    //No one actually knows what exactly happens in the substructure
        //if something breaks the while won't execute and the robot will stand still
        while (isOperatorControl() && isEnabled()) {
            driveSticks.update();   //DriverJoysticks, which are controlling input from the joysticks to make it meaningful
              
            monitor.update();       //MonitorControl update() method 
                                    //Note: MonitorControl is actually those pins that grab the ball
            
            grabber.update();       //apparantly so is grabber, JAKE TELL ME WHAT YOU DID!!!
            wheels.useMotors();     //simple this is the wheels, like the ones that move the robot along the earths surface
            base.update();          // remember that camera thats so cool, yea this is what makes it MMMMOOOOVVVVEEEE
            
            Timer.delay(0.005f);    //gotta delay cus otherwise we get weird errors
        }//end while
    }//end operatorControl()
}//end class RobotTemplate
