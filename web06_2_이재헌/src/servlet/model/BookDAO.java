package servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {

		Connection getConnect() throws SQLException;
		void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
		void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
		void registerBook(BookVO vo) throws SQLException;
		ArrayList<BookVO> showAllBook() throws SQLException;
		ArrayList<BookVO> findBooksByTitle(String title) throws SQLException;
		ArrayList<BookVO> findBooksByPublisher(String publisher) throws SQLException;
		ArrayList<BookVO> findBooksByPrice(int price) throws SQLException;
	}

