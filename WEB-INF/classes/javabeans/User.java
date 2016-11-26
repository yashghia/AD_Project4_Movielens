package javabeans;

import java.io.Serializable;

import util.Hashpassword;
/**
 * User model implements serializable and contains User fields
 * @author Krishna
 * @version 1.0
 * @since 2016-02-27
 */
public class User implements Serializable{
	
	private String name;
	private String email;
	private String userType;
	private String password;
	private String salt;
	private int numCoins;
	private int numPostedStudies;
	private int numParticipation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = Hashpassword.hashAndSaltPassword(password,this.salt);
	}
        public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
//	public String getConfirmPassword() {
//		return confirmPassword;
//	}
//	public void setConfirmPassword(String confirmPassword) {
//		this.confirmPassword = confirmPassword;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getNumCoins() {
		return numCoins;
	}
	public void setNumCoins(int numCoins) {
		this.numCoins = numCoins;
	}
	public int getNumPostedStudies() {
		return numPostedStudies;
	}
	public void setNumPostedStudies(int numPostedStudies) {
		this.numPostedStudies = numPostedStudies;
	}
	public int getNumParticipation() {
		return numParticipation;
	}
	public void setNumParticipation(int numParticipation) {
		this.numParticipation = numParticipation;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
        public User(String name, String email, String userType, String password, String salt, int numCoins,
			int numPostedStudies, int numParticipation,boolean flag) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
                this.salt=salt;
                if(!flag) {
                    this.password = password;
                    System.out.println("false " + password);
                } else {
                    this.password = Hashpassword.hashAndSaltPassword(password,salt);
                    System.out.println("true " + password);
                }
		//this.confirmPassword = confirmPassword;
		this.numCoins = numCoins;
		this.numPostedStudies = numPostedStudies;
		this.numParticipation = numParticipation;
	}
	
	public User(String name, String email, String userType) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", userType=" + userType + ", numCoins=" + numCoins
				+ ", numPostedStudies=" + numPostedStudies + ", numParticipation=" + numParticipation + "]";
	}
	

}
