package com.ray.entity;


import org.springframework.stereotype.Component;


import java.io.Serializable;

/**
 * User
 *
 * @author ray
 *
 */
@Component
public class User implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String userNo;


    private String userName;
    
    private Role role;
    
    private String userPwd;
    
    private Integer maxScore;
    
    private byte[] userPic;
    

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public byte[] getUserPic() {
		return userPic;
	}

	public void setUserPic(byte[] userPic) {
		this.userPic = userPic;
	}

}
