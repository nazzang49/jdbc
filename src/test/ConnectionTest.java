package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			//1) JDBC Driver(maria) 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			//2) 연결
			String url = "jdbc:mariadb://192.168.1.54:3307/webdb";
			String user = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("[연결 섣공]");
		} catch (ClassNotFoundException e) {
			System.out.println("[드라이버 로딩 실패]");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[에러 발생]");
			e.printStackTrace();
		} finally {
			try {
				if(!conn.isClosed()&&conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
