package com.jltc.service.impl;

import com.jltc.constant.Constant;
import com.jltc.dao.MessageDao;
import com.jltc.dao.impl.MessageDaoImpl;
import com.jltc.domain.Message;
import com.jltc.domain.PageBean;
import com.jltc.service.MessageService;

public class MessageServiceImpl implements MessageService {

	public String addMessage(Message message) {
		MessageDao InsertMessage = new MessageDaoImpl();
		try{
		boolean flag=InsertMessage.addMessage(message);
		if(flag){
			return Constant.SUCCESS;
		}else{
			return Constant.FAIL;
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public PageBean<Message> userSelectMessage(int curpage, int pageSize, Message message) {
		PageBean<Message> pb=new PageBean<>();
		pb.setPagesize(pageSize);
		try {
			MessageDao messageDao = new MessageDaoImpl();
			pb.setTotaldata(messageDao.userSelectMessageCount(message));
			pb.setTotalpage();
			if(curpage<1 || curpage>pb.getTotalpage()){
				curpage=1;
			}
			pb.setCurpage(curpage);
			pb.setList(messageDao.userSelectMessage(curpage, pageSize, message));
			return pb;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public PageBean<Message> messageManager(int curpage, int pageSize, Message msg) {
		PageBean<Message> pb=new PageBean<>();
		pb.setPagesize(pageSize);
		try {
			MessageDao MessageDao=new MessageDaoImpl();
			pb.setTotaldata(MessageDao.messageManagerCount(msg));
			pb.setTotalpage();
			if(curpage<1 || curpage>pb.getTotalpage()){
				curpage=1;
			}
			pb.setCurpage(curpage);
			pb.setList(MessageDao.messageManager(curpage, pageSize, msg));
			return pb;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String deleteMessage(Integer _id) {
		try {
			MessageDao InsertMessage = new MessageDaoImpl();
			Boolean result = InsertMessage.deleteMessage(_id);
			if (result) {
				return Constant.SUCCESS;
			} else {
				return Constant.FAIL;
			} 
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Message adminSelectMessageById(Integer _id) {
		MessageDao InsertMessage=new MessageDaoImpl();
		try {
			return InsertMessage.adminSelectMessageById(_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String adminUpdateMessageReply(Message message2) {
		MessageDao Message = new MessageDaoImpl();
		boolean result = Message.adminUpdateMessageReply(message2);
		if(result){
			return Constant.SUCCESS;
		}else{
			return Constant.FAIL;
		}
	}
	}


