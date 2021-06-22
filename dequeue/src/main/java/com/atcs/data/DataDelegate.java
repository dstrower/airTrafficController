package com.atcs.data;

import com.atcs.objects.DequeueResult;
import com.atcs.objects.Plane;
import com.atcs.objects.Status;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.*;

public class DataDelegate {

   String connectionUrl;

    public DataDelegate(String cl) {
        this.connectionUrl = cl;

    }

    public DequeueResult dequeuePlane() {
        Connection connect = null;
        try {
            connect = DriverManager
                    .getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DequeueResult dequeueResult = new DequeueResult();
        HashMap<Integer,Plane> planeMap = getPlaneList(connect);
        Set<Integer> planeSet = planeMap.keySet();
        Iterator<Integer> planeIt = planeSet.iterator();
        int queueId = 0;
        Plane plane = null;
        if(planeIt.hasNext()) {
           queueId = planeIt.next();
           plane = planeMap.get(queueId);
        }
        if(queueId == 0) {
            Status status = new Status();
            status.setError(true);
            status.setErrorMessage("There are no planes to remove from the queue.");
            dequeueResult.setStatus(status);
        } else {
           Status status = removePlane(queueId,connect);
           dequeueResult.setStatus(status);
           dequeueResult.setPlane(plane);
        }
        return dequeueResult;
    }

    private Status removePlane(int queueId,Connection connect) {
        Status status = new Status();
        try {
            Statement statement = connect.createStatement();
            String sql = "delete from air_traffic_queue where queue_id = " + String.valueOf(queueId);
           System.out.println("Delete sql = "+ sql);
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            status.setError(true);
            status.setErrorMessage(throwables.getMessage());
        }
        return status;
    }


    private HashMap<Integer,Plane> getPlaneList(Connection connect) {
        HashMap<Integer,Plane> planeMap = new HashMap<>();
        List<Plane> planeList = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            String sql = "select queue_id,plane_id,type,size, ";
            sql = sql + "case ";
            sql = sql + "when type = 'EMERGENCY' then 1 ";
            sql = sql + " when type = 'VIP' then 2 ";
            sql = sql + " when type = 'PASSENGER' then 3 ";
            sql = sql + " when type = 'CARGO' then 4 ";
            sql = sql + "else 5 ";
            sql = sql + "end as type_order, ";
            sql = sql + "case ";
            sql = sql + "when size = 'LARGE' then 1 ";
            sql = sql +  "when size = 'SMALL' then 2 ";
            sql = sql + "else 3 ";
            sql = sql + "end as size_order ";
            sql = sql + "from air_traffic_queue order by type_order,size_order,queue_id";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                int id = rs.getInt("plane_id");
                String type = rs.getString("type");
                String size = rs.getString("size");
                int queue_id = rs.getInt("queue_id");
                Plane plane = new Plane();
                plane.setId(id);
                plane.setSize(size);
                plane.setType(type);
                planeList.add(plane);
                planeMap.put(queue_id,plane);
            } else {
                planeMap.put(0,null);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return planeMap;
    }

}
