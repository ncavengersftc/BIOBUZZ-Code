package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.commands.IntakePollen;
import org.firstinspires.ftc.teamcode.commands.ShootPollen;
import org.firstinspires.ftc.teamcode.commands.StopIntake;
import org.firstinspires.ftc.teamcode.commands.StopShooter;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
public class op extends CommandOpMode {
    private Shooter shooter;
    private Intake intake;
    private Follower follower;
        private final PoseFactory poseFactory = PoseFactory.degrees();
        private final Pose start = poseFactory.of(56, 8, 90);
        private final Pose path1 = poseFactory.of(56.103, 22.9324, 89.6048);
        private final Pose point2Start = poseFactory.of(56.103, 22.9324, 180);
        private final Pose point2 = poseFactory.of(22.5077, 8.618, 180);
        private final Pose point2Control1 = poseFactory.of(36.397, 35.3275, 0);
        private final Pose point3 = poseFactory.of(8.7535, 8.6894, 179.7025);
        private final Pose point4Start = poseFactory.of(8.7535, 8.6894, 180);
        private final Pose point4 = poseFactory.of(56.3245, 22.4857, 90);
        private final Pose point4Control1 = poseFactory.of(23.4479, 31.5806, 0);
        private final Pose point5Start = poseFactory.of(56.3245, 22.4857, 90);
        private final Pose point5 = poseFactory.of(9.9669, 46.3559, 180);
        private final Pose point6Start = poseFactory.of(9.9669, 46.3559, 180);
        private final Pose point6 = poseFactory.of(56.0568, 22.4021, 90);
        private final Path first = Paths.line(start, path1).tangent();
        private final Path second = Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
        private final Path third = Paths.line(point2, point3).tangent();
        private final Path fourth = Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
        private final Path fifth = Paths.line(point5Start, point5).linear(point5Start, point5);
        private final Path sixth = Paths.line(point6Start, point6).linear(point6Start, point6);
        public void initialize(){
            shooter = new Shooter(hardwareMap);
            intake = new Intake (hardwareMap);
            follower = Constants.create(hardwareMap);
            follower.setPose(start);
            schedule(new SequentialCommandGroup(
                    new FollowPathCommand(follower, first, true),
                    new ShootPollen(shooter),
                    new WaitCommand(100),
                    new StopShooter(shooter),
                    new FollowPathCommand(follower, second, true),
                    new IntakePollen(intake),
                    new FollowPathCommand(follower, third, true),
                    new StopIntake(intake),
                    new FollowPathCommand(follower, fourth, true),
                    new ShootPollen(shooter),
                    new WaitCommand(100),
                    new StopShooter(shooter),
                    new FollowPathCommand(follower, fifth, true),
                    new IntakePollen(intake),
                    new WaitCommand(100),
                    new StopIntake(intake),
                    new FollowPathCommand(follower, sixth),
                    new ShootPollen (shooter),
                    new WaitCommand (100),
                    new StopShooter(shooter)));

        }
        public void run(){
            super.run();
            follower.update();
        }
    }

