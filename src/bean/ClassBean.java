package bean;

import java.time.LocalDate;
import java.util.List;

/**
 * 班级实体类
 */
public class ClassBean {
    /**班级id*/
    private int classId;
    /**班级名称*/
    private String className;
    /**开班时间*/
    private LocalDate startDate;
    /**带班老师*/
    private String teacher;

    /*
    *//**默认学生集合*//*
    private List<StudentBean> list;*/

//    ----------------------------------
    /**学生总数*/
    private int sumStudent=0;
//    -------------------------------------


    public ClassBean() {
    }

    public ClassBean(int classId) {
        this.classId = classId;
    }

    public ClassBean(String className) {
        this.className = className;
    }

    public ClassBean(String className, LocalDate startDate, String teacher) {
        this.className = className;
        this.startDate = startDate;
        this.teacher = teacher;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getSumStudent() {
        return sumStudent;
    }

    public void setSumStudent(int sumStudent) {
        this.sumStudent = sumStudent;
    }

    /*@Override
    public String toString() {
        return "ClassBean{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", startDate=" + startDate +
                ", teacher='" + teacher + '\'' +
                '}'+ "\n";
    }*/

    @Override
    public String toString() {
        return  this.className;
    }
}
