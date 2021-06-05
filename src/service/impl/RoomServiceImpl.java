package service.impl;

import bean.RoomBean;
import dao.IFacilityDao;
import dao.IRoomDao;
import dao.IStudentDao;
import dao.impl.FacilityDaoImpl;
import dao.impl.RoomDaoImpl;
import dao.impl.StudentDaoImpl;
import service.IRoomService;

import javax.swing.*;
import java.util.List;

public class RoomServiceImpl implements IRoomService {
    //房间持久层接口对象
    private IRoomDao roomDao = new RoomDaoImpl();

    //学生持久层接口对象
    private IStudentDao stuDao = new StudentDaoImpl();

    //房间设施持久层接口对象
    private IFacilityDao facilityDao = new FacilityDaoImpl();


    /**
     * 查询所有房间
     */
    @Override
    public List<RoomBean> findAllRoom() {
        return roomDao.findAllRoom();
    }

    /**
     * 添加房间
     * @param room   房间实体类
     */
    @Override
    public void addRoom(RoomBean room) {
        roomDao.addRoom(room);
    }


    /**
     * 根据房间编号删除房间
     * 1、查询该房间的以住人数，
     * 1、如果不为0，返回false（if条件判断）
     * 2、按房间的ID删除该房间的设施记录（级联删除）
     * 3、按ID删除房间
     * @param roomId  房间id
     * @return  如果房间还有人，返回“该房间还有人，不能删除”，如果房间没有，返回null
     */
    @Override
    public Boolean delRoom(int roomId) {
        if (roomDao.everRoomNum(roomId) > 0) {
            JOptionPane.showMessageDialog(null,"该房间还有人住，不能删除");
            return false;
        }
        facilityDao.delFacility(roomId);
        roomDao.delRoom(roomId);
        return true;
    }



    /**
     * 查看房间信息
     * 1、按房间编号查看房间信息
     * 2、查询房间已住人数，
     * 3、查询房间学生信息，
     * 4、在学生信息中需要查询班级的名称
     * @param roomId  房间编号
     * @return  房间信息
     */
    @Override
    public RoomBean findRoomById(int roomId) {
        return roomDao.findByRoomId(roomId);
    }

    @Override
    public List<RoomBean> findByRoomItem(String address, String status, String notFullRoom, String type) {
        return roomDao.findByRoomItem(address,status,notFullRoom,type);
    }

    @Override
    public RoomBean findByRoomAddress(String address) {
        return roomDao.findByRoomAddress(address);
    }

    @Override
    public List<RoomBean> findBySex(String sex) {
        return roomDao.findBySex(sex);

    }

    @Override
    public int findStudentNumByRoomId(int roomId) {
        return roomDao.everRoomNum(roomId);
    }

    /**
     * 查看房间信息
     * 1、按房间地址、房间状态、是否可住、房间类型来动态查询房间信息，
     * 2、查询学生类,统计每个房间以住人数（辅）
     * @param address  房间地址
     * @param status   房间状态
     * @param useNum   是否可住    需根据已住人数判断该房间是否可住。
     * @param type  房间类型
     * @return  房间集合
     */


}
