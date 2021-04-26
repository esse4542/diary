package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.source.tree.MemberSelectTree;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/removeMember")
public class RemoveMemberController extends HttpServlet {
	private MemberService memberService;
	// ��й�ȣ �Է� ��
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/removeMember.jsp").forward(request, response);
	}
	// ���� �׼�
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberPw = request.getParameter("memberPw");
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		boolean result = this.memberService.removeMemberByKey(member);
		if(result == false) { //if(!result)
			System.out.println("ȸ��Ż�� ����");
			response.sendRedirect(request.getContextPath()+"/auth/removeMember"); // ��й�ȣ ���н�
			return;
		}
		response.sendRedirect(request.getContextPath()+"/auth/logout");
	}
}
