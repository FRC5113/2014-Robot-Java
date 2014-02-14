

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author MHS
 */
public class Lifter1 {

    private RobotDrive lift = new RobotDrive(4, 5);

    Button Right4;
    Button Right5;
    Joystick rightStick;
    
    public void update(Button Right4, Button Right5) {
           lift.setSafetyEnabled(true);
           
           
        
        
        
        
                

    }    
}

