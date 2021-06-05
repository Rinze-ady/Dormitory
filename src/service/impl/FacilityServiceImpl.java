package service.impl;

import bean.FacilityBean;
import dao.IFacilityDao;
import dao.IRoomDao;
import dao.impl.FacilityDaoImpl;
import dao.impl.RoomDaoImpl;
import service.IFacilityService;

import java.util.List;

public class FacilityServiceImpl implements IFacilityService {

    //损坏设施持久层接口对象
    private IFacilityDao facilityDao = new FacilityDaoImpl();

    //房间持久层接口对象
    private IRoomDao roomDao = new RoomDaoImpl();

    /**
     * 查询某个房间的设施损坏情况
     * 1、按房间ID查看房间设施信息
     * @param roomId 房间编号
     * @return 设施实体类集合
     */
    @Override
    public List<FacilityBean> findByCode(int roomId) {
        return facilityDao.findByCode(roomId);
    }


    /**
     * 添加设施损坏记录
     * 1、按设施实体类添加设施损坏记录
     * 2、房间状态修改为“设备损坏”
     *
     */
    @Override
    public void addInfo(FacilityBean facility, int roomId) {
        roomDao.changeRoomStatus1(roomId);
        facilityDao.addInfo(facility);
    }




    /**
     * 处理房间已损坏设施
     * 1、将该设施记录状态修改为已解决
     * 2、统计该房间‘未解决’设施数量
     * 3、如果该数为0，将该房间状态修改为”正常“
     * @param roomId 设施所在的房间编号
     * @param facilityId  损坏设施编号
     *
     */
    @Override
    public void deal(int roomId, int facilityId) {
        facilityDao.deal(facilityId,"已解决");
        if (facilityDao.countByStatus(roomId,"未解决")<=0){
            roomDao.changeRoomStatus(roomId);
        }
    }

    /**
     * 按房间id删除设施记录
     */

}
