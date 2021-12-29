package com.jltc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jltc.dao.UserDao;
import com.jltc.domain.User;
import com.jltc.utils.DateUtils;
import com.jltc.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {
	@Override
	public User login(User user) {
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 0.加载驱动
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();

			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "select id, username, userpwd, userxm, birthday, email, gender, headpic, role";
			sql += " from users where username='" + user.getUsername() + "' and userpwd='" + user.getUserpwd() + "'";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);

			// 4.执行SQL语句增、删、改方法
			rs = stmt.executeQuery(sql);
			User user1 = new User();
			if (rs.next()) {
				user1.setId(rs.getInt("id"));
				user1.setUsername(rs.getString("username"));
				user1.setUserpwd(rs.getString("userpwd"));
				user1.setUserxm(rs.getString("userxm"));
				user1.setBirthday(rs.getDate("birthday"));
				user1.setEmail(rs.getString("email"));
				user1.setGender(rs.getString("gender"));
				user1.setHeadpic(rs.getString("headpic"));
				user1.setRole(rs.getString("role"));
			}
			return user1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	@Override
	public boolean updateUserMessage(User user) {
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "update users set userxm='" + user.getUserxm() + "'," + "birthday='"
					+ DateUtils.DateToString(user.getBirthday()) + "'," + "email='" + user.getEmail() + "',gender='"
					+ user.getGender() + "'," + "headpic='" + user.getHeadpic() + "'";
			sql = sql + " where id='" + user.getId() + "'";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句增、删、改方法
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(stmt, conn);
		}
		return false;
	}
	@Override
	public boolean userReg(User user) {
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "INSERT INTO users(username, userpwd, userxm, birthday, email, gender, headpic, role)";
			sql = sql + " VALUES ('" + user.getUsername() + "', '" + user.getUserpwd() + "', '" + user.getUserxm()
					+ "', '" + DateUtils.DateToString(user.getBirthday()) + "','" + user.getEmail() + "', '"
					+ user.getGender() + "', '" + user.getHeadpic() + "', 'user')";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句增、删、改方法
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

			JDBCUtils.release(stmt, conn);

		}
		return false;
	}
	@Override
	public String usernameExist(String username) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "select username from users where username='" + username + "'";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);

			// 4.执行SQL语句增、删、改方法
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	@Override
	public boolean updateUserPassword(User user1) {
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "update users set userpwd='" + user1.getUserpwd() + "'";
			sql = sql + " where id=" + user1.getId();
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句增、删、改方法
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

			JDBCUtils.release(stmt, conn);

		}
	}

	@Override
	public Integer adminSelectUserMessageCount(User user) {
		// 声明连接对象conn
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			String sql = "SELECT count(*)";
			sql = sql + " FROM users";
			sql = sql + " WHERE 1=1";
			if (user != null && user.getUsername() != null && !"".equals(user.getUsername().trim()) && !"null".equals(user.getUsername())) {
				sql = sql + " AND username LIKE '%" + user.getUsername() + "%'";
			}
			if (user != null && user.getUserxm() != null && !"".equals(user.getUserxm().trim()) && !"null".equals(user.getUserxm())) {
				sql = sql + " AND userxm LIKE '%" + user.getUserxm() + "%'";
			}
			// 性别
			if (user != null && user.getGender() != null && !"all".equals(user.getGender()) && !"null".equals(user.getGender())) {
				sql = sql + " AND gender='" + user.getGender() + "'";
			}

			if (user != null && user.getRole() != null && !"".equals(user.getRole().trim()) && !"null".equals(user.getRole())) {
				sql = sql + " AND role='" + user.getRole() + "' ";
			}
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句查询方法
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public List<User> adminSelectUserMessage(int curpage, int pageSize, User user) {
		// 声明连接对象conn
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		// 声明返回数据列表集合对象
		ArrayList<User> lists = null;
		try {
			lists = new ArrayList<User>();
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			String sql = "SELECT id,username, userpwd, userxm, birthday, email, gender, headpic, role";
			sql = sql + " FROM users";
			sql = sql + " WHERE 1=1";
			if (user != null && user.getUsername() != null && !"".equals(user.getUsername().trim()) && !"null".equals(user.getUsername())) {
				sql = sql + " AND username LIKE '%" + user.getUsername() + "%'";
			}
			if (user != null && user.getUserxm() != null && !"".equals(user.getUserxm().trim()) && !"null".equals(user.getUserxm())) {
				sql = sql + " AND userxm LIKE '%" + user.getUserxm() + "%'";
			}
			// 性别
			if (user != null && user.getGender() != null && !"all".equals(user.getGender()) && !"null".equals(user.getGender())) {
				sql = sql + " AND gender='" + user.getGender() + "'";
			}

			if (user != null && user.getRole() != null && !"".equals(user.getRole().trim()) && !"null".equals(user.getRole())) {
				sql = sql + " AND role='" + user.getRole() + "' ";
			}
			sql = sql + " ORDER BY username";
			int pageIndex = (curpage - 1) * pageSize;
			sql = sql + " LIMIT " + pageIndex + "," + pageSize;
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句查询方法
			rs = stmt.executeQuery(sql);
			// 5.将结果集rs转换为ArrayList
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setUserpwd(rs.getString("userpwd"));
				u.setUserxm(rs.getString("userxm"));
				u.setBirthday(rs.getDate("birthday"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setHeadpic(rs.getString("headpic"));
				u.setRole(rs.getString("role"));
				lists.add(u);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	@Override
	public boolean deleteUserMessage(Integer _id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "delete from users";
			sql += " where id='"+_id+"'";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句增、删、改方法
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(stmt, conn);
		}
		return false;
	}
	@Override
	public User adminSelectUserMessageById(Integer _id) {
		Connection conn = null;
		// 声明命令对象stmt
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 0.加载驱动
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "select id, username, userpwd, userxm, birthday, email, gender, headpic, role";
			sql += " from users where id=" + _id + "";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);
			// 4.执行SQL语句增、删、改方法
			rs = stmt.executeQuery(sql);
			User user = new User();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setUserxm(rs.getString("userxm"));
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setHeadpic(rs.getString("headpic"));
				user.setRole(rs.getString("role"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	@Override
	public String userPasswordExist(Integer id,String userpwd) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1.获取Connection对象
			conn = JDBCUtils.getConnection();
			// 2.获取Statement对象
			stmt = conn.createStatement();
			// 3.定义SQL语句
			String sql = "select userpwd from users where id='" + id + "' and userpwd='"+userpwd+"'";
			// 在控制台输出sql语句，用以验证、调试sql语句是否正确
			System.out.println(sql);

			// 4.执行SQL语句增、删、改方法
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
}
