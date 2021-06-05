package service.impl;

import bean.StudentBean;
import dao.IClassDao;
import dao.IRoomDao;
import dao.IStudentDao;
import dao.impl.ClassDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.impl.StudentDaoImpl;
import service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    //学生持久层接口对象
    private IStudentDao stuDao = new StudentDaoImpl();
    /**
     * 班级持久对象
     */
    private IClassDao classDao=new ClassDaoImpl();
    /**
     * 房间持久对象
     */
    private IRoomDao roomDao=new RoomDaoImpl();

    /**
     * 学生入住
     * 1、添加学生
     * @param student  学生实体类
     */
    @Override
    public void addStudent(StudentBean student) {

        stuDao.addStudent(student);
    }

    /**
     * 按id给学生退房
     * 1、删除学生
     * @param stuId  学生id
     */
    @Override
    public void delStudent(int stuId) {

        stuDao.delStudent(stuId);
    }

    /**
     * 根据学生编号，修改学生的房间信息
     * @param stuId   学生id
     * @param roomId   新房间id
     */
    @Override
    public void changeRoom(int stuId, int roomId) {
        stuDao.changeRoom(stuId,roomId);
    }

    /**
     * 根据id查询学生信息
     * @param id  学生id
     * @return  学生对象
     */
    @Override
    public StudentBean findStuByStuId(int id) {
        return stuDao.findById(id);
    }


    /**
     * 查询学生信息
     * 1、按姓名，班级，所在房间动态查询学生信息
     * 2、查询学生房间地址和班级名称（sql语句联表查询）
     * @param name  学生姓名
     * @param stuClass  学生所在班级
     * @param address  学生所住地址
     * @return  学生集合
     */
    @Override
    public List<StudentBean> findByItem(String name, String stuClass, String address) {
        return stuDao.findByItem(name, stuClass, address);
    }





    /**
     * 查询房间中的以住学生集合，sql语句联表查询，同时查询学生班级名称
     * @param roomId
     * @return
     */
    @Override
    public List<StudentBean> findStuByRoomId(int roomId) {
        return stuDao.findStudentByRoomId(roomId);
    }

    @Override
    public String findClassByStudentId(int studentId) {
        return stuDao.findClassByStudentId(studentId);
    }
}
