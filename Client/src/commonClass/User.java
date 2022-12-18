package commonClass;

import java.io.Serial;
import java.io.Serializable;

/**
 * Karl Rules!
 * 2022/12/17
 */
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID= 10231313L;

    private String userId;
    private String passwd;

    public User(){

    }
    public User(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
