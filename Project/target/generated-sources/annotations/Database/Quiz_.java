package Database;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T22:50:55")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

    public static volatile SingularAttribute<Quiz, Date> editionDate;
    public static volatile SingularAttribute<Quiz, Integer> id;
    public static volatile SingularAttribute<Quiz, String> title;
    public static volatile SingularAttribute<Quiz, Date> creationDate;
    public static volatile SingularAttribute<Quiz, Integer> userId;
    public static volatile SingularAttribute<Quiz, Integer> categoryId;
    public static volatile SingularAttribute<Quiz, Boolean> enabled;

}