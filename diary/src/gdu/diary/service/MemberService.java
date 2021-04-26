package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	// 회원정보 수정
	public void modifyMemberPw(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			this.memberDao.updateMemberPw(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 회원가입
	public Member addMember(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.insertMember(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnMember;
	}
	
	
	// 삭제성공 : ture, 삭제실패(rollback) = false
	public boolean removeMemberByKey(Member member) {
		this.memberDao = new MemberDao();
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		Connection conn = null;
		int todoRowCnt = 0;
		int memberRowCnt = 0;
		try {
			conn = dbUtil.getConnection();
			todoRowCnt = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());
			System.out.println(todoRowCnt + "<-- todoRowCnt");
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);
			System.out.println(memberRowCnt + "<-- memberRowCnt");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // 모든 쿼리가 취소, select빼고
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (todoRowCnt+memberRowCnt) > 0;
	}
	
	
	
	
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
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return retruenMember; 
	}
}
