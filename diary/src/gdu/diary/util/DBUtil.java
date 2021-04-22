package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
	// 1. DB ����
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary", "root", "java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 2. DB�ڿ� (conn, stmt, rs) ����
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		// rs, stmt, conn �� null�� �ƴ� ��쿡�� close���� ������ �ȶߵ��� �Ѵ�. (��ȿ�� �˻�)
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}