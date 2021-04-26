package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Todo;

@WebServlet("/auth/todoOne")
public class TodoOneController extends HttpServlet {
	private TodoService todoService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수집
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		System.out.println("todoNo: "+todoNo);

		// dao 객체 생성 후 todo 객체에 todo 정보 담기
		this.todoService = new TodoService();
		Todo todoOne = new Todo();
		todoOne = this.todoService.getTodoOne(todoNo);

		// request 객체에 todo 정보 저장
		request.setAttribute("todoOne", todoOne);
		// todoOne으로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/auth/todoOne.jsp").forward(request, response);
	}
}