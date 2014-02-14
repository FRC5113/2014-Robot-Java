/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author MHS
 */
public class Drive {

    private RobotDrive drive = new RobotDrive(3, 4);
    
    boolean useTankDrive = true;
    
         double speedDefault = 0.4;
         double speedMax = 0.7;
         double speedMin = 0.25;
         
         boolean isEmergencyStopped = false;

    /*
     Similar to pow, but only uses integer exponents and
     keeps negative inputs negative.
     */
    private double powJoystick(double a, int b) {
        double f;
        f = Math.abs(a);
        for (int c = 1; c < b; c++) {
            f *= f;
        }
        if (a < 0) {
            f = -f;
        }
        return f;
    }

    /*
     Two joysticks are used, one for each set of wheels.
     */
    private void tankDrive(Joystick rightStick, Joystick leftStick, boolean squareForwards) {
        double right = rightStick.getY();
        double left = leftStick.getY();

        right = powJoystick(right, 1);
        left = powJoystick(left, 1);

        left *= -0.95;
        right *= -0.95;

        drive.tankDrive(left, right);
    }

   

    /*
     Controls simple player control, speed control, motor safety, etc.
     */
    public void speedControl(boolean useSpeedMax, boolean useSpeedMin) {
        drive.setSafetyEnabled(true);
       
        if(isEmergencyStopped == false){
       
        //Set wheel speed
        if (useSpeedMin) {
            drive.setMaxOutput(speedMin);
        } else if (useSpeedMax) {
            drive.setMaxOutput(speedMax);
        } else  
            drive.setMaxOutput(speedDefault); 
        
    }
        else{
              drive.setMaxOutput(0.0);
              isEmergencyStopped = false;
            }
    }
    /*
    Stops motors from moving, but does not "Disable".
    */
    public void emergencyStop() {
        
        isEmergencyStopped = true;
    }
    
    public void useMotors(){
        
        tankDrive(RobotTemplate.driveSticks.rightStick, RobotTemplate.driveSticks.leftStick, true);
        
    }
    
    public void simpleDrive(float left, float right) {
        drive.tankDrive(left, right);
    }
    
    

}
