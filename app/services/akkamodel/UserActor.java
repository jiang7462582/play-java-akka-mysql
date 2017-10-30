package services.akkamodel;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.Status;
import models.User;
import play.Logger;


/**
 * @author jiang
 */
public class UserActor extends AbstractActor {
    public static Props props() {
        return Props.create(UserActor.class);
    }

    @Override
    public void preStart() {
        Logger.info("我启动了");
    }

    @Override
    public void postStop() {
        Logger.info("我停止了");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UserActorBeans.Query.class, r ->
                    sender().tell(User.findById(r.id), self())
                ).
                matchAny(x ->sender().tell(
                        new Status.Failure(new Exception("unknown message")), self()
                ))
                .build();
    }
}
