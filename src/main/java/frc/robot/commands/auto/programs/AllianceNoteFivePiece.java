// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.programs;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.auto.FollowPathCmd;
import frc.robot.commands.auto.SetInitialPoseCmd;
import frc.robot.commands.automation.AutoAllHomeCmd;
import frc.robot.commands.automation.AutoGroundIntakeCmd;
import frc.robot.commands.automation.AutoSubwooferFireCmd;
import frc.robot.subsystems.FeederSys;
import frc.robot.subsystems.PivotSys;
import frc.robot.subsystems.RollersSys;
import frc.robot.subsystems.SwerveSys;

public class AllianceNoteFivePiece extends SequentialCommandGroup {
  public AllianceNoteFivePiece(SwerveSys swerveSys, FeederSys FeederSys, RollersSys RollersSys, PivotSys PivotSys) {
    addCommands(
      // Again you can do it this way or keep the commands in their own files if you're more comfortable with that.
      new SetInitialPoseCmd("SubwooferPosToMidlineNoteThree", swerveSys),
      // Commands.runOnce(() -> swerveSys.setTranslation(new Translation2d(1.3, 5.55)), swerveSys),
      new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys),
      new WaitCommand(0.08),
      new FollowPathCmd("SubwooferPosToMidlineNoteThree", swerveSys)
        .alongWith(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() > 4.0)
        .andThen(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys))),
      new FollowPathCmd("MidlineNoteThreeToSubwooferPos", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys)
          .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < 1.8))
          .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new FollowPathCmd("SubwooferPosToAllianceNoteOne", swerveSys)
        .alongWith(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys)),
      new FollowPathCmd("AllianceNoteOneToSubwooferPos", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys)
          .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < 1.8))
          .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new FollowPathCmd("SubwooferPosToAllianceNoteTwo", swerveSys)
        .alongWith(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys)),
      new FollowPathCmd("AllianceNoteTwoToSubwooferPos", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys)
          .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < 1.8))
          .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new FollowPathCmd("SubwooferPosToAllianceNoteThree", swerveSys)
        .alongWith(new AutoGroundIntakeCmd(PivotSys, FeederSys, RollersSys)),
      new FollowPathCmd("AllianceNoteThreeToSubwooferPos", swerveSys)
        .alongWith(new AutoAllHomeCmd(PivotSys, FeederSys, RollersSys)
          .andThen(new WaitUntilCommand(() -> swerveSys.getBlueSidePose().getX() < 1.8))
          .andThen(new AutoSubwooferFireCmd(FeederSys, RollersSys, PivotSys))),
      new WaitCommand(0.75)
    );
  }
}
