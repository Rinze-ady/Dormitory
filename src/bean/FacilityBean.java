package bean;

import java.time.LocalDate;

/**
 * 房间设施实体类
 */
public class FacilityBean {
    /**设施编号*/
    private int facilityId;
    /**损坏描述*/
    private String destroyInfo;
    /**损坏日期*/
    private LocalDate destroyDate = LocalDate.now();
    /**解决状态*/
    private String deal = "未解决";

    /**设施对应房间编号*/
    private RoomBean facRoomBean;

//    --------------------------
    /**房间设施数量*/
    private int facilityNum = 0;
//    ------------------------

    public FacilityBean() {
    }

/*    public FacilityBean(String destroyInfo,int roomId) {
        this.destroyInfo = destroyInfo;
        this.facRoomBean = new RoomBean();
        this.facRoomBean.setRoomId(roomId);
//        this.deal = deal;
    }*/

    public FacilityBean(String destroyInfo,LocalDate destroyDate, String deal, int roomId) {
        this.destroyInfo = destroyInfo;
        this.destroyDate = destroyDate;
        this.deal = deal;
        this.facRoomBean = new RoomBean();
        this.facRoomBean.setRoomId(roomId);
//        this.deal = deal;
    }



    public RoomBean getFacRoomBean() {
        return facRoomBean;
    }

    public void setFacRoomBean(RoomBean facRoomBean) {
        this.facRoomBean = facRoomBean;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getDestroyInfo() {
        return destroyInfo;
    }

    public void setDestroyInfo(String destroyInfo) {
        this.destroyInfo = destroyInfo;
    }

    public LocalDate getDestroyDate() {
        return destroyDate;
    }

    public void setDestroyDate(LocalDate destroyDate) {
        this.destroyDate = destroyDate;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public int getFacilityNum() {
        return facilityNum;
    }

    public void setFacilityNum(int facilityNum) {
        this.facilityNum = facilityNum;
    }

    @Override
    public String toString() {
        return "FacilityBean{" +
                "facilityId=" + facilityId +
                ", destroyInfo='" + destroyInfo + '\'' +
                ", destroyDate=" + destroyDate +
                ", deal='" + deal + '\'' +
                ", facRoomBean=" + facRoomBean +
                '}';
    }
}
