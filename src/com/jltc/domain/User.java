package com.jltc.domain;

import java.util.Date;

public class User {
	private Integer id;
	private String username;
	private String userpwd;
	private String userxm;
	private Date birthday;
	private String email;
	private String gender;
	private String headpic;
	private String role;

	public User() {
		super();
	}

	public User(Integer id, String username, String userpwd, String userxm, Date birthday, String email, String gender,
			String headpic, String role) {
		super();
		this.id = id;
		this.username = username;
		this.userpwd = userpwd;
		this.userxm = userxm;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
		this.headpic = headpic;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserxm() {
		return userxm;
	}

	public void setUserxm(String userxm) {
		this.userxm = userxm;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", userpwd=" + userpwd + ", userxm=" + userxm
				+ ", birthday=" + birthday + ", email=" + email + ", gender=" + gender + ", headpic=" + headpic
				+ ", role=" + role + "]";
	}

}
