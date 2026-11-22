package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

public class Basic extends CommandOpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose start1 = poseFactory.of(56, 8, 270);
    private final Pose end1 = poseFactory.of(56, 12, 270);
    private final Path first = Paths.line(start1,end1).linear(start1,end1);

    public void initialize(){
        schedule(
                new FollowPathCommand(follower, first, true)
        );
    }
    public void run(){
        super.run();
        follower.update();
    }
}
