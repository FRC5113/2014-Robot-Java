/*
 * majority of this class was written by George Elia
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author George Elia
 */
public class Autonomous {

    private static final double distance = 9.4;
    private static float autoLeft = -0.5f;
    private static float autoRight = -0.57f;
    private static double leftDistance = 0.0;
    private static double rightDistance = 0.0;
    public Autonomous()
    {
        SmartDashboard.putNumber("autoLeft", autoLeft);     //attempted placing values onto smartdashboard
        SmartDashboard.putNumber("autoRight", autoRight);   //note: we aren't very good at this
    }
    public void update() {
        //the conversion factor between meters and tics. is 1 meter = 636.61 tics(which is what encoders measure)
        RobotTemplate.wheels.leftEncoder.setDistancePerPulse(1 / 636.61);       //once the code is pushed to git
        RobotTemplate.wheels.rightEncoder.setDistancePerPulse(1 / 636.61);      //place these lines within the constructor
        
        leftDistance = Math.abs(RobotTemplate.wheels.leftEncoder.getDistance());    //use absolute value
        rightDistance = Math.abs(RobotTemplate.wheels.rightEncoder.getDistance());  //for comparison purposes
        
        //test this on raw values then on distance values if possible.
        if (leftDistance < distance || rightDistance < distance) {
            RobotTemplate.wheels.simpleDrive(autoLeft, autoRight);    //negative values because the robot was wired that way
        } else {
            RobotTemplate.wheels.simpleDrive(0, 0);             //this part signifies that the robot stop moving
        }//end else                                             //this should occur once the robot has moved 9.4 meters.
    }//end update()
}//end class Autonomous
