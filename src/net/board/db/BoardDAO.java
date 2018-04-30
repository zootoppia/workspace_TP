package net.board.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.myBatisSetting.sqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sessionf = sqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BoardDAO(){
		sqlsession = sessionf.openSession(true);
	}
	
    //Faq 게시판 글쓰기
	public void insertFaq(BoardDTO bDTO) {
		sqlsession.insert("insertFaq",bDTO);
	}
	
	public int selectFaq() {
		int result;
		result=sqlsession.selectOne("selectFaq");
		return result;
	}
	
	//Faq 게시판 리스트
		public List<BoardDTO> getFaqList(int startRow, int pageSize){
			HashMap map = new HashMap();
			map.put("startRow", startRow-1);
			map.put("pageSize", pageSize);
			List<BoardDTO> faqList = sqlsession.selectList("getFaqList",map);
			return faqList;
		}
		
		//Faq 게시판 글 개수 구하기
		public int getFaqCount(){
			int count;
			count = sqlsession.selectOne("getFaqCount");
			return count;
		}
	

	//Notice 게시판 글쓰기
	public int insertCur(BoardDTO bDTO) {
		int result;
		int count = sqlsession.selectOne("countCur");
		if((Integer)count == null ) count = -1;
		System.out.println(count);
		count += 1;
		bDTO.setCur_num(count);
		result = sqlsession.insert("insertCur", bDTO);
		return result;
	}
	//NOtice 게시판 글쓰기
	public int insertNotice(BoardDTO bDTO){
		int result;
		result = sqlsession.insert("insertNotice", bDTO);
		return result;
	}
	
	//Notice 게시판 리스트
	public List<BoardDTO> getNoticeList(int startRow, int pageSize){
		HashMap map = new HashMap();
		map.put("startRow", startRow-1);
		map.put("pageSize", pageSize);
		List<BoardDTO> noticeList = sqlsession.selectList("getNoticeList",map);
		return noticeList;
	}
	
	//Notice 게시판 글 개수 구하기
	public int getNoticeCount(){
		int count;
		count = sqlsession.selectOne("getNoticeCount");
		return count;
	}
	
	//Notice 게시판 해당 번호 글 가져오기
	public BoardDTO getNotice(int notice_num){
		BoardDTO bDTO = sqlsession.selectOne("getNotice", notice_num);
		return bDTO;
	}
	
	//Notice 게시판 글 수정
	public void updateNotice(BoardDTO bDTO){
		sqlsession.update("updateNotice", bDTO);
	}
	
	//Notice 게시판 글 삭제
	public void deleteNotice(int notice_num){
		sqlsession.delete("deleteNotice", notice_num);
	}
	
	//Qna 게시판 글쓰기
	public int insertQna(Vector vector) {
		int result;
		HashMap map = new HashMap<>();
		List<BoardDTO> list = (List<BoardDTO>)vector.get(0);
		String id = (String)vector.get(1);
		map.put("list", list);
		map.put("id", id);
		
		result = sqlsession.insert("insertQna", map);
		return result;
	}
	
	public int selectMaxNum() {
		int maxNum;
		maxNum = sqlsession.selectOne("selectMaxNum");
		return maxNum;
	}
	
}
