package dao;

import bean.ClassBean;
import bean.FacilityBean;

import java.util.List;

public interface IFacilityDao {

    /**
     *
     * 1、按房间ID查看房间设施信息
     * @param roomId 房间编号
     * @return 设施实体类集合
     */
    public List<FacilityBean> findByCode(int roomId);

    /**
     * 添加
     */
    public void addInfo(FacilityBean fa);



    /**
     * 修改设施状态
     */
    public void deal(int setId, String state);



    /**
     * 按房间id删除设施记录
     */
    public void delFacility(int roomId);

    /**
     * 按房间ID和状态，统计设施数量
     * @param roomId
     * @param status
     * @return
     */
    public int countByStatus(int roomId, String status);
}
