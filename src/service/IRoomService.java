package service;

import bean.RoomBean;

import java.util.List;

public interface IRoomService {
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
     *
     * 1、查询该房间的以住人数，如果不为0，返回false（if条件判断）
     *
     * 2、删除房间的设施记录，按房间的ID删除（级联删除）
     * 3、删除房间，按房间ID删除
     * @param roomId  房间id
     * @return  如果房间还有人，返回“该房间还有人，不能删除”，如果房间没有，返回null
     */
    public Boolean delRoom(int roomId);

    /**
     * 查看房间信息
     * 1、按房间编号查看房间信息
     * 2、查询房间已住人数，
     * 3、查询房间学生信息，
     * 4、查询班级的名称，在学生信息中需要
     * @param roomId  房间编号
     * @return  房间信息
     */
    public RoomBean findRoomById(int roomId);


    /**
     * 查看房间信息
     * 1、按房间地址、房间状态、是否可住、房间类型来动态查询房间信息，
     * 2、查询学生类,统计每个房间以住人数（辅）
     * @param address  房间地址
     * @param status   房间状态
     * @param notFullRoom   是否可住    需根据已住人数判断该房间是否可住。
     * @param type  房间类型
     * @return  房间集合
     */
    public List<RoomBean> findByRoomItem(String address, String status, String notFullRoom, String type);
//---------------------------------------------------
    /**
     * 根据房间地址查找房间信息
     * @param address 房间地址
     * @return 房间对象
     */
    public RoomBean findByRoomAddress(String address);



    /**
     * 根据性别查找可住房间
     * @param sex 性别
     * @return 房间对象
     */
    public List<RoomBean> findBySex(String sex);


    public int findStudentNumByRoomId(int roomId);

}
