package com.jltc.domain;

import java.util.Date;

public class Message {
	private Integer id;
	private String msgtype;
	private String title;
	private String content;
	private Date pubdate;
	private Date pubdateend;
	private String reply;
	private User user;
	private String usertype;

	public Message() {
		super();
	}

	public Message(Integer id, String msgtype, String title, String content, Date pubdate, Date pubdateend,
					String reply, User user, String usertype) {
		this.id = id;
		this.msgtype = msgtype;
		this.title = title;
		this.content = content;
		this.pubdate = pubdate;
		this.pubdateend = pubdateend;
		this.reply = reply;
		this.user = user;
		this.usertype = usertype;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public Date getPubdateend() {
		return pubdateend;
	}

	public void setPubdateend(Date pubdateend) {
		this.pubdateend = pubdateend;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message2{" +
				"id=" + id +
				", msgtype='" + msgtype + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", pubdate=" + pubdate +
				", pubdateend=" + pubdateend +
				", reply='" + reply + '\'' +
				", user=" + user +
				", usertype='" + usertype + '\'' +
				'}';
	}
}
