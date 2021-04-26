package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	// 1. DB ����
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary","root","java1004");
			conn.setAutoCommit(false); 
			// setAutoCommit(false): �������� ���� ������ �ϳ��� �۾����� ����Ǿ�� �� ��� ������ ������ �۵����� ���ϰ� �Ѵ�.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// 1) finally ���� �����Ͽ� conn.close()�� �ϸ� �ȵǴ� ����?
		// conn�� �����ؾߵż� close�ϸ� �ȵǱ� ������ finally�� ������� �ʴ´� 
		return conn;
	}
}