package dao;

import bean.RoomBean;

import java.util.List;

public interface IRoomDao {
    /**
     * 查询所有房间
     */
    public List<RoomBean> findAllRoom();


    /**
     * 添加房间
     * @param room   房间实体类
     */

    public void addRoom(RoomBean room);

    /**
     *删除房间
     * @param roomId
     */
    public void delRoom(int roomId);

    /**
     * 查询房间
     * 1、按房间ID查询
     * 查询房间信息，统计以住人数
     * @param roomId 房间ID
     * @return 房间信息实体类
     */
    public RoomBean findByRoomId(int roomId);




    /**
     * 动态查询房间信息
     * @param address
     * @param status
     * @param notFullRoom
     * @param type
     */
    public List<RoomBean> findByRoomItem(String address, String status, String notFullRoom, String type);


    /**
     * 查询每个房间的已住人数
     * @return
     */
    public int everRoomNum(int roomId);

    //改变房间状态未正常
    public void changeRoomStatus(int roomId);
    //改变房间状态为设备损坏
    public void changeRoomStatus1(int roomId);
    //按地址查询房间
    public RoomBean findByRoomAddress(String address);

    //根据性别查找房间
    public List<RoomBean> findBySex(String sex);
}
