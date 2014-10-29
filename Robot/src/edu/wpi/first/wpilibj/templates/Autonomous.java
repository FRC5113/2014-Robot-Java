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

    private static final double distance = 6.0; //apparently this is the number of meters we must travel
    private static float autoLeft = -0.5f;  //speed of the left wheels, also they're negative, cus the robot is wired that way
    private static float autoRight = -0.57f;    //speed of the right wheels, it's slightly different cuz the robot drives weird
    private static double leftDistance = 0.0;
    private static double rightDistance = 0.0;

    float time = System.currentTimeMillis();

    public Autonomous() {
        SmartDashboard.putNumber("autoLeft", autoLeft);     //attempted placing values onto smartdashboard
        SmartDashboard.putNumber("autoRight", autoRight);   //note: we aren't very good at this
        Robot5113.wheels.leftEncoder.setDistancePerPulse(1 / 636.61);
        Robot5113.wheels.rightEncoder.setDistancePerPulse(1 / 636.61);
    }

    public void update() {

        if (System.currentTimeMillis() > time + 7000) {
            reachedGoal();
        } //Uncomment this if autonomous is inconsistent or broken.
        //The disabled code below allows for distance sensing. Because of inconsistencies, it is disabled for safety.
        else {
            //the conversion factor between meters and tics. is 1 meter = 636.61 tics(which is what encoders measure)   

            leftDistance = Math.abs(Robot5113.wheels.leftEncoder.getDistance());    //use absolute value
            rightDistance = Math.abs(Robot5113.wheels.rightEncoder.getDistance());  //for comparison purposes
            //test this on raw values then on distance values if possible.
            if (leftDistance < distance || rightDistance < distance) {
                Robot5113.wheels.simpleDrive(autoLeft, autoRight);
            } else {
                reachedGoal();
            }//end else                                                      //this should occur once the robot has moved the amount of meters.
        }

    }//end update()

    private void reachedGoal() {
        Robot5113.wheels.simpleDrive(-0.3f, -0.34f);
        //RobotTemplate.wheels.simpleDrive((Math.sin((System.currentTimeMillis() / 5000f))) / 3f, (Math.cos((System.currentTimeMillis() / 5000f))) / 3f);
        //Complicated math stuff that makes the robot shift right and left to allow it to push the ball in if needed.
        Robot5113.grabber.drive.tankDrive(1, 1);       //this part signifies that the robot stop moving

    }
}//end class Autonomous