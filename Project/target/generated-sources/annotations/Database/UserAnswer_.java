package Database;

import Database.UserAnswerPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-06T17:46:37")
@StaticMetamodel(UserAnswer.class)
public class UserAnswer_ { 

    public static volatile SingularAttribute<UserAnswer, Integer> answerId;
    public static volatile SingularAttribute<UserAnswer, Integer> questionId;
    public static volatile SingularAttribute<UserAnswer, UserAnswerPK> userAnswerPK;

}