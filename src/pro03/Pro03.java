package pro03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pro03 {

	public static void main(String[] args) {

		
		// 코드작성
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
			conn = DriverManager.getConnection(url, "hr", "hr");


			String query = "select es.employee_id, " +
				                  "es.last_name, " +
				                  "es.email, " +
				                  "js.job_title, " +
				                  "ds.department_name, " +
				                 "ls.city " +
				           "from employees es, jobs js, departments ds, locations ls " +
			               "where es.job_id = js.job_id " +
			                     "and es.department_id = ds.department_id " +
			                     "and ds.location_id = ls.location_id " +
			                     "and js.job_id = 'PU_CLERK' " +
			                     "and ls.city = 'Seattle' " +
			               "order by es.employee_id desc";

					
			pstmt = conn.prepareStatement(query); 
			rs = pstmt.executeQuery(); 
			
			System.out.printf("%12s %12s %12s %25s %20s %12s","employee_id","last_name","email","job_title","department_name","city" );
			System.out.println();
			while (rs.next()) {
				String employeeId = String.valueOf(rs.getInt("employee_id"));
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String jobTitle = rs.getString("job_title");
				String departmentName = rs.getString("department_name");
				String city = rs.getString("city");
				
				System.out.printf("%12s %12s %12s %25s %20s %12s",employeeId,lastName,email,jobTitle,departmentName,city);
				System.out.println();
			}

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {



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

}
