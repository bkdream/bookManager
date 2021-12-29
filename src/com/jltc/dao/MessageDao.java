package com.jltc.dao;

import java.util.ArrayList;
import java.util.List;

import com.jltc.domain.Message;

public interface MessageDao {
	/**
	 * 用户发表留言功能
	 * @param message
	 * @return
	 */
	boolean addMessage(Message message);
	/**
	 * 返回用户查询留言信息总条数count(*)
	 * @param message
	 * @return
	 */
	Integer userSelectMessageCount(Message message);
	/**
	 * 返回用户查询留言信息内容
	 * @param curpage
	 * @param pageSize
	 * @param message
	 * @return
	 */
	ArrayList<Message> userSelectMessage(int curpage, int pageSize, Message message);
	/**
	 * 管理员查询所有留言的数据总数
	 * @param msg
	 * @return
	 */
	Integer messageManagerCount(Message msg);
	/**
	 * 管理员查看所有留言的数据集合
	 * @param curpage
	 * @param pageSize
	 * @param msg
	 * @return
	 */
	List<Message> messageManager(int curpage, int pageSize, Message msg);
	/**
	 * 管理员查看所有留言后 删除留言功能
	 * @param _id
	 * @return
	 */
	Boolean deleteMessage(Integer _id);
	/**
	 * 管理员查看所有留言后 删除留言功能
	 * @param _id
	 * @return
	 */
	Message adminSelectMessageById(Integer _id);
	/**
	 * 管理员回复留言功能（顺带改改提问 回答不了就解决提问题的人）
	 * @param message2
	 * @return
	 */
	boolean adminUpdateMessageReply(Message message2);

}
