package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	private DBUtil dbUtil;
	private TodoDao todoDao;

	// 일정 삭제
	public void removeTodoOne(int todoNo) {
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			this.todoDao.deleteTodoOne(conn, todoNo);
			conn.commit();
		} catch (SQLException e) {
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

	// 일정 수정
	public void modifyTodoOne(Todo todo) {
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			this.todoDao.updateTodoOne(conn, todo);
			conn.commit();
		} catch (SQLException e) {
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

	// 일정 상세보기
	public Todo getTodoOne(int todoNo) {
		Todo todo = new Todo();
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			todo = this.todoDao.selectTodoOne(conn, todoNo);
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
		return todo;
	}

	// 일정 추가
	public int addTodo(Todo todo) {
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		Connection conn = null;
		int rowCnt = 0;
		try {
			conn = this.dbUtil.getConnection();
			rowCnt = this.todoDao.insertTodo(conn, todo);
			conn.commit();
		} catch (SQLException e) {
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
		return rowCnt;
	}
}