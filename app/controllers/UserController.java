package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.akkamodel.UserActor;
import services.akkamodel.UserActorBeans;
/**
 * @author jiang
 */
public class UserController extends Controller {
    public Result query(Long id) {
        final ActorSystem system = ActorSystem.create("user");
        ActorRef actorRef = system.actorOf(UserActor.props());
        UserActorBeans.Query query = new UserActorBeans.Query(id);
        JsonNode result = FutureConverters.toJava(Patterns.ask(actorRef,query,2000))
                .thenApply(Object::toString).thenApply(Json::parse).toCompletableFuture().join();
        return ok(result);
    }
}
