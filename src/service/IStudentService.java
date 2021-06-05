package service;

import bean.StudentBean;

import java.util.List;

public interface IStudentService {
    /*
    *//**
     * 查寻所有学生信息
     *//*
    public List<StudentBean> findAllStu();
*/
    /**
     *
     * 1、按姓名，班级，所在房间动态查询学生信息
     * 2、查询学生房间地址和班级名称（sql语句联表查询）
     * @param name  学生姓名
     * @param stuClass  学生所在班级
     * @param address  学生所住地址
     * @return  学生集合
     */
    public List<StudentBean> findByItem(String name, String stuClass, String address);


    /**
     *
     * 1、添加学生
     * @param student  学生实体类
     */
    public void addStudent(StudentBean student);

    /**
     * 按id给学生退房
     * 1、删除学生
     * @param stuId  学生id
     */
    public void delStudent(int stuId);

    /**
     * 根据学生编号，修改学生的房间信息
     * @param stuId   学生id
     * @param roomId   新房间id
     */
//    public void update(int stuId,String address);
    public void changeRoom(int stuId,int roomId);

    /**
     * 根据id查询学生信息,同时查询班级名称和房间地址
     * @param id  学生id
     * @return  学生对象
     */
    public StudentBean findStuByStuId(int id);


    /**
     * 查询房间中的以住学生集合，sql语句联表查询，同时查询学生班级名称
     * @param roomId
     * @return
     */
    public List<StudentBean> findStuByRoomId(int roomId);

//--------------------------------------------

    /**
     * 根据学生id查找班级
     * @return 班级名称
     */
    public String findClassByStudentId(int studentId);
}
