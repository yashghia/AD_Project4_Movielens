/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Harish
 */
public class Recommend implements Serializable{
    private String email;
    private String token;
    //private Date date;
    public Recommend() {
        
    }
    public Recommend(String email,String token) {
        this.email=email;
        this.token=token;
        //this.date=date;
    }
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
        
	public void setToken(String token) {
		this.token = token;
	}
        public String getToken() {
		return token;
	}
	
}
