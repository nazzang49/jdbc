package hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String keyword = sc.nextLine();
		System.out.print("검색어 입력 --> "+keyword);
		
		EmployeeDAO edao = new EmployeeDAO();
		List<EmployeeVO> list = edao.getList(keyword);
		
		for(EmployeeVO empList : list) {
			System.out.println(empList.getFirst_name());
		}
		sc.close();
	}
}
