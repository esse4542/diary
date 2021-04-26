package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {

	private MemberService memberService;
	
	// ȸ�� ���� ��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	// ���� �׼�
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		
		// �Է� form���� ���� memberId, memberPw ����
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// �����
		System.out.println("memberId: "+ memberId);
		System.out.println("memberPw: "+ memberPw);
		
		// ��ó��: member vo ��ü�� ����
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// Service���� insert �޼��� ȣ��
		Member returnMember = this.memberService.addMember(member);
		
		// redirect
		response.sendRedirect(request.getContextPath()+"/login");
	}

}