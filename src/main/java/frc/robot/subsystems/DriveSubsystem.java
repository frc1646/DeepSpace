/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands. 

  private ADXRS450_Gyro gyro;
  private DriveSide leftSide, rightSide;
  private static DriveSubsystem instance;

  private DriveSubsystem() {
    gyro = new ADXRS450_Gyro();
    leftSide = new DriveSide(RobotMap.FRONT_LEFT, RobotMap.BACK_LEFT, RobotMap.INV_1, RobotMap.INV2);
    rightSide = new DriveSide(RobotMap.FRONT_RIGHT, RobotMap.BACK_RIGHT, RobotMap.INV_3, RobotMap.INV_4);


  }

  public void setPower(double leftPower, double rightPower) {
    leftSide.setPower(leftPower);
    rightSide.setPower(rightPower);
  }

  public double getAngle() {
    return gyro.getAngle();
  }
  public void resetGyro() {
    gyro.reset();
  }

  public void calibrateGyro() {
    gyro.calibrate();
  }




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithJoy());
  }

  public static DriveSubsystem getInstance() {
    if (instance == null ) {
      instance == new DriveTrain();
    }
    return instance;
  }

  private class DriveSide {
    private VictorSP master, slave;
    //private Encoder encoder

    public DriveSide(int port1, int port2, boolean inv1, boolean inv2) {
      master = new VictorSP(port1);
      slave = new VictorSP(port2);

      master.setInverted(inv1);
      slave.setInverted(inv2);

    }

    public void setPower(double power) {
      master.set(power);
      slave.set(power);
    }

  }
}
  



