package hr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
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
			if(pstmt!=null&&!pstmt.isClosed()) {
				pstmt.close();
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
	
	public List<EmployeeVO> getList(String keyword){
		List<EmployeeVO> list = new ArrayList<>();
		
		try {
			//1) JDBC Driver(maria) 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2) 연결
			String url = "jdbc:mariadb://192.168.1.54:3307/employees";
			String user = "hr";
			String pw = "hr";
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("[연결 섣공]");
			
			//3) sql 실행
			sql = "select * from employees where first_name like ? or last_name like ?";
			
			//4) pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			//5) 실행 결과 호출
			while(rs.next()) {
				//가능하면 wrapper 클래스 사용 권고
				Long no = rs.getLong(1);
				Date birth_date = rs.getDate(2);
				String first_name = rs.getString(3);
				String last_name = rs.getString(4);
				String gender = rs.getString(5);
				Date hire_date = rs.getDate(6);
				
				//클라이언트에게 결과 가져다 주는 로직
				EmployeeVO vo = new EmployeeVO();
				vo.setNo(no);
				vo.setBirth_date(birth_date);
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setGender(gender);
				vo.setHire_date(hire_date);
				
				list.add(vo);
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
		return list;
	}
}