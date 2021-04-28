package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoDao {
	
	// dday 출력
	/*
	 * SELECT todo_no todoNo,todo_date todoDate,todo_title todoTitle,DATEDIFF(todo_date,DATE(NOW())) dday
	 *  FROM todo
	 *  WHERE todo_date > DATE(NOW()) AND member_no=?"
	 */
	public List<Map<String, Object>> selectTodoDdayList(Connection conn, int memberNo) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_DDAY_LIST);
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("todoNo", rs.getInt("todoNo"));
				map.put("todoDate", rs.getString("todoDate"));
				map.put("todoTitle", rs.getString("todoTitle"));
				map.put("dday", rs.getInt("dday"));
				list.add(map);
			}
		} finally {
			rs.close(); // 김태O씨 이슈
			stmt.close();
		}
		return list;
	}
	
	

	// todo 상세보기 삭제
	public void deleteTodoOne(Connection conn, int todoNo) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_ONE);
			stmt.setInt(1, todoNo);
			System.out.println("deleteTodoOne "+stmt);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}

	// todo 상세보기 수정
	public void updateTodoOne(Connection conn, Todo todo) throws SQLException {
		PreparedStatement stmt = null;
		try {
			// UPDATE_TODO_ONE = "UPDATE todo SET todo_title=?, todo_content=?, todo_font_color=? WHERE todo_no=?";
			stmt = conn.prepareStatement(TodoQuery.UPDATE_TODO_ONE);
			stmt.setString(1, todo.getTodoTitle());
			stmt.setString(2, todo.getTodoContent());
			stmt.setString(3, todo.getTodoFontColor());
			stmt.setInt(4, todo.getTodoNo());
			System.out.println("updateTodoOne "+stmt);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}

	// todo 상세보기
	public Todo selectTodoOne(Connection conn, int todoNo) throws SQLException {
		Todo todo = new Todo();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// SELECT_TODO_ONE = "SELECT todo_no todoNo, todo_date todoDate, todo_title todoTitle, todo_content todoContent, todo_font_color todoFontColor, todo_add_date todoAddDate FROM todo WHERE todo_no=?";
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_ONE);
			stmt.setInt(1, todoNo);
			System.out.println("selectTodoOne" + stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoTitle(rs.getString("todoTitle"));
				todo.setTodoContent(rs.getString("todoContent"));
				todo.setTodoFontColor(rs.getString("todoFontColor"));
				todo.setTodoAddDate(rs.getString("todoAddDate"));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return todo;
	}
	// 날짜 별 todo 보기
	public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException {
		List<Todo> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoTitle(rs.getString("todoTitle"));
				todo.setTodoFontColor(rs.getString("todoFontColor"));
				list.add(todo);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}

	// todo 추가
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1, todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	
	
	// 게시물 삭제 
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException {
		
		int rowCnt = 0;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			System.out.printf("stmt: %s<TodoDao.deleteTodoByKey()>\n", stmt);
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();//conn은 service에서 닫음
		}

		return rowCnt;
		
	}
}