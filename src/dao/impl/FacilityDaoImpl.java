package dao.impl;

import bean.FacilityBean;
import dao.BaseDao;
import dao.IFacilityDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacilityDaoImpl extends BaseDao implements IFacilityDao {
    /**
     * 查看房间设施的维护信息
     * 1、按房间ID查看房间设施信息
     * @param roomId 房间编号
     * @return 设施实体类集合
     */
    @Override
    public List<FacilityBean> findByCode(int roomId) {
        List<FacilityBean> list = new ArrayList<>();

        this.setConnection();
        String str = "select f.`pk_facilityId`,  f.`f_describe`, f.`f_reportDate`, f.`f_whetherSolve` from t_facility f where f.`fk_roomId` = ?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1, roomId);

            rs = ps.executeQuery();
            while (rs.next()){
                list.add(fullFacility());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return list;

    }

    @Override
    public void addInfo(FacilityBean fa) {

        this.setConnection();

        String str = "insert into t_facility(f_describe, f_reportDate,f_whetherSolve,fk_roomId) values(?,?,?,?)";

        try {
            ps = con.prepareStatement(str);
            //这里就体现了构造方法的左右，定义一个有三个形参的构造方法
            ps.setObject(1, fa.getDestroyInfo());
            ps.setObject(2, fa.getDestroyDate());
            ps.setObject(3,fa.getDeal());
            ps.setObject(4,fa.getFacRoomBean().getRoomId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
    }

    @Override
    public void deal(int facilityId, String state) {
        this.setConnection();

        String str = "update t_facility set f_whetherSolve = ? where pk_facilityId = ?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,state);
            ps.setObject(2,facilityId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 按房间id删除设施记录
     */
    @Override
    public void delFacility(int roomId) {

        this.setConnection();

        String str = "delete from t_facility where fk_roomId=?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1, roomId);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     * 统计房间“未解决”设施数量
     * @param roomId
     * @param status
     * @return
     */
    @Override
    public int countByStatus(int roomId, String status) {

        FacilityBean facilityBean = new FacilityBean();

        this.setConnection();

        String str = "select count(f.`f_whetherSolve`) cf from t_facility f where f.`fk_roomId`=? and f.`f_whetherSolve`= ?";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,roomId);
            ps.setObject(2,status);

            rs = ps.executeQuery();
            if(rs.next()){
                facilityBean.setFacilityNum(rs.getInt("cf"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return facilityBean.getFacilityNum();
    }



    /**
     * 从结果集得到数据，封装实体对象
     * 表中的外键不在表示层显示，在实体类中定义的其他实体类对象属性不用在此处操作
     * @return
     */
    private FacilityBean fullFacility(){
        FacilityBean fa = new FacilityBean();

        try {
//            fa.setFacilityId(rs.getInt("pk_facilityId"));
            fa.setDestroyInfo(rs.getString("f_describe"));
            fa.setDestroyDate(LocalDate.parse(rs.getString("f_reportDate")));
            fa.setDeal(rs.getString("f_whetherSolve"));
//            fa.setRoomId(rs.getInt("fk_roomId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return fa;
    }





    public static void main(String[] args) {
        //依赖倒置原则，接口引用接口实现类对象
        IFacilityDao dao = new FacilityDaoImpl();
//        System.out.println(dao.findAllByRoom(2));
//        System.out.println(dao.countByStatus(1,"未解决"));
//        dao.delFacility(1);
//        dao.addInfo(new FacilityBean("地板坏了",LocalDate.parse("2020-05-05"),"未解决", 1));
        dao.deal(2,"已解决");

    }
}
