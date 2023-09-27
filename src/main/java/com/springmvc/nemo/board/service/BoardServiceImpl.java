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
		
		
		
		if(boardVO.getIsImgExist()) {
			List<String> fileNameList= new ArrayList<String>(Arrays.asList(imgName));
            moveImageDir(fileNameList, article_no);
			content=content.replace("/gettempimage?", "/getimage?article_no="+article_no+"&");
			boardVO.setContent(content);
		}
		
		logger.info("boardVO={}", boardVO.toString());
		boardDAO.addBoard(boardVO);
		
		
		return article_no;
	}
	
	
	@Override
	public void modBoard(BoardVO boardVO) throws DataAccessException {
		
		BoardVO oldBoard = boardDAO.getBoard(boardVO.getArticle_no());
		String oldContent = oldBoard.getContent();
		int article_no = oldBoard.getArticle_no();
		
		
		String[] imgName = boardVO.getImageName();
		
		String newContent = boardVO.getContent();

		if(boardVO.getIsImgExist()) {
			List<String> fileNameList= new ArrayList<String>(Arrays.asList(imgName));
			// content 업데이트
			newContent=newContent.replace("/gettempimage?", "/getimage?article_no="+article_no+"&");
			boardVO.setContent(newContent);
			
			
			List<String> oldFileNameList = getImageFileName(oldContent, article_no);
			List<String> newFileNameList = getImageFileName(newContent, article_no);
			
			logger.info("oldContent={}", oldContent);
			logger.info("newContent={}", newContent);
			
			logger.info("oldFileNameList={}", oldFileNameList.toString());
			logger.info("newFileNameList={}", newFileNameList.toString());
			
			
			// 수정되어서 없어진 이미지 삭제
			delOldFile(article_no, oldFileNameList, newFileNameList);
			
			// temp에 올라온 이미지 옮기기
			moveImageDir(fileNameList, article_no);
		}
		
		boardDAO.modBoard(boardVO);

	}
	
	
	@Override
	public void delBoard(int article_no) throws DataAccessException {
		
		boardDAO.delBoard(article_no);
		
		try {
			
			BOARD_IMG_DIR=this.getClass().getResource("").getPath();
			BOARD_IMG_DIR=BOARD_IMG_DIR.substring(1,BOARD_IMG_DIR.indexOf(".metadata"));
			BOARD_IMG_DIR=BOARD_IMG_DIR.replace("/", "\\");
			BOARD_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\boardImages\\"+article_no;
			
			File boardDir = new File(BOARD_IMG_DIR);
			FileUtils.deleteDirectory(boardDir);
			
		} catch (Exception e) {
			logger.info("게시글 삭제 중 에러");
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Map<String, Object> searchBoard(Map<String, Object> searchMap) throws DataAccessException {
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		List<BoardVO> boardList = boardDAO.searchBoard(searchMap);
		int totBoard = boardDAO.getTotSearchBoard(searchMap);
		
		boardMap.put("boardList", boardList);
		boardMap.put("totBoard", totBoard);
		
		return boardMap;
	}
	
	
	
	@Override
	public CommentVO addComment(CommentVO commentVO) throws DataAccessException {
		
		int newCommentNo = boardDAO.getNewCommentNo();
		commentVO.setComment_no(newCommentNo);
		
		boardDAO.addComment(commentVO);
		
		int article_no = commentVO.getArticle_no();
		boardDAO.increaseCommentCnt(article_no);
		
		return boardDAO.getCommentVOByNo(newCommentNo);
	}
	
	
	@Override
	public String modComment(CommentVO commentVO) throws DataAccessException {
		
		int comment_no = commentVO.getComment_no();
		String origin_user_id = boardDAO.getUserIdByCommentNo(comment_no);
		
		if(origin_user_id.equals(commentVO.getUser_id())) {
			boardDAO.modComment(commentVO);
			
			return "success";
			
		} else {
			return "fail";
		}
		
	}
	
	
	@Override
	public String getContentByCommentNo(int comment_no) throws DataAccessException {
		
		
		return boardDAO.getContentByCommentNo(comment_no);
	}
	
	
	@Override
	public String getUserIdByCommentNo(int comment_no) throws DataAccessException {
		
		return boardDAO.getUserIdByCommentNo(comment_no);
	}
	
	
	@Override
	public void delComment(CommentVO commentVO) throws DataAccessException {
		int comment_no = commentVO.getComment_no();
		int article_no = commentVO.getArticle_no();
		
		boardDAO.delComment(comment_no);
		boardDAO.decreaseCommentCnt(article_no);
		
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
			logger.info("temp에서 이미지 파일 복사하는 중 에러");
			e.printStackTrace();
		}
	}
	
	
	// old content에서 이미지 이름 추출
	private List<String> getImageFileName(String content, int article_no){
		List<String> fileNameList = new ArrayList<String>();
		
		Pattern pattern=Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher=pattern.matcher(content);
		while(matcher.find()) {
			String[] arr = matcher.group(1).split("getimage\\?article\\_no="+article_no+"&amp\\;savedfilename=");
			if(arr.length>1) {
				logger.info("getImageFileName={}", arr[1]);
				fileNameList.add(arr[1]);
			}
		}
		
		return fileNameList;
	}
	
	
	
	private void delOldFile(
			int article_no, List<String> oldFileNameList, List<String> newFileNameList) {
		
		try {
			BOARD_IMG_DIR=this.getClass().getResource("").getPath();
			BOARD_IMG_DIR=BOARD_IMG_DIR.substring(1,BOARD_IMG_DIR.indexOf(".metadata"));
			BOARD_IMG_DIR=BOARD_IMG_DIR.replace("/", "\\");
			BOARD_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\boardImages\\"+article_no+"\\";
			
			for(String oldFileName : oldFileNameList) {
				
				if(!newFileNameList.contains(oldFileName)) {
					logger.info("삭제될 파일 이름={}",oldFileName);
					File oldFile = new File(BOARD_IMG_DIR+"\\"+oldFileName);
					oldFile.delete();
				}
				
			}
			
			
		} catch (Exception e) {
			logger.info("오래된 이미지 파일 삭제하는 중 에러");
			e.printStackTrace();
		}
		
	}
	

}
