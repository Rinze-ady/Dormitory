package bean;

import java.util.List;

/**
 * 房间实体类
 */
public class RoomBean {
    /**房间id*/
    private int roomId;
    /**房间地址*/
    private String roomAddress;
    /**可容纳人数*/
    private int canNum;
    /**房间状态*/
    private String status = "正常";
    /**已住人数*/
    private int useNum;
    /**房间类型*/
    private String type;
    /**学生集合*/
    private List<StudentBean> list;



    public RoomBean() {
    }

    public RoomBean(int id){
        this.roomId = id;
    }

    public RoomBean(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public RoomBean(String roomAddress, int canNum, String type) {
        this.roomAddress = roomAddress;
        this.canNum = canNum;
        this.type = type;
    }

    public RoomBean(String roomAddress, int canNum, String status, String type) {
        this.roomAddress = roomAddress;
        this.canNum = canNum;
        this.status = status;
        this.type = type;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public int getCanNum() {
        return canNum;
    }

    public void setCanNum(int canNum) {
        this.canNum = canNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StudentBean> getList() {
        return list;
    }

    public void setList(List<StudentBean> list) {
        this.list = list;
    }

    /*@Override
    public String toString() {
        return "RoomBean{" +
                "roomId=" + roomId +
                ", roomAddress='" + roomAddress + '\'' +
                ", canNum=" + canNum +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}' + "\n";
    }*/

    @Override
    public String toString() {
        return this.roomAddress;
    }
}
