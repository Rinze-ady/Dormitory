package dao;

import java.sql.*;

public class BaseDao {
    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //建立连接
    protected Connection con;

    //sql语句执行对象
    protected PreparedStatement ps;

    //结果集
    protected ResultSet rs;

    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     */
    public void setConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:6789/dormitory?characterEncoding=utf-8",
                    "root", "lovo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnection(){
        try {
            if(rs!=null){
                rs.close();
            }
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BaseDao dao = new BaseDao();
        dao.setConnection();
        System.out.println(dao.con);
        System.out.println(dao.ps);
    }

}
