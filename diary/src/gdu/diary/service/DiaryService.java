package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class DiaryService {
	private DBUtil dbUtil;
	private TodoDao todoDao;
	// �޷�
	public Map<String, Object> getDiary(int memberNo, String targetYear, String targetMonth) {
		// Ÿ�� ��,��,��
		// Ÿ�� 1��(��¥)
		// Ÿ�� ���������� ���� -> endDay
		
		// startBlank + endDay + endBlank
		// ��ü ���� ����(���������� ���ں��ٴ� ũ�� 7�� ������ �������� ��)
		// ���� �� ���� -> startBlank
		// �̹��� ���ڰ� ���;� �� ���� ����(1~��������¥)
		// ���� ���� ���� -> endBlank  -> (startBlank + endDay)%7 
		//�������� ���� �־��� ���̹Ƿ� HashMap ��� - Object�� ����ϸ� �������� Ÿ���� ���� �� ����(��� ���迡�� ���� ������ �־: ���⼺)
		Map<String, Object> map = new HashMap<String, Object>();
		//Calendar Ŭ���� ���
		//diary���� ��¥ ������ �� ��� calendar�� ��¥�� �ٲ������� �ƴѰ�� �׳� ���� ��¥�� ��
		Calendar target = Calendar.getInstance();
		if(targetYear != null) {
			target.set(Calendar.YEAR,  Integer.parseInt(targetYear));
		}
		
		if(targetMonth != null) {
			//�ι�° �μ��� -1�̸� target�� ���� -1�ϰ� ���� 11(12��) ���� ����.
			//�ι�° �μ����� 12�̸� target�� ���� +1�ϰ� ���� 0(1��) ���� ����.
			target.set(Calendar.MONTH,  Integer.parseInt(targetMonth));
		}
		
		/*
	    int numTargetMonth = 0;
		int numTargetYear = 0;
		if(targetMonth != null && targetYear != null) {
			numTargetYear = Integer.parseInt(targetYear);
			numTargetMonth = Integer.parseInt(targetMonth);
			
			if(numTargetMonth == 0) {
				numTargetYear = numTargetYear - 1;
				numTargetMonth = 12;
			} else if(numTargetMonth == 13) {
				numTargetYear = numTargetYear + 1;
				numTargetMonth = 1;
			}
			
			target.set(Calendar.YEAR, numTargetYear);
			target.set(Calendar.MONTH, numTargetMonth - 1);
		}
		*/
			
		target.set(Calendar.DATE, 1); // ������ ��¥�� 1�� �ٲ۴�
		// Ÿ�� ���� 1 ���� �տ� �;� �� �� ���� ���� 
		int startBlank = target.get(Calendar.DAY_OF_WEEK) - 1;
		// Ÿ�� ���� ������ ��¥
		int endDay = target.getActualMaximum(Calendar.DATE);
		int endBlank = 0;
		if ((startBlank + endDay) % 7 != 0) {
			endBlank = 7 - (startBlank + endDay) % 7;
		}
		// int totalCall = startBlank + endDay + endBlank;
		
		map.put("targetYear", target.get(Calendar.YEAR));
		map.put("targetMonth", target.get(Calendar.MONTH));
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		

		//2. targetYear, targetMonth(0�̸� 1��, 1�̸� 2��)�� �ش��ϴ� todo��� �����ͼ� �߰�
		this.todoDao = new TodoDao();
		List<Todo> todoList = null;

		this.dbUtil = new DBUtil();
		Connection conn = null;
		try {
			conn = this.dbUtil.getConnection();
			todoList = this.todoDao.selectTodoListByDate(conn, memberNo, target.get(Calendar.YEAR), target.get(Calendar.MONTH)+1);
			conn.commit();
		} catch(Exception e) {
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

		map.put("todoList", todoList);
		
		return null;
	}
}
