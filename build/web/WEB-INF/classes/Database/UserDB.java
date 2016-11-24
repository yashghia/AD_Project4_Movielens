package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javabeans.User;
import util.Hashpassword;

public class UserDB {


	 public static String validateUser(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM user WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            
	            rs= ps.executeQuery();
	            while (rs.next())
	            {
                        return rs.getString("salt");
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return null;
	    }
         public static boolean selectUser(String email,String pwd,String salt) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM user WHERE username = ? and password=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2,Hashpassword.hashAndSaltPassword(pwd, salt));
	            rs= ps.executeQuery();
	            while (rs.next())
	            {
	             return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return false;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return false;
	    }
	  public static User getUser(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from user WHERE username = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
                        user.setSalt(rs.getString("salt"));
	            	user.setNumPostedStudies(rs.getInt("studies"));
	            	user.setNumParticipation(rs.getInt("participation"));
	            	user.setNumCoins(rs.getInt("coins"));
	               return user;
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
	  public static List<User> getUsers() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<User> users = new ArrayList();
	        String query = "SELECT * from user";
	        try {
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	               	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
	            	user.setNumPostedStudies(rs.getInt("studies"));
	            	user.setNumParticipation(rs.getInt("participation"));
	            	user.setNumCoins(rs.getInt("coins"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return users;
	    }

	 public static int addUser(User user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "INSERT INTO user "
	                + "(username, password, type, studies, participation, coins, name,salt) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getEmail());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getUserType());
	            ps.setInt(4, user.getNumPostedStudies());
	            ps.setInt(5, user.getNumParticipation());
	            ps.setInt(6, user.getNumCoins());
	            ps.setString(7, user.getName());
                    ps.setString(8, user.getSalt());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static void updateUserParticipation(String email, int parNum) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "Participation = ? "
	                + "WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, parNum);
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }

	 public static void updateUserCoins(String email, int coins) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "coins = ? "
	                + "WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, coins);
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
         public static void updatePassword(String email, String password,String salt) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "password = ?" 
	                + "WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
                    //String salt=Hashpassword.getSalt();
	            ps.setString(1, Hashpassword.hashAndSaltPassword(password,salt));
	            ps.setString(2, email);
                    //ps.setString(3, salt);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
}
