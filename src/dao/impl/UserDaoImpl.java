package dao.impl;

import bean.UserBean;
import dao.BaseDao;
import dao.IUserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements IUserDao {
    /**
     * 登录
     * @param userName 账户
     * @param pwd 密码
     * @return
     */
    @Override
    public UserBean logIn(String userName, String pwd) {
        UserBean user=new UserBean();
        this.setConnection();
        try {
            ps=con.prepareStatement("select * from t_user where u_name=? and u_pwd=?");
            ps.setObject(1,userName);
            ps.setObject(2,pwd);
            rs=ps.executeQuery();
            if (rs.next()!=false){
                user=fullUser();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return user;
    }

    /**
     * 查询所有
     */
    @Override
    public List<UserBean> findAllUser() {
        List<UserBean> list = new ArrayList<>();

        this.setConnection();

        try {
            ps = con.prepareStatement("select*from t_user");

            rs = ps.executeQuery();
            while (rs.next()){
                list.add(fullUser());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return list;
    }

    /**
     * 添加用户
     * @param user  用户对象
     */
    @Override
    public void addUser(UserBean user) {

        this.setConnection();

        String str = "insert into t_user(u_name, u_pwd, u_realName, u_grade)values(?,?,?,?)";
        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,user.getUserName());
            ps.setObject(2,user.getUserPwd());
            ps.setObject(3, user.getRealName());
            ps.setObject(4,user.getUserGrade());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 删除用户
     * @param userId  用户id
     */
    @Override
    public void delUser(int userId) {
        this.setConnection();

        String str = "delete from t_user where pk_userId = ?";
        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,userId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    @Override
    public boolean checkName(String name) {
        this.setConnection();
        boolean yesOrNot=false;
        try {
            ps=con.prepareStatement("select * from t_user where u_name=?");
            ps.setObject(1,name);
            rs=ps.executeQuery();
            if (rs.next()){
                yesOrNot= true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();

        return yesOrNot;
    }

    @Override
    public String updateUser(int userId, String grade) {
        this.setConnection();
        try {
            ps=con.prepareStatement("update t_user set u_grade=? where pk_userId=?");
            ps.setObject(1,grade);
            ps.setObject(2,userId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return "修改成功";
    }

    @Override
    public String updatePwd(String oldPwd, String newPwd) {
        this.setConnection();
        try {
            ps=con.prepareStatement("update t_user set u_pwd=? where u_pwd=?");
            ps.setObject(1,newPwd);
            ps.setObject(2,oldPwd);
            ps.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        if (ps!=null){
            return "修改成功";
        }
        this.closeConnection();
        return "修改失败";
    }



    @Override
    public boolean findByUserId(int userId) {
        this.setConnection();
        try {
            ps=con.prepareStatement("select * from t_user where pk_userId=?");
            ps.setObject(1,userId);
            rs=ps.executeQuery();
            if (rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return true;
    }


    /**
     * 根据id查找用户
     * @param userId 用户id
     * @return
     */
    @Override
    public UserBean findUser(int userId) {

        UserBean us = new UserBean();
        this.setConnection();

        String str = "select*from t_user where pk_userId = ?";
        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,userId);

            rs = ps.executeQuery();
            if(rs.next()){
                us = fullUser();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return us;
    }



    /**
     * 从结果集中得到数据，封装实体对象
     * 表中的外键不在表示层显示，在实体类中定义的其他实体类对象属性不用在此处操作
     * 通过结果集来封装实体对象，封装属性个数和实体类中重写toString方法中的个数相同，和表中每个表的列数相同
     */
    private UserBean fullUser(){
        UserBean us = new UserBean();

        try {
            us.setUserId(rs.getInt("pk_userId"));
            us.setUserName(rs.getString("u_name"));
            us.setUserPwd(rs.getString("u_pwd"));
            us.setRealName(rs.getString("u_realName"));
            us.setUserGrade(rs.getString("u_grade"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return us;
    }

    public static void main(String[] args) {
        //依赖倒置原则，接口引用接口实现类对象
        IUserDao dao = new UserDaoImpl();
//        System.out.println(dao.findAllUser());
//        System.out.println(dao.logIn("aaa","123"));
//        dao.addUser(new UserBean("rrrr","658","杨波","房间管理员"));
//        dao.delUser(5);
//        dao.updateUser(4,"超级管理员");
//        System.out.println(dao.updatePwd(1,"987","123"));
        System.out.println(dao.findUser(2));
    }



}
