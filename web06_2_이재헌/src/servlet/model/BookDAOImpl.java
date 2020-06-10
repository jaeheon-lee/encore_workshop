package servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDAOImpl implements BookDAO {
	DataSource ds;
	private static BookDAOImpl dao = new BookDAOImpl();

	private BookDAOImpl() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			System.out.println("dataSource Look up 성공");

		} catch (NamingException e) {
			System.out.println("dataSource Look up 실패");
		}
	}

	public static BookDAOImpl getInstance() {
		return dao;
	}

	@Override
	public Connection getConnect() throws SQLException {
		System.out.println("DB연결 성공");
		return ds.getConnection();
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}

	@Override
	public void registerBook(BookVO vo) throws SQLException {
		System.out.println(vo);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			String query = "INSERT INTO book(isbn, title, catalogue, nation, publish_date, publisher, author, price, currency, description) VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			String isbn = "";
			for (String s : vo.getIsbn()) {
				isbn += s;
			}
			ps.setString(1, isbn);
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getCatalogue());
			ps.setString(4, vo.getNation());
			ps.setString(5, vo.getPublish_date());
			ps.setString(6, vo.getPublisher());
			ps.setString(7, vo.getAuthor());
			ps.setInt(8, vo.getPrice());
			ps.setString(9, vo.getCurrency());
			ps.setString(10, vo.getDescription());
			ps.executeUpdate();
		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public ArrayList<BookVO> showAllBook() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<BookVO> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			conn = getConnect();
			String query = "select * from book";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String[] isbn = new String[3];
				for (int i = 0; i < 3; i++) {
					isbn[i] = rs.getString(1).substring(0, 3);
				}

				list.add(new BookVO(isbn, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
			}

		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}

	@Override
	public ArrayList<BookVO> findBooksByTitle(String title) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<>();
		try {
			
			conn = getConnect();
			title = "%"+title+"%";
			String query = "select * from book where title like ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				String[] isbn = new String[3];
				for (int i = 0; i < 3; i++) {
					isbn[i] = rs.getString(1).substring(0, 3);
				}
				list.add(new BookVO(isbn, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
			}
		} finally {
			closeAll(rs, ps, conn);

		}
		return list;
	}
	
	@Override
	public ArrayList<BookVO> findBooksByPublisher(String publisher) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<>();
		try {
			conn = getConnect();
			String query = "select * from book where title like '%?%'";
			ps = conn.prepareStatement(query);

			ps.setString(1, publisher);
			rs = ps.executeQuery();
			while (rs.next()) {
				String[] isbn = new String[3];
				for (int i = 0; i < 3; i++) {
					isbn[i] = rs.getString(1).substring(0, 3);
				}
				list.add(new BookVO(isbn, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
			}
		} finally {
			closeAll(rs, ps, conn);

		}
		return list;
	}
	@Override
	public ArrayList<BookVO> findBooksByPrice(int price) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<>();
		try {
			conn = getConnect();
			String query = "select * from book where title like '%?%'";
			ps = conn.prepareStatement(query);

			ps.setInt(1, price);
			rs = ps.executeQuery();
			while (rs.next()) {
				String[] isbn = new String[3];
				for (int i = 0; i < 3; i++) {
					isbn[i] = rs.getString(1).substring(0, 3);
				}
				list.add(new BookVO(isbn, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
			}
		} finally {
			closeAll(rs, ps, conn);

		}
		return list;
	}
}
