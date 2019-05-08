package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			//1) 새로 생성한 JDBC Driver 로딩
			Class.forName("driver.MyDriver");
			//2) 연결
			String url = "jdbc:mydb://192.168.1.54:3307/webdb";
			String user = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("[연결 섣공]"+conn);
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
