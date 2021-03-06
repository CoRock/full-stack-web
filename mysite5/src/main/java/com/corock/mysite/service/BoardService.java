package com.corock.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.BoardDAO;
import com.corock.mysite.vo.BoardVO;

@Service
public class BoardService {
	
	private static final int LIST_SIZE = 5;		// 리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5;		// 페이지 리스트의 페이지 수
	
	@Autowired
	private BoardDAO boardDao;

	public Map<String, Object> getMessageList( int currentPage, String keyword ) {
		System.out.println("keyword in BoardService: " + keyword);
		System.out.println("currentPage in BoardService: " + currentPage);

		// 1. 페이징을 위한 기본 데이터 계산
		int totalCount = boardDao.getTotalCount( keyword );
		int pageCount = (int) Math.ceil( (double) totalCount / LIST_SIZE );
		int blockCount = (int) Math.ceil( (double) pageCount / PAGE_SIZE );
		int currentBlock = (int) Math.ceil( (double) currentPage / PAGE_SIZE );
		
		// 2. 파라미터 page 값 검증
		if ( currentPage > pageCount ) {
			currentPage = pageCount;
			currentBlock = (int) Math.ceil( (double) currentPage / PAGE_SIZE );
		}
		
		if ( currentPage < 1 ) {
			currentPage = 1;
			currentBlock = 1;
		}
		
		// 3. view에서 페이지 리스트를 렌더링 하기 위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : ( currentBlock - 1 ) * PAGE_SIZE + 1;
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount;
		
		// 4. 리스트 가져오기
		List<BoardVO> list = boardDao.getList( keyword, currentPage, LIST_SIZE );
		
		// 5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<>();
		
		map.put( "list", list );
		map.put( "totalCount", totalCount );
		map.put( "listSize", LIST_SIZE );
		map.put( "currentPage", currentPage );
		map.put( "beginPage", beginPage );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		map.put( "endPage", endPage );
		map.put( "keyword", keyword );
		
		return map;
	}

	public boolean increaseGroupOrderNo(BoardVO boardVo) {
		return boardDao.updateOrderNo( boardVo.getGroupNo(), boardVo.getOrderNo() ) > 0;
	}

	public boolean addMessage( BoardVO boardVo ) {
		return boardDao.insert( boardVo ) == 1;
	}

	public BoardVO getMessage(Long no) {
		BoardVO boardVo = boardDao.get( no );
		
		if ( boardVo != null ) {
			boardDao.updateHit( no );
		}
		
		return boardVo;
	}

	public boolean deleteMessage( Long boardNo, Long userNo ) {
		int count = boardDao.delete( boardNo, userNo );
		return count == 1;
	}

	public BoardVO getMessage( Long no, Long userNo ) {
		BoardVO boardVo = boardDao.get( no, userNo );
		return boardVo;
	}

	public boolean modifyMessage( BoardVO boardVo ) {
		int count = boardDao.update( boardVo );
		return count == 1;
	}
	
}
