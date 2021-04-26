package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	
	
	// ȸ������ ���� 
	public void updateMemberPw(Connection conn, Member member) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_PW);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			// �����
			System.out.println("updateMemberPw "+stmt);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	
	
	
	
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException {
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	
	
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException { //Connection ������ �Ű������� �޾ƾߵȴ�
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return returnMember;
	}
	
	
	
	// ȸ������ 
	public Member insertMember(Connection conn, Member member) throws SQLException {
		Member returnMember = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			// �����
			System.out.println("insertMember "+stmt);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return returnMember;
	}
}