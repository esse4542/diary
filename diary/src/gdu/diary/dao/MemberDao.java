package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
	
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
			
		} finally {
			dbUtil.close(rs, stmt, null);
		}
		
		return returnMember;
	}
}