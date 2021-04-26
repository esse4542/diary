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
	// todo �󼼺��� ���� ��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		Todo todoOne = new Todo();
		this.todoService = new TodoService();
		todoOne = this.todoService.getTodoOne(todoNo);

		request.setAttribute("todoOne", todoOne);
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodoOne.jsp").forward(request, response);
	}

	// ���� �׼�
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.todoService = new TodoService();

		// �Է� ������ ���� ���� ����
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");

		// ��ó��: todo vo ��ü�� ����
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoDate(todoDate);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);

		System.out.println(todo); // todo.toString()

		// service���� modify �޼��� ȣ��
		this.todoService.modifyTodoOne(todo);

		// ���û
		response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo="+todoNo);
	}

}