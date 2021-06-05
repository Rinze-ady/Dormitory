package dao.impl;

import bean.ClassBean;
import bean.RoomBean;
import bean.StudentBean;
import dao.BaseDao;
import dao.IStudentDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl  extends BaseDao implements IStudentDao {



    /**
     * 学生入住
     * 1、添加学生
     * @param student  学生实体类
     */
    @Override
    public void addStudent(StudentBean student) {
        this.setConnection();

        String str = "insert into t_student(s_name,s_sex,fk_classId,fk_roomId,s_phone,s_intoDate,s_imgPath)values(?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,student.getStuName());
            ps.setObject(2,student.getStuSex());

            ps.setObject(3,student.getStuClassBean().getClassId());
            ps.setObject(4,student.getStuRoomBean().getRoomId());

            ps.setObject(5,student.getTelNum());
            ps.setObject(6,student.getStartDate());
            ps.setObject(7,student.getFacePath());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 按id给学生退房
     * 1、删除学生
     * @param stuId  学生id
     */
    @Override
    public void delStudent(int stuId) {
        this.setConnection();

        String str = "delete from t_student where pk_studentId = ?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,stuId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 根据学生编号，修改学生的房间信息
     * @param stuId   学生id
     * @param roomId   新房间id
     */
    @Override
    public void changeRoom(int stuId, int roomId) {
        this.setConnection();

        //新房间没有的情况是不是该判断一下

        String str = "update t_student set fk_roomId = ? where pk_studentId = ?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,roomId);
            ps.setObject(2,stuId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 根据id查询学生信息
     * @param id  学生id
     * @return  学生对象
     */
    @Override
    public StudentBean findById(int id) {
        StudentBean st = new StudentBean();
        this.setConnection();

        String str = "SELECT * FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE pk_studentId=?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1, id);

            rs = ps.executeQuery();
            while (rs.next()){
                st = fullStudent();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return st;
    }

    @Override
    public List<StudentBean> findByItem(String name, String className, String room) {
        List<StudentBean>list=new ArrayList<>();
        this.setConnection();
        String sql="SELECT * FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` left JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE 1=1 ";
        if (name!=null&&name.length()!=0){
            sql+="and s_name like '%"+name+"%'";
        }
        if (className!=null&&className.length()!=0){
            sql+=" and c_className like '%"+className+"%'";
        }
        if (room!=null&&room.length()!=0){
            sql+=" and r_address like '%"+room+"%'";
        }
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(fullStudent());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    /**
     * 按班级id统计学生人数
     *
     */
    @Override
    public int sumByClassId(int classId) {
        ClassBean classBean=new ClassBean();
        this.setConnection();

        try {
            ps=con.prepareStatement("SELECT COUNT(pk_studentId) c FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` GROUP BY pk_classId HAVING c.`pk_classId`=?");
            ps.setObject(1,classId);
            rs=ps.executeQuery();
            if (rs.next()){
                classBean.setSumStudent(rs.getInt("c"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classBean.getSumStudent();
    }

    @Override
    public String findClassName(int id) {
        ClassBean classBean=new ClassBean();
        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT c_className c FROM t_student s JOIN t_class c ON s.`pk_studentId`=c.`pk_classId` WHERE pk_studentId=?");
            ps.setObject(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                classBean.setClassName(rs.getString("c"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classBean.getClassName();
    }

    @Override
    public String findRoom(int id) {
        RoomBean roomBean=new RoomBean();
        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT r_address c FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE pk_studentId=?");
            ps.setObject(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                roomBean.setRoomAddress(rs.getString("c"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomBean.getRoomAddress();
    }

    @Override
    public List<StudentBean> findStudentByRoomId(int roomId) {
        List<StudentBean>list=new ArrayList<>();
        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT * FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE pk_roomId=?");
            ps.setObject(1,roomId);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(fullStudent());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public String findClassByStudentId(int studentId) {
        this.setConnection();
        String str=null;
        try {
            ps=con.prepareStatement("SELECT c_className FROM t_student s JOIN t_class c ON s.`fk_classId`=c.`pk_classId` WHERE pk_studentId=?");
            ps.setObject(1,studentId);
            rs=ps.executeQuery();
            if (rs.next()){
                str=rs.getString("c_className");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }


    /**
     * 从结果集中得到数据，封装实体对象
     * 表中的外键不在表示层显示，在实体类中定义的其他实体类对象属性不用在此处操作
     */
    private StudentBean fullStudent(){
        StudentBean st = new StudentBean();

        try {
            st.setStuId(rs.getInt("pk_studentId"));
            st.setStuName(rs.getString("s_name"));
            st.setStuSex(rs.getString("s_sex"));

            st.setStuRoomBean(new RoomBean(rs.getString("r_address")));
            st.setStuClassBean(new ClassBean(rs.getString("c_className")));

            st.setTelNum(rs.getString("s_phone"));
            st.setFacePath(rs.getString("s_imgpath"));
            st.setStartDate(LocalDate.parse(rs.getString("s_intoDate")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return st;
    }

    public static void main(String[] args) {
        //依赖倒置原则，接口引用接口实现类对象
        IStudentDao dao = new StudentDaoImpl();
//        System.out.println(dao.findAllStu());
//        dao.addStudent(new StudentBean("王得分","女",2,1,"13798475865","6.gif"));
//        dao.delStudent(7);
//        dao.changeRoom(6,2);
//        System.out.println(dao.findById(1));
//        System.out.println(dao.sumStuRoom(3));
//        System.out.println(dao.sumStu(1));

    }
}
