package service;

import bean.FacilityBean;
import bean.RoomBean;

import java.util.List;

public interface IFacilityService {

    /**
     * 查询房间设施信息，按编号查询
     *
     * @param roomId 房间编号
     * @return 设施实体类集合
     */
    public List<FacilityBean> findByCode(int roomId);

    /**
     * 添加设施损坏记录，形参未Bean实体类
     *
     * 修改对应房间状态为“设施损坏”
     * @param  facility   损坏设施实体类
     */
    public void addInfo(FacilityBean facility,int roomId);

    /**
     *
     * 1、修改房间设施记录，状态修改为已解决
     * 2、统计该房间‘未解决’设施数量
     * 3、如果该数为0，将该房间状态修改为”正常“
     * @param roomId 设施所在的房间编号
     * @param facilityId  损坏设施编号
     *
     */
    public void deal(int roomId, int facilityId);

}
