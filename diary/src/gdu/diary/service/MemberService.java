package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	// select - > get
	// insert - > add
	// update - > modify
	// delete - > remove
	public Member getMemberKey(Member member) {
		this.memberDao = new MemberDao();
		Member retruenMember = null;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			retruenMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		
		
		return retruenMember; 
	}
}
