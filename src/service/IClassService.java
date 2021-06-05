package service;

import bean.ClassBean;

import java.time.LocalDate;
import java.util.List;

public interface IClassService {
    /**
     * 查询所有班级
     */
    public List<ClassBean> findAllClass();


    /**
     * 添加班级
     * @param className
     * @param teacher
     * @param startDate
     */
    public void addClass(String className, String teacher, LocalDate startDate);

    /**
     * 按班级id删除班级
     * 1、按id号查询班级的学生人数
     * 2、若班级剩余学生人数为0，则删除失败
     * @param classId  班级id
     * @return  如果班级有人，则返回“该班级还有人，不能删除”，如果没人返回null
     */
    public boolean delClass(int classId);
//----------------------------------------------------------------
    /**
     * 按名称查找班级
     * @param className
     * @return
     */
    public ClassBean findClassByName(String className);

}
