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

    private static final double distance = 9.4; //apparently this is the number of meters we must travel
    private static float autoLeft = -0.5f;  //speed of the left wheels, also they're negative, cus the robot is wired that way
    private static float autoRight = -0.57f;    //speed of the right wheels, it's slightly different cuz the robot drives weird
    private static double leftDistance = 0.0;
    private static double rightDistance = 0.0;
    public Autonomous()
    {
        SmartDashboard.putNumber("autoLeft", autoLeft);     //attempted placing values onto smartdashboard
        SmartDashboard.putNumber("autoRight", autoRight);   //note: we aren't very good at this
        RobotTemplate.wheels.leftEncoder.setDistancePerPulse(1 / 636.61);
        RobotTemplate.wheels.rightEncoder.setDistancePerPulse(1 / 636.61);
    }
    public void update() 
    {
        //the conversion factor between meters and tics. is 1 meter = 636.61 tics(which is what encoders measure)   

        leftDistance = Math.abs(RobotTemplate.wheels.leftEncoder.getDistance());    //use absolute value
        rightDistance = Math.abs(RobotTemplate.wheels.rightEncoder.getDistance());  //for comparison purposes
        
        //test this on raw values then on distance values if possible.
        if (leftDistance < distance || rightDistance < distance) {
            RobotTemplate.wheels.simpleDrive(autoLeft, autoRight); 
        } else {
            RobotTemplate.wheels.simpleDrive(0, 0);             //this part signifies that the robot stop moving
        }//end else                                             //this should occur once the robot has moved 9.4 meters.
    }//end update()
}//end class Autonomous