// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.OIConstants;

public class GrabberWheelSubsystem extends SubsystemBase {

  private final CANSparkMax m_grabberMotor = new CANSparkMax(GrabberConstants.motorID, MotorType.kBrushless);
  private final Joystick joystick = new Joystick(OIConstants.kOperatorController);

  private boolean lastButtonState = false;
  private boolean state = false;

  /** Creates a new GrabberWheelSubsystem. */
  public GrabberWheelSubsystem() {
    m_grabberMotor.setInverted(false);
    m_grabberMotor.getEncoder().setPosition(0);
    this.lastButtonState = false;
    this.state = false;
  }

  @Override
  public void periodic() {
    boolean buttonInput = this.joystick.getRawButtonPressed(OIConstants.Btn_RB);
    if(this.lastButtonState == true && buttonInput == false){
      this.state = !this.state;
    }
    this.lastButtonState = buttonInput;
    if (this.state){
      this.m_grabberMotor.set(0.15);
    } else {
      this.m_grabberMotor.set(0);
    }
  }

  public void rollRun(double speed){
    m_grabberMotor.set(speed);
  }

  public void rollStop(){
    m_grabberMotor.set(0);
  }

  public double getWheelPos(){
   return m_grabberMotor.getEncoder().getPosition();
  }

  public double getWheelVel(){
    return m_grabberMotor.getEncoder().getVelocity();
  }

  public void resetEncoders(){
    m_grabberMotor.getEncoder().setPosition(0);
  }
}
