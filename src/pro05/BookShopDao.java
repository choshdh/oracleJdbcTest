package pro05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookShopDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void insert(BookVo vo) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into bookshop values(seq_bookshop_id.nextval, ? , ? , ? , ? , ? )";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubDate());
			pstmt.setString(4, vo.getAuthorName());
			pstmt.setString(5, vo.getStateCode());

			int result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
	}

	public void rent(int num) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update bookshop set state_code = ?  where id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.setInt(2, num);
			
			int result = pstmt.executeUpdate();
			
			select(num);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:" + e);

		} finally {
			close();
		}
	}
	
	
	public List<BookVo> getListAll() {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from bookshop";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(); 

			List<BookVo> l = new ArrayList<BookVo>();
			// 4.결과처리
			while (rs.next()) {
				BookVo vo = new BookVo();
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				String stateCode = rs.getString("state_code");

				vo.setId(id);
				vo.setTitle(title);
				vo.setPubs(pubs);
				vo.setPubDate(pubDate);
				vo.setAuthorName(authorName);
				if(stateCode.equals("0")) {
					vo.setStateCode("대여중");
				}else {
					vo.setStateCode("재고있음");
				}
				l.add(vo);
			}
			return l;

		} catch (SQLException e) {
			System.out.println("error:" + e);
			return null;

		} finally {
			close();
		}
	}
	
	
	public void select(int num) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select title from bookshop where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery(); 

			// 4.결과처리
			while (rs.next()) {
				String title = rs.getString("title");
				System.out.println(title + " 이(가) 대여 되었습니다.");
			}


		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {
			close();
		}
	}
	
	

	private void connect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	private void close() {
		// 5. 자원정리

		try {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

}
