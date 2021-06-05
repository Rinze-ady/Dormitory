package bean;

/**
 * 用户实体类
 */
public class UserBean {
    /**用户id*/
    private int userId;
    /**用户名*/
    private String userName;
    /**用户密码*/
    private String userPwd = "123456";
    /**用户真实姓名*/
    private String realName;
    /**用户等级*/
    private String userGrade;

    public UserBean(String userName, String userPwd, String realName, String userGrade) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.realName = realName;
        this.userGrade = userGrade;
    }

    public UserBean() {
    }

//    -------------------------------------------------
    //测试用
    public UserBean(String userGrade) {
        this.userGrade = userGrade;
    }
// --------------------------------------------------------------


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", realName='" + realName + '\'' +
                ", userGrade='" + userGrade + '\'' +
                '}';
    }
}
