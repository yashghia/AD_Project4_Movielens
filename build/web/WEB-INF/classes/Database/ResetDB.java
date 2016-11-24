package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javabeans.Reset;
import javabeans.TempUser;

/**
 *
 * @author Harish
 */
public class ResetDB {
    public static int addUser(Reset reset) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "INSERT INTO reset "
	                               + "VALUES (?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
                    java.sql.Date sqlDate=new Date(reset.getDate().getTime());
	            
                    ps.setString(1, reset.getEmail());
	            ps.setString(2, reset.getToken());
                    ps.setDate(3, sqlDate);//tempuser.getDate().getTime());
                    
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    public static Reset getUser(String token) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from reset WHERE token = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, token);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Reset reset = new Reset();
	            	reset.setEmail(rs.getString("email"));
                        reset.setDate(rs.getDate("expiration"));
                        reset.setToken("token");
	            	return reset;
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
    public static int deleteUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "DELETE from tempuser where email= ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
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
