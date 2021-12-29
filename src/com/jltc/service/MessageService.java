package com.jltc.service;

import com.jltc.domain.Message;
import com.jltc.domain.PageBean;

public interface MessageService {
	/**
	 * 用户发表留言功能
	 * @param message
	 * @return
	 */
	String addMessage(Message message);
	/**
	 * 用户查询本人留言和全部留言的功能
	 * @param curpage
	 * @param pageSize
	 * @param message
	 * @return
	 */
	PageBean<Message> userSelectMessage(int curpage, int pageSize, Message message);
	/**
	 * 管理员查询所有留言功能
	 * @param curpage
	 * @param pageSize
	 * @param msg
	 * @return
	 */
	PageBean<Message> messageManager(int curpage, int pageSize, Message msg);
	/**
	 * 管理员查看所有留言后 删除留言功能
	 * @param _id
	 * @return
	 */
	String deleteMessage(Integer _id);
	/**
	 * 管理员查询用户留言后 将此id用户的留言显示在修改界面内
	 * @param _id
	 * @return
	 */
	Message adminSelectMessageById(Integer _id);
	/**
	 * 管理员回复留言功能（顺带改改提问 回答不了就解决提问题的人）
	 * @param message2
	 * @return
	 */
	String adminUpdateMessageReply(Message message2);

}
