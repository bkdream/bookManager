package com.jltc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jltc.dao.MessageDao;
import com.jltc.domain.Message;
import com.jltc.domain.User;
import com.jltc.utils.DateUtils;
import com.jltc.utils.JDBCUtils;

public class MessageDaoImpl implements MessageDao {

	@Override
	public boolean addMessage(Message message) {
		Connection conn = null;
        // 声明命令对象stmt
        Statement stmt = null;

        try {

            // 0.加载驱动
            // 1.获取Connection对象
            conn = JDBCUtils.getConnection();

            // 2.获取Statement对象
            stmt = conn.createStatement();
            // 3.定义SQL语句
            String sql = "INSERT INTO msgs(msgtype,title,content,pubdate,reply,userid)";
            sql = sql + " VALUES ('" + message.getMsgtype() + "', '" + message.getTitle() + "', '" + message.getContent()
                    + "', '" + DateUtils.DateToString(message.getPubdate()) + "','', '" + message.getId() + "')";
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);

            // 4.执行SQL语句增、删、改方法
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {

            JDBCUtils.release(stmt, conn);

        }
        return false;
	}

	@Override
	public Integer userSelectMessageCount(Message message) {
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
            String sql = "SELECT count(*)";
            sql = sql + " FROM msgs";
            sql = sql + " WHERE 1=1";

            if (message != null) {
                //留言类别 一般/重要
                if (!"all".equals(message.getMsgtype())) {
                    sql += " and msgtype like '%" + message.getMsgtype() + "%'";
                }
                //留言开始和结束日期
                if (message.getPubdate() != null) {
                    if (message.getPubdateend() == null) {
                        sql += " and pubdate ='" + DateUtils.DateToString(message.getPubdate()).trim() + "'";
                    } else if (message.getPubdateend() != null) {
                        sql += " and pubdate between '" + DateUtils.DateToString(message.getPubdate()) + "' and '" +
                                DateUtils.DateToString(message.getPubdateend()) + "'";
                    }
                }
                //是否本人留言，还是全部留言
                if (!"all".equals(message.getUsertype())) {
                    sql += " and userid ='" + message.getUser().getId() + "'";
                }
                //留言的标题搜索
                if (message.getTitle() != null && !"".equals(message.getTitle())) {
                    sql += " and title like '%" + message.getTitle().trim() + "%'";
                }
            }
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);

            // 4.执行SQL语句增、删、改方法
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
	public ArrayList<Message> userSelectMessage(int curpage, int pageSize, Message message) {
		Connection conn = null;
        // 声明命令对象stmt
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> Messages = null;
        try {
            Messages = new ArrayList<>();
            // 0.加载驱动
            // 1.获取Connection对象
            conn = JDBCUtils.getConnection();

            // 2.获取Statement对象
            stmt = conn.createStatement();
            // 3.定义SQL语句
            String sql = "select msgs.id,msgtype,title,content,pubdate,reply,userid,username";
            sql = sql + " from msgs,users where msgs.userid=users.id";

            if (message != null) {
                //留言类别 一般/重要
                if (!"all".equals(message.getMsgtype())) {
                    sql += " and msgtype like '%" + message.getMsgtype() + "%'";
                }
                //留言开始和结束日期
                if (message.getPubdate() != null) {
                    if (message.getPubdateend() == null) {
                        sql += " and pubdate ='" + DateUtils.DateToString(message.getPubdate()).trim() + "'";
                    } else if (message.getPubdateend() != null) {
                        sql += " and pubdate between '" + DateUtils.DateToString(message.getPubdate()) + "' and '" +
                                DateUtils.DateToString(message.getPubdateend()) + "'";
                    }
                }
                //是否本人留言，还是全部留言
                if (!"all".equals(message.getUsertype())) {
                    sql += " and userid ='" + message.getUser().getId() + "'";
                }
                //留言的标题搜索
                if (message.getTitle() != null && !"".equals(message.getTitle())) {
                    sql += " and title like '%" + message.getTitle().trim() + "%'";
                }
            }
            sql = sql + " ORDER BY username";
            int pageIndex =(curpage-1)*pageSize;
            sql = sql + " LIMIT "+pageIndex+ "," + pageSize;
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);

            // 4.执行SQL语句增、删、改方法
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Message msg1 = new Message();
                msg1.setId(rs.getInt("id"));
                msg1.setMsgtype(rs.getString("msgtype"));
                msg1.setTitle(rs.getString("title"));
                msg1.setContent(rs.getString("content"));
                msg1.setPubdate(rs.getDate("pubdate"));
                msg1.setReply(rs.getString("reply"));
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                msg1.setUser(user);
                Messages.add(msg1);
            }
            return Messages;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

            JDBCUtils.release(rs, stmt, conn);

        }
	}

	@Override
	public Integer messageManagerCount(Message msg) {
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
            String sql = "SELECT count(*)";
            sql = sql + " FROM msgs";
            sql = sql + " WHERE 1=1";

            if (msg != null) {
                if (msg.getUser() != null && msg.getUser().getUsername() != null && !"".equals(msg.getUser().getUsername()) && !"null".equals(msg.getUser().getUsername())) {
                    sql += " and username like '%" + msg.getUser().getUsername().trim() + "%'";
                }
                if (msg.getPubdate() != null &&!"null".equals(msg.getPubdate())) {
                    if (msg.getPubdateend() == null) {
                        sql += " and pubdate ='" + DateUtils.DateToString(msg.getPubdate()).trim() + "'";
                    } else if (msg.getPubdateend() != null && !"null".equals(msg.getPubdateend())) {
                        sql += " and pubdate between '" + DateUtils.DateToString(msg.getPubdate()) + "' and '" + DateUtils.DateToString(msg.getPubdateend()) + "'";
                    }
                }
                if (!"all".equals(msg.getMsgtype()) && !"".equals(msg.getMsgtype()) && msg.getMsgtype()!=null && !"null".equals(msg.getMsgtype())) {
                    sql += " and msgtype ='" + msg.getMsgtype().trim() + "'";
                }
                if (msg.getTitle() != null && !"".equals(msg.getTitle()) && !"null".equals(msg.getTitle())) {
                    sql += " and title like '%" + msg.getTitle().trim() + "%'";
                }
            }
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);

            // 4.执行SQL语句增、删、改方法
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
	public List<Message> messageManager(int curpage, int pageSize, Message msg) {
		Connection conn = null;
        // 声明命令对象stmt
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> Messages = null;
        try {
            Messages = new ArrayList<>();
            // 0.加载驱动
            // 1.获取Connection对象
            conn = JDBCUtils.getConnection();

            // 2.获取Statement对象
            stmt = conn.createStatement();
            // 3.定义SQL语句
            String sql = "select msgs.id,msgtype,title,content,pubdate,reply,userid,username";
            sql = sql + " from msgs,users where msgs.userid=users.id";

            if (msg != null) {
                if (msg.getUser() != null && msg.getUser().getUsername() != null && !"".equals(msg.getUser().getUsername()) && !"null".equals(msg.getUser().getUsername())) {
                    sql += " and username like '%" + msg.getUser().getUsername().trim() + "%'";
                }
                if (msg.getPubdate() != null &&!"null".equals(msg.getPubdate())) {
                    if (msg.getPubdateend() == null) {
                        sql += " and pubdate ='" + DateUtils.DateToString(msg.getPubdate()).trim() + "'";
                    } else if (msg.getPubdateend() != null && !"null".equals(msg.getPubdateend())) {
                        sql += " and pubdate between '" + DateUtils.DateToString(msg.getPubdate()) + "' and '" + DateUtils.DateToString(msg.getPubdateend()) + "'";
                    }
                }
                if (!"all".equals(msg.getMsgtype()) && !"".equals(msg.getMsgtype()) && msg.getMsgtype()!=null && !"null".equals(msg.getMsgtype())) {
                    sql += " and msgtype ='" + msg.getMsgtype().trim() + "'";
                }
                if (msg.getTitle() != null && !"".equals(msg.getTitle()) && !"null".equals(msg.getTitle())) {
                    sql += " and title like '%" + msg.getTitle().trim() + "%'";
                }
            }
            sql = sql + " ORDER BY username";
            int pageIndex =(curpage-1)*pageSize;
            sql = sql + " LIMIT "+pageIndex+ "," + pageSize;
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);
            // 4.执行SQL语句增、删、改方法
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Message msg1 = new Message();
                msg1.setId(rs.getInt("id"));
                msg1.setMsgtype(rs.getString("msgtype"));
                msg1.setTitle(rs.getString("title"));
                msg1.setContent(rs.getString("content"));
                msg1.setPubdate(rs.getDate("pubdate"));
                msg1.setReply(rs.getString("reply"));
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                msg1.setUser(user);
                Messages.add(msg1);
            }
            return Messages;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
	}

	@Override
	public Boolean deleteMessage(Integer _id) {
		Connection conn = null;
        // 声明命令对象stmt
        Statement stmt = null;
        try {
            // 0.加载驱动
            // 1.获取Connection对象
            conn = JDBCUtils.getConnection();

            // 2.获取Statement对象
            stmt = conn.createStatement();
            // 3.定义SQL语句
            String sql = "delete from msgs";
            sql += " where id='" + _id + "'";
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
	public Message adminSelectMessageById(Integer _id) {
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
            String sql = "select msgs.id,msgtype,title,content,pubdate,reply,userid,username";
            sql = sql + " from msgs,users where msgs.userid=users.id";
            sql += " and msgs.id = " + _id + "";
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);
            // 4.执行SQL语句增、删、改方法
            rs = stmt.executeQuery(sql);
            Message msg1 = new Message();
            if (rs.next()) {
                msg1.setId(rs.getInt("id"));
                msg1.setMsgtype(rs.getString("msgtype"));
                msg1.setTitle(rs.getString("title"));
                msg1.setContent(rs.getString("content"));
                msg1.setPubdate(rs.getDate("pubdate"));
                msg1.setReply(rs.getString("reply"));
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                msg1.setUser(user);
            }
            System.out.println(msg1);
            return msg1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
	}

	@Override
	public boolean adminUpdateMessageReply(Message message2) {
		Connection conn = null;
        // 声明命令对象stmt
        Statement stmt = null;
        try {
            // 0.加载驱动
            // 1.获取Connection对象
            conn = JDBCUtils.getConnection();
            // 2.获取Statement对象
            stmt = conn.createStatement();
            // 3.定义SQL语句
            String sql = "update msgs set msgtype='" + message2.getMsgtype() + "',title='" + message2.getTitle() +
                    "'," +
                    "content='" + message2.getContent() + "',reply='" + message2.getReply() + "'";
            sql += " where id='" + String.valueOf(message2.getId()) + "'";
            // 在控制台输出sql语句，用以验证、调试sql语句是否正确
            System.out.println(sql);
            // 4.执行SQL语句增、删、改方法
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            JDBCUtils.release(stmt, conn);
        }
        return false;
	}
}
