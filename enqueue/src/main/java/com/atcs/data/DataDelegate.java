package com.atcs.data;

import com.atcs.objects.Plane;
import com.atcs.objects.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DataDelegate {
    /**
     * This method inserts the plane into the queue.
     * @param plane - The object to insert
     * @return status - This indicates if the insertion was successful.
     */
    public Status addPlaneToQueue(Plane plane,String connection) throws SQLException {
        Connection connect =  DriverManager.getConnection(connection);
        Status status = new Status();
        if(isAlreadyInQueue(plane.getId(),connection)) {
            status.setError(true);
            status.setErrorMessage("Id '" + plane.getId() + "' is already in the queue.");
        } else {
            String sql = "insert into air_traffic_queue (plane_id,type,size) ";
            sql = sql + "values (" + String.valueOf(plane.getId()) + ",'" + plane.getType().toUpperCase()+"',";
            sql = sql + "'" + plane.getSize().toUpperCase() + "')";
            try {
                Statement statement = connect.createStatement();
                statement.execute(sql);
            } catch (SQLException throwables) {
                System.out.println("sql = " + sql);
                throwables.printStackTrace();
                status.setError(true);
                status.setErrorMessage(throwables.getMessage());
            }
        }
        return status;
    }

    /**
     * This method checks if the id is already in the queue
     * @param id
     * @return true or false
     */
    private boolean isAlreadyInQueue(int id,String connection) {
        List<Plane> planeList = getPlaneList(connection);
        if(planeList != null) {
            for(Plane plane: planeList) {
                if(id == plane.getId()) {
                    return true;
                }
            }
        }
        return false;
    }


    public List<Plane> getPlaneList(String connection) {
        List<Plane> planeList = new ArrayList<>();
        try {
            Connection connect =  DriverManager.getConnection(connection);
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
            while(rs.next()) {
                int id = rs.getInt("plane_id");
                String type = rs.getString("type");
                String size = rs.getString("size");
                Plane plane = new Plane();
                plane.setId(id);
                plane.setSize(size);
                plane.setType(type);
                planeList.add(plane);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return planeList;
    }

}
