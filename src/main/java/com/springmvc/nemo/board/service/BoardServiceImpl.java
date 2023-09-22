package com.springmvc.nemo.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.board.dao.BoardDAO;
import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private static String BOARD_IMG_DIR;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public Map<String, Object> listBoard(Map<String, Object> pagingMap) throws DataAccessException {
		
		int group_id = (Integer) pagingMap.get("group_id");
		
		List<BoardVO> noticeList = boardDAO.getNoticeList(group_id);
		
		int totBoard = boardDAO.getTotBoard(group_id);
		
		List<BoardVO> boardList =  boardDAO.getBoardList(pagingMap);
		
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("noticeList", noticeList);
		boardMap.put("totBoard", totBoard);
		boardMap.put("boardList", boardList);
		
		return boardMap;
	}
	
	
	@Override
	public BoardVO getBoard(int article_no) throws DataAccessException {
		
		return boardDAO.getBoard(article_no);
	}
	
	@Override
	public List<CommentVO> getCommentsList(int article_no) throws DataAccessException {
		
		return boardDAO.getCommentsList(article_no);
	}
	
	@Override
	public void updateBoardViewCnt(int article_no) throws DataAccessException {
		
		boardDAO.updateBoardViewCnt(article_no);
	}
	
	
	@Override
	public int addBoard(BoardVO boardVO) throws DataAccessException {
		int article_no = boardDAO.getNewArticleNo();
		boardVO.setArticle_no(article_no);
		
		String[] imgName = boardVO.getImageName();
		String content = boardVO.getContent();
		List<String> fileNameList= new ArrayList<String>(Arrays.asList(imgName));
		
		
		if(boardVO.getIsImgExist()) {
			
            moveImageDir(fileNameList, article_no);
			content=content.replace("/gettempimage?", "/getimage?article_no="+article_no+"&");
			boardVO.setContent(content);
		}
		
		logger.info("boardVO={}", boardVO.toString());
		boardDAO.addBoard(boardVO);
		
		
		return article_no;
	}
	
	
	
		//temp 에서 이미지 폴더 이동 하는 메소드 
		private void moveImageDir(List<String> fileNameList, int article_no) {
		//articleno로 폴더 생성
		//tmp에서 articleno폴더로 이동
			try {
				BOARD_IMG_DIR=this.getClass().getResource("").getPath();
				BOARD_IMG_DIR=BOARD_IMG_DIR.substring(1,BOARD_IMG_DIR.indexOf(".metadata"));
				BOARD_IMG_DIR=BOARD_IMG_DIR.replace("/", "\\");
				BOARD_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\boardImages";

				if(fileNameList!=null && fileNameList.size()!=0) {
					for(String imgName:fileNameList) {
						File srcFile=new File(BOARD_IMG_DIR+"\\temp\\"+imgName);
						File destDir=new File(BOARD_IMG_DIR+"\\"+article_no);
						FileUtils.moveFileToDirectory(srcFile, destDir, true);
						srcFile.delete();
					}	
				}
				
			} catch (Exception e) {
				System.out.println("temp에서 이미지 파일 복사하는 중 에러");
				e.printStackTrace();
			}
		}
	

}
