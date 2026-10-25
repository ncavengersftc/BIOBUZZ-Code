package org.firstinspires.ftc.teamcode.pedro;

import static com.pedropathing.api.Paths.curve;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;


public class BlueOne extends OpMode{
    private Follower follower;
    private final PoseFactory p= PoseFactory.degrees();
    private final Pose start = p.of(56, 8, 90);
    private final Pose mid = p.of(70,9,90);
    private final Pose end = p.of(84,9,90);
    private Path end(){
        return curve(start,mid,end).linear(start,end);
    }


    public void init(){
        follower=Constants.create(hardwareMap);
        follower.setPose(start);
    }
    public void loop(){

    }
}
