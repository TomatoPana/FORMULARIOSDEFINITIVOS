package Database;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-06T21:56:13")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Date> dateTime;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Short> tipo;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> nombre;
    public static volatile SingularAttribute<Users, String> email;

}