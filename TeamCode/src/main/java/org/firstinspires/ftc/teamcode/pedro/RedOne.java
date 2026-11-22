package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.commands.ShootPollen;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous
public class RedOne extends CommandOpMode {
    private Shooter shooter;
    private Follower follower;
        private final PoseFactory poseFactory = PoseFactory.degrees();
        private final Pose start1 = poseFactory.of(56, 8, 270);
        private final Pose end1 = poseFactory.of(11.9355, 46.5786, 180);
        private final Pose control1 = poseFactory.of(33.9678, 27.2893, 250);
        private final Pose control2 = poseFactory.of(9, 66, 0);
        private final Pose end2 = poseFactory.of(46.7, 129.5, 270);
        private final Pose end3 = poseFactory.of(61, 133,270);
        private final Pose control3 = poseFactory.of(62, 121, 240);
        private final Path first = Paths.line(start1,end1).linear(start1,end1);
        private final Path second = Paths.curve(end1,control1,start1).linear(end1,start1);
        private final Path third = Paths.curve(start1,control2,end2).linear(start1,end2);
        private final Path fourth = Paths.curve(end2,control3,end3).linear(end2,end3);
    @Override
    public void initialize(){
        shooter = new Shooter(hardwareMap);
        follower = Constants.create(hardwareMap);
        follower.setPose(start1);
        schedule(new SequentialCommandGroup(
                new ShootPollen(shooter),
                new WaitCommand(50),
                new FollowPathCommand(follower, first, true),
                //collection thing
                new WaitCommand(100),
                new FollowPathCommand(follower, second, true),
                new ShootPollen(shooter),
                new WaitCommand(50),
                new FollowPathCommand(follower, third, true),
                //collection thing
                new FollowPathCommand(follower, fourth, false)
    ));
        }
    public void run(){
        super.run();
        follower.update();
    }
}
