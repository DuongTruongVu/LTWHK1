package dao;


import model.Address;
import utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AddressDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Address> getAllAddress() {
        List<Address> addresss = new ArrayList<Address>();
        String query = "select * from address";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                addresss.add(new Address(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addresss;
    }

    public String getDefaultAddressByUserId(int id) {
        String query = "select * from address where user_id = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}


