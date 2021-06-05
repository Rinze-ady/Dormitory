package dao;

import bean.UserBean;

import java.util.List;

public interface IUserDao {
    /**
     * 登录
     * @param name 账户
     * @param pwd 密码
     * @return
     */
    public UserBean logIn(String name, String pwd);

    /**
     * 查询所有
     */
    public List<UserBean> findAllUser();


    /**
     * 添加用户
     * @param user  用户对象
     */
    public void addUser(UserBean user);



    /**
     * 删除用户
     * @param userId  用户id
     */
    public void delUser(int userId);

    /**
     * 修改用户等级
     * @param userId 用户id
     * @param grade 等级
     */
//    public void updateUser(String grade);
    public String updateUser(int userId, String grade);


    /**
     * 修改密码
     *
     * @param newPwd
     * @param oldPwd
     * @return
     */
    public String updatePwd(String oldPwd,String newPwd);

    /**
     *检测id是否存在
     */
    public boolean findByUserId(int userId);

    /**
     * 根据id查找用户
     * @param userId 用户id
     * @return
     */
    public UserBean findUser(int userId);

    /**
     * 判断是否重名
     * @param name 用户名
     * @return 判断结果
     */
    public boolean checkName(String name);
}
