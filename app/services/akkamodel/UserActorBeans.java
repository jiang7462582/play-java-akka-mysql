package services.akkamodel;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author jiang
 */
public class UserActorBeans {
    public static class Query implements Serializable {
        Long id;

        public Query(Long id) {
            this.id = id;
        }
    };
    public static class userSave{

    }
}
