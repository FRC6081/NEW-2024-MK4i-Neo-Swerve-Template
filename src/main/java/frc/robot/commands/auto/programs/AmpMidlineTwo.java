// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.programs;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.commands.auto.FollowPathCmd;
import frc.robot.commands.auto.SetInitialPoseCmd;
import frc.robot.commands.automation.AutoAllHomeCmd;
import frc.robot.commands.automation.AutoGroundIntakeCmd;
import frc.robot.commands.automation.AutoSubwooferFireCmd;
import frc.robot.subsystems.FeederSys;
import frc.robot.subsystems.PivotSys;
import frc.robot.subsystems.RollersSys;
import frc.robot.subsystems.SpacebarSys;
import frc.robot.subsystems.SwerveSys;

public class AmpMidlineTwo extends SequentialCommandGroup {
  public AmpMidlineTwo(SwerveSys swerveSys, FeederSys FeederSys, RollersSys RollersSys, PivotSys PivotSys, SpacebarSys SpacebarSys) {
    addCommands(
      new SetInitialPoseCmd("AmpMidlineTwoPathOne", swerveSys),
      new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys),
      new WaitCommand(0.08),
      new FollowPathCmd("AmpMidlineTwoPathOne", swerveSys)
        .alongWith(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() > 5.5)
          .andThen(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys, SpacebarSys))),
      new FollowPathCmd("AmpMidlineTwoPathTwo", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys))
          .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < AutoConstants.offsetSubwooferShotThreshold)
          .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new FollowPathCmd("AmpMidlineTwoPathThree", swerveSys)
        .alongWith(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() > 5.5)
          .andThen(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys, SpacebarSys))),
      new FollowPathCmd("AmpMidlineTwoPathFour", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys))
        .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < AutoConstants.offsetSubwooferShotThreshold)
        .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new WaitCommand(0.5)
    );
  }
}
