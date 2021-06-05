package service.impl;

import bean.ClassBean;
import dao.IClassDao;
import dao.IStudentDao;
import dao.impl.ClassDaoImpl;
import dao.impl.StudentDaoImpl;
import service.IClassService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ClassServiceImp implements IClassService {

    //班级持久层接口对象
    private IClassDao classDao = new ClassDaoImpl();

    /**
     * 学生持久层接口对象
     */
    private IStudentDao studentDao = new StudentDaoImpl();

    /**
     * 查询所有班级
     */
    @Override
    public List<ClassBean> findAllClass() {
        return classDao.findAllClass();
    }

    @Override
    public void addClass(String className, String teacher, LocalDate startDate) {
        classDao.addClass(className,teacher,startDate);
    }


    /**
     * 按班级id删除班级
     * 1、按id号查询班级的学生人数
     * 2、若班级剩余学生人数为0，则删除失败
     * @param classId  班级id
     * @return  如果班级有人，则返回“该班级还有人，不能删除”，如果没人返回null
     */
    @Override
    public boolean delClass(int classId) {
        if (studentDao.sumByClassId(classId)<=0){
            classDao.delClass(classId);
            return true;
        }
        JOptionPane.showMessageDialog(null,"该班级还有学生，不能删除");
        return false;

    }

    @Override
    public ClassBean findClassByName(String className) {
        return null;
    }
}
