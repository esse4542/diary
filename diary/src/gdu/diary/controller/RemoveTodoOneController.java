package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;

@WebServlet("/auth/removeTodoOne")
public class RemoveTodoOneController extends HttpServlet {
	private TodoService todoService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));

		this.todoService = new TodoService();
		this.todoService.removeTodoOne(todoNo);

		response.sendRedirect(request.getContextPath()+"/auth/diary");
	}

}