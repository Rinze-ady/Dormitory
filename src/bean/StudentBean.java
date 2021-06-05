package bean;

import java.time.LocalDate;

/**
 * 学生实体类
 */
public class StudentBean {
    /**学生编号*/
    private int stuId;
    /**学生姓名*/
    private String stuName;
    /**学生性别*/
    private String stuSex;


    /**学生电话*/
    private String telNum;
    /**学生头像文件*/
    private String facePath;
    /**学生入住时间*/
    private LocalDate startDate = LocalDate.now();

    /**学生所在班级*/
    private ClassBean stuClassBean;
    /**学生所在房间*/
    private RoomBean stuRoomBean;

    public StudentBean(String stuName, String stuSex, int classId, int roomId,
                       String telNum, LocalDate startDate,String facePath) {
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuClassBean = new ClassBean();
        this.stuClassBean.setClassId(classId);
        this.stuRoomBean = new RoomBean();
        this.stuRoomBean.setRoomId(roomId);
        this.telNum = telNum;
        this.facePath = facePath;
        this.startDate = startDate;
        }

    public StudentBean() {
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public ClassBean getStuClassBean() {
        return stuClassBean;
    }

    public void setStuClassBean(ClassBean stuClassBean) {
        this.stuClassBean = stuClassBean;
    }

    public RoomBean getStuRoomBean() {
        return stuRoomBean;
    }

    public void setStuRoomBean(RoomBean stuRoomBean) {
        this.stuRoomBean = stuRoomBean;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


   /* @Override
    public String toString() {
        return "StudentBean{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", telNum='" + telNum + '\'' +
                ", face='" + face + '\'' +
                ", inDate=" + inDate +
                ", stuClass=" + stuClassBean +
                ", stuRoom=" + stuRoomBean +
                '}' + "\n";
    }*/

    @Override
    public String toString() {
        return this.stuName;
    }
}
