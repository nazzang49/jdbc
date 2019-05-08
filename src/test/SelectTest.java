package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static String sql = "";
	
	public static void close() {
		try {
			if(!conn.isClosed()&&conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(!stmt.isClosed()&&stmt!=null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(!rs.isClosed()&&rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			//1) JDBC Driver(maria) 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2) 연결
			String url = "jdbc:mariadb://192.168.1.54:3307/webdb";
			String user = "webdb";
			String pw = "webdb";
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("[연결 섣공]");
			
			//3) statement 객체 생성
			stmt = conn.createStatement();
			
			//4) sql 실행
			sql = "select * from department";
			rs = stmt.executeQuery(sql);
			
			//5) 실행 결과 호출
			while(rs.next()) {
				//가능하면 wrapper 클래스 사용 권고
				Long no = rs.getLong("no");
				String name = rs.getString(2);
				System.out.println(no+" : "+name);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("[드라이버 로딩 실패]");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[에러 발생]");
			e.printStackTrace();
		} finally {
			close();
		}	
	}
}
