package servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO {
	DataSource ds;
	private static UserDAOImpl dao = new UserDAOImpl();

	private UserDAOImpl() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			System.out.println("dataSource Look up 성공");

		} catch (NamingException e) {
			System.out.println("dataSource Look up 실패");
		}
	}
	
	public static UserDAOImpl getInstance() {
		return dao;
	}
	

	@Override
	public Connection getConnect() throws SQLException {
		System.out.println("DB연결 성공");
		return ds.getConnection();
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps !=null)
			ps.close();
		if(conn!=null)
			conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(ps,conn);
	}
	
	@Override
	public UserVO login(String userId, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO vo = null;
		
		try {
			conn = getConnect();
			String query = "select * from userinfo where userId=? and password=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				vo= new UserVO(userId, password);
			}			
		}finally {
			closeAll(rs, ps, conn);
		}
		return vo;
	}

	@Override
	public void registerMember(UserVO vo) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<UserVO> showAllUser() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findByIdMemeber(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
