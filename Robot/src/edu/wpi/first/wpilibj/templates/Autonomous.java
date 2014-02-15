/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author MHS
 */
public class Autonomous {

    private static int distance = 120;

    public void update() {
        RobotTemplate.wheels.leftEncoder.setDistancePerPulse(1 / 636.61);
        RobotTemplate.wheels.rightEncoder.setDistancePerPulse(1 / 636.61);
        //the conversion factor between inches and tics.
        if (Math.abs(RobotTemplate.wheels.leftEncoder.getDistance()) < distance && Math.abs(RobotTemplate.wheels.rightEncoder.getDistance()) < distance) {
            RobotTemplate.wheels.simpleDrive(1, 1);
        } else {
            RobotTemplate.wheels.simpleDrive(0, 0);
        }//end else
    }//end update()
}//end class Autonomous
