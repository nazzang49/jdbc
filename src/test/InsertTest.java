package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	static Scanner sc = null;
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static String sql = "";
	
	public static void close() {
		try {
			//null 조건을 먼저 명시 = 예외 발생 방지
			if(rs!=null&&!rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(stmt!=null&&!stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(conn!=null&&!conn.isClosed()) {
				conn.close();
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
			
			sc = new Scanner(System.in);
			String name = sc.next();
			
			//4) sql 실행
			sql = "insert into department values(null,'"+name+"')";
			int count = stmt.executeUpdate(sql);
			
			if(count==1) {
				System.out.println("[삽입 성공]");
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
