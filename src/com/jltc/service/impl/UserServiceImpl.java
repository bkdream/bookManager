package com.jltc.service.impl;

import com.jltc.constant.Constant;
import com.jltc.dao.UserDao;
import com.jltc.dao.impl.UserDaoImpl;
import com.jltc.domain.PageBean;
import com.jltc.domain.User;
import com.jltc.domain.UserFormCheck;
import com.jltc.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(User user) {
		UserDao userDao =new UserDaoImpl();
		try{
			return userDao.login(user);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String updateUserMessage(User user) {
		UserFormCheck ufc = new UserFormCheck(user);
		String result = ufc.checkUserEditForm();
		if(!"".equals(result)){
			return result;
		}
		UserDao userDao =new UserDaoImpl();
		try{
			boolean flag=userDao.updateUserMessage(user);
			if(flag){
				return Constant.SUCCESS;
			}else{
				return Constant.FAIL;
			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String userReg(User user, String confirmpwd) {
		UserFormCheck ufc = new UserFormCheck(user,confirmpwd);
		String result = ufc.checkUserRegForm();
		if(!"".equals(result)){
			return result;
		}
		UserDao userDao =new UserDaoImpl();
		try{
			boolean flag=userDao.userReg(user);
			if(flag){
				return Constant.SUCCESS;
			}else{
				return Constant.FAIL;
			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String usernameExist(String username) {
		UserDao UserDao=new UserDaoImpl();
		try {
			String oldUserPassword=UserDao.usernameExist(username);
			return oldUserPassword;
		} catch (Exception e) {
			throw  new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String updateUserPassword(User user1,String confirmpwd,String newpwd) {
		UserFormCheck ufc = new UserFormCheck(user1,confirmpwd,newpwd);
		String result = ufc.checkUserEditPwdForm();
		if(!"".equals(result)){
			return result;
		}
		UserDao userDao =new UserDaoImpl();
		try{
			boolean flag=userDao.updateUserPassword(user1);
			if(flag){
				return Constant.SUCCESS;
			}else{
				return Constant.FAIL;
			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public PageBean<User> UserMessage(int curpage, int pageSize, User user) {
		PageBean<User> pb=new PageBean<>();
		pb.setPagesize(pageSize);
		try {
			UserDao userDao=new UserDaoImpl();
			pb.setTotaldata(userDao.adminSelectUserMessageCount(user));
			pb.setTotalpage();
			if(curpage<1 || curpage>pb.getTotalpage()){
				curpage=1;
			}
			pb.setCurpage(curpage);
			pb.setList(userDao.adminSelectUserMessage(curpage, pageSize, user));
			return pb;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String deleteUserMessage(Integer _id) {
		UserDao userDao =new UserDaoImpl();
		try{
			boolean flag=userDao.deleteUserMessage(_id);
			if(flag){
				return Constant.SUCCESS;
			}else{
				return Constant.FAIL;
			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public User adminSelectUserMessageById(Integer _id) {
		UserDao userDao =new UserDaoImpl();
		try{
			return userDao.adminSelectUserMessageById(_id);
		}catch(Exception e){
//			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String userPasswordExist(Integer id,String userpwd) {
		UserDao UserDao=new UserDaoImpl();
		try {
			String oldUserPassword=UserDao.userPasswordExist(id,userpwd);
			return oldUserPassword;
		} catch (Exception e) {
			throw  new RuntimeException(e.getMessage());
		}
	}

}
