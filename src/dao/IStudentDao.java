package dao;

import bean.StudentBean;

import java.util.List;

public interface IStudentDao {
    /**
     * 查寻所有学生信息
     */
//    public List<StudentBean> findAllStu();

    /**
     * 学生入住
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
    public void changeRoom(int stuId,int roomId);

    /**
     * 根据id查询学生信息
     * @param id  学生id
     * @return  学生对象
     */
    public StudentBean findById(int id);

    /**
     * 查询学生信息
     * @param name
     * @param stuClass
     * @param address
     * @return
     */
    public List<StudentBean> findByItem(String name, String stuClass, String address);

    /**
     * 按班级id统计学生人数
     *
     */
    public int sumByClassId(int classId);


    /**
     * 查询班级名称
     * @param id 学生id
     * @return 返回班级名称
     */
    public String findClassName(int id);

    /**
     * 查询房间地址
     * @param id 学生id
     * @return 房间地址
     */
    public String findRoom(int id);



    /**
     * 根据房间id查询学生集合,同时查询班级名称和学生信息
     * @param roomId 房间id
     * @return 学生集合
     */
    public List<StudentBean> findStudentByRoomId(int roomId);

    /**
     * 根据学生id查找班级
     * @return 班级名称
     */
    public String findClassByStudentId(int studentId);


}
