package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Todo;

@WebServlet("/auth/modifyTodoOne")
public class ModifyTodoOneController extends HttpServlet {
	private TodoService todoService;
	// todo 상세보기 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		Todo todoOne = new Todo();
		this.todoService = new TodoService();
		todoOne = this.todoService.getTodoOne(todoNo);

		request.setAttribute("todoOne", todoOne);
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodoOne.jsp").forward(request, response);
	}

	// 수정 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.todoService = new TodoService();

		// 입력 폼에서 받은 정보 수집
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");

		// 전처리: todo vo 객체에 저장
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoDate(todoDate);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);

		System.out.println(todo); // todo.toString()

		// service에서 modify 메서드 호출
		this.todoService.modifyTodoOne(todo);

		// 재요청
		response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo="+todoNo);
	}

}