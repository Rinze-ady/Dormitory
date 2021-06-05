package dao.impl;

import bean.RoomBean;
import bean.StudentBean;
import dao.BaseDao;
import dao.IRoomDao;
import dao.IStudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl extends BaseDao implements IRoomDao {


    /**
     * 添加房间
     * @param room   房间实体类
     */
    @Override
    public void addRoom(RoomBean room) {
        this.setConnection();

        String str = "insert into t_room(r_address,r_capacity,r_roomStatus,r_roomType)values(?,?,?,?)";

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,room.getRoomAddress());
            ps.setObject(2,room.getCanNum());
            ps.setObject(3,room.getStatus());
            ps.setObject(4,room.getType());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    @Override
    public void delRoom(int roomId) {
        this.setConnection();

        String str = "delete from t_room where pk_roomId=?";

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

    @Override
    public void changeRoomStatus(int roomId) {
        this.setConnection();
        try {
            ps=con.prepareStatement("update t_room set r_roomStatus='正常' where pk_roomId=?");
            ps.setObject(1,roomId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
    }

    @Override
    public void changeRoomStatus1(int roomId) {
        this.setConnection();
        try {
            ps=con.prepareStatement("update t_room set r_roomStatus='设施损坏' where pk_roomId=?");
            ps.setObject(1,roomId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
    }

    @Override
    public int everRoomNum(int roomId) {
        RoomBean roomBean=new RoomBean();
        this.setConnection();

        try {
            ps=con.prepareStatement("SELECT COUNT(pk_studentId) b FROM t_student s JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId` WHERE r.`pk_roomId`=? GROUP BY r.`r_address`");
            ps.setObject(1,roomId);
            rs=ps.executeQuery();
            if (rs.next()){
                roomBean.setUseNum(rs.getInt("b"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomBean.getUseNum();
    }

    /**
     * 查询房间
     * 1、按房间ID查询
     * 查询房间信息，统计以住人数
     * @param roomId 房间ID
     * @return 房间信息实体类
     */
    @Override
    public RoomBean findByRoomId(int roomId) {

        RoomBean room=new RoomBean();
        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT pk_roomId,r.`r_capacity`,COUNT(s.`pk_studentId`) s,r_address,r_roomStatus,r_roomType,s_name,c_className,s_phone FROM t_room r LEFT JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId` LEFT JOIN t_class c ON c.`pk_classId`=s.`fk_classId` GROUP BY r.`pk_roomId` HAVING pk_roomId=?");
            ps.setObject(1,roomId);
            rs=ps.executeQuery();
            if (rs.next()){
                room.setRoomId(rs.getInt("pk_roomId"));
                room.setRoomAddress(rs.getString("r_address"));
                room.setCanNum(rs.getInt("r_capacity"));
                room.setStatus(rs.getString("r_roomStatus"));
                room.setType(rs.getString("r_roomType"));
                room.setUseNum(rs.getInt("s"));
//                room=fullRoom();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
        return room;
    }

    /**
     * 查询所有房间
     */
    @Override
    public List<RoomBean> findAllRoom() {

        List<RoomBean>list=new ArrayList<>();
        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT pk_roomId,r.`r_capacity`,COUNT(s.`pk_studentId`) s,r_address,r_roomStatus,r_roomType FROM t_room r LEFT JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId`GROUP BY r.`pk_roomId`");
            rs=ps.executeQuery();
            while (rs.next()){
                RoomBean room=new RoomBean();
                room.setRoomId(rs.getInt("pk_roomId"));
                room.setRoomAddress(rs.getString("r_address"));
                room.setCanNum(rs.getInt("r_capacity"));
                room.setStatus(rs.getString("r_roomStatus"));
                room.setType(rs.getString("r_roomType"));
                room.setUseNum(rs.getInt("s"));
                list.add(room);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    @Override
    public List<RoomBean> findByRoomItem(String address, String status, String notFullRoom, String type) {
        List<RoomBean>list=new ArrayList<>();
        this.setConnection();
//        String sql="SELECT * FROM t_room r left JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId` where 1=1 ";
        String sql= "SELECT pk_roomId,r.`r_capacity`,COUNT(s.`pk_studentId`) s,r_address,r_roomStatus,r_roomType FROM t_room r LEFT JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId` where 1=1 ";
        if (address!=null&&address.length()!=0){
            sql+=" and r_address like'%"+address+"%'";
        }
        if (status!=null&&!status.equals("不限")){
            sql+=" and r_roomStatus='"+status+"'";
        }
        if (type!=null&&!type.equals("不限")){
            sql+=" and r_roomType='"+type+"'";
        }
        sql+=" GROUP BY r.`pk_roomId` ";
        if (notFullRoom!=null&&!type.equals("不限")){
            sql+="HAVING COUNT(s.`pk_studentId`)<r.`r_capacity`";

        }
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                RoomBean room=new RoomBean();
                room.setRoomId(rs.getInt("pk_roomId"));
                room.setRoomAddress(rs.getString("r_address"));
                room.setCanNum(rs.getInt("r_capacity"));
                room.setStatus(rs.getString("r_roomStatus"));
                room.setType(rs.getString("r_roomType"));
                room.setUseNum(rs.getInt("s"));
                list.add(room);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }





    @Override
    public RoomBean findByRoomAddress(String address) {
        RoomBean roomBean=new RoomBean();
        this.setConnection();
        try {
            ps=con.prepareStatement("select * from t_room where r_address=?");
            ps.setObject(1,address);
            rs=ps.executeQuery();
            while (rs.next()){
                roomBean=fullRoom();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomBean;
    }

    @Override
    public List<RoomBean> findBySex(String sex) {
        List<RoomBean>list=new ArrayList<>();

        this.setConnection();
        try {
            ps=con.prepareStatement("SELECT * FROM t_student s  JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE r_roomType=? GROUP BY r_address HAVING r_capacity>COUNT(pk_studentId)");
            ps.setObject(1,sex);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(fullRoom());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }








    /**
     * 从结果集中得到数据，封装实体对象
     *表中的外键不在表示层显示，在实体类中定义的其他实体类对象属性不用在此处操作
     */
    private RoomBean fullRoom(){
        RoomBean ro = new RoomBean();

        try {
            ro.setRoomId(rs.getInt("pk_roomId"));
            ro.setRoomAddress(rs.getString("r_address"));
            ro.setCanNum(rs.getInt("r_capacity"));
            ro.setStatus(rs.getString("r_roomStatus"));
//            ro.setUseNum(rs.getInt(""));
            ro.setType(rs.getString("r_roomType"));
            //学生集合？？？？

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ro;
    }

    public static void main(String[] args) {
        //依赖倒置原则，接口引用接口实现类对象
        IRoomDao dao = new RoomDaoImpl();
//        System.out.println(dao.findAllRoom());
//        dao.addRoom(new RoomBean("望江路1号",8,"女生宿舍"));
//        dao.updateState(3, "正常");
        dao.delRoom(6);
    }
}
