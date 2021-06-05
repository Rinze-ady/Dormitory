package dao;

import bean.ClassBean;

import java.time.LocalDate;
import java.util.List;

public interface IClassDao {
    /**
     * 查询所有班级
     */
    public List<ClassBean> findAllClass();

    /**
     * 添加班级
     * @param className 班级名称
     * @param teacher 带班老师
     * @param startDate 开班时间
     */
    public void addClass(String className, String teacher, LocalDate startDate);

    /**
     * 删除班级
     * @param classId
     */
//    public void delClass(int classId);
    public boolean delClass(int classId);
}
