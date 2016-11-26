/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javabeans.Recommend;
import javabeans.Reset;
import javabeans.TempUser;

/**
 *
 * @author Harish
 */
public class RecommendDB {
    public static int addUser(Recommend rec) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "INSERT INTO recuser "
	                               + "VALUES (?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
                    //java.sql.Date sqlDate=new Date(rec.getDate().getTime());
	            
                    ps.setString(1, rec.getEmail());
	            ps.setString(2, rec.getToken());
                    //ps.setDate(3, sqlDate);//tempuser.getDate().getTime());
                    
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    public static Recommend getUser(String token) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from recuser WHERE token = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, token);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Recommend rec = new Recommend();
	            	rec.setEmail(rs.getString("email"));
                        //rec.setDate(rs.getDate("expiration"));
                        rec.setToken(token);
	            	return rec;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
    public static int deleteUser(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "DELETE from recuser where token= ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, token);
                    return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
    }
}
