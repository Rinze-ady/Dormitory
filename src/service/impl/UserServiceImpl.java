package service.impl;

import bean.UserBean;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    //用户持久层接口对象
    private IUserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param name 账户
     * @param pwd 密码
     * @return
     */
    @Override
    public UserBean logIn(String name, String pwd) {
        return userDao.logIn(name, pwd);
    }

    /**
     * 查询所有
     */
    @Override
    public List<UserBean> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 添加用户
     * @param user  用户对象
     */
    @Override
    public void addUser(UserBean user) {

        userDao.addUser(user);
    }

    /**
     * 删除用户
     * @param userId  用户id
     */
    @Override
    public void delUser(int userId) {

        userDao.delUser(userId);
    }

    /**
     * 修改用户等级
     * @param userId 用户id
     * @param grade 等级
     */
    @Override
    public void updateUser(int userId, String grade) {

        userDao.updateUser(userId,grade);
    }

    @Override
    public String updatePwd(String oldPwd, String newPwd) {
        return null;
    }

    /**
     * 修改密码
     * @param userId
     * @param newPwd
     * @param oldPwd
     * @return
     */
  /*  @Override
    public String updatePwd(int userId, String newPwd, String oldPwd) {
        return userDao.updatePwd(userId,newPwd,oldPwd);
    }*/

    /**
     * 根据id查找用户
     * @param userId 用户id
     * @return
     */
    @Override
    public UserBean findUserById(int userId) {
        return userDao.findUser(userId);
    }
}
