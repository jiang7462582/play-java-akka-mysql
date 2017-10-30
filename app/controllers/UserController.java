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
    
    private final ActorSystem system = ActorSystem.create("user");
    private ActorRef actorRef = system.actorOf(UserActor.props());


    public Result query(Long id) {
        UserActorBeans.Query query = new UserActorBeans.Query(id);
        JsonNode result = FutureConverters.toJava(Patterns.ask(actorRef,query,2000))
                .thenApply(Object::toString).thenApply(Json::parse).toCompletableFuture().join();
        return ok(result);
    }

    public Result save(String name) {
        UserActorBeans.UserSave userSave = new UserActorBeans.UserSave(name);
        JsonNode result = FutureConverters.toJava(Patterns.ask(actorRef,userSave,2000))
                .thenApply(Object::toString).thenApply(Json::parse).toCompletableFuture().join();
        return ok(result);
    }

}
