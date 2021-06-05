package dao.impl;

import bean.ClassBean;
import bean.StudentBean;
import dao.BaseDao;
import dao.IClassDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl extends BaseDao implements IClassDao {
    /**
     * 查询所有班级
     */
    @Override
    public List<ClassBean> findAllClass() {
        List<ClassBean> list = new ArrayList<>();

        this.setConnection();

        try {
            ps = con.prepareStatement("select*from t_class");

            rs = ps.executeQuery();
            while (rs.next()){
                list.add(fullClass());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return list; 
    }

    @Override
    public void addClass(String className, String teacher, LocalDate startDate) {
        this.setConnection();
        try {
            ps=con.prepareStatement("insert into t_class(c_className,c_startDate,c_headmaster)values(?,?,?)");
            ps.setObject(1,className);
            ps.setObject(2,startDate);
            ps.setObject(3,teacher);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
    }



    @Override
    public boolean delClass(int classId) {
        this.setConnection();

        String str = "delete from t_class where pk_classId=?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1, classId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return false;
    }



    /**
     * 从结果集中得到数据，封装实体对象
     * 表中的外键不在表示层显示，在实体类中定义的其他实体类对象属性不用在此处操作
     * @return
     */
    private ClassBean fullClass(){
        ClassBean cl = new ClassBean();

        try {
            cl.setClassId(rs.getInt("pk_classId"));
            cl.setClassName(rs.getString("c_className"));
            cl.setStartDate(LocalDate.parse(rs.getString("c_startDate")));
            cl.setTeacher(rs.getString("c_headmaster"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cl;
    }


    public static void main(String[] args) {
        //依赖倒置原则，接口引用接口实现类对象
        IClassDao dao = new ClassDaoImpl();
//        System.out.println(dao.findAllClass());
//        dao.addClass(new ClassBean("GT11",LocalDate.parse("2019-11-05"),"朱老师"));

    }

}
