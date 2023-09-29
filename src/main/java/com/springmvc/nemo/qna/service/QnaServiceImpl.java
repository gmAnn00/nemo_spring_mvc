package com.springmvc.nemo.qna.service;

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

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.qna.dao.QnaDAO;
import com.springmvc.nemo.qna.vo.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	private static String QNA_IMG_DIR;

	private static final Logger logger = LoggerFactory.getLogger(QnaServiceImpl.class);

	@Override
	public Map<String, Object> listQna(Map<String, Object> pagingMap) throws DataAccessException {

		Map<String, Object> qnaMap = new HashMap<String, Object>();

		int totQna = qnaDAO.getTotQna();
		qnaMap.put("totQna", totQna);

		List<QnaVO> qnaList = new ArrayList<QnaVO>();

		int admin = (Integer) pagingMap.get("admin");

		if (admin == 1) {
			qnaList = qnaDAO.getAdminQnaList(pagingMap);
		} else {
			qnaList = qnaDAO.getUserQnaList(pagingMap);
		}

		qnaMap.put("qnaList", qnaList);

		return qnaMap;
	}

	@Override
	public QnaVO getQna(int qna_no) throws DataAccessException {

		return qnaDAO.getQna(qna_no);
	}

	@Override
	public int addQna(QnaVO qnaVO) throws DataAccessException {

		int qna_no = qnaDAO.getNewQnaNo();

		qnaVO.setQna_no(qna_no);

		String[] imgName = qnaVO.getImageName();
		String content = qnaVO.getContent();

		if (qnaVO.getIsImgExist()) {
			List<String> fileNameList = new ArrayList<String>(Arrays.asList(imgName));
			moveImageDir(fileNameList, qna_no);
			content = content.replace("/gettempimage?", "/getimage?qna_no=" + qna_no + "&");
			qnaVO.setContent(content);
		}

		qnaDAO.addQna(qnaVO);

		return qna_no;
	}

	@Override
	public void modQna(QnaVO qnaVO) throws DataAccessException {

		QnaVO oldQna = qnaDAO.getQna(qnaVO.getQna_no());
		String oldContent = oldQna.getContent();
		int qna_no = oldQna.getQna_no();

		String[] imgName = qnaVO.getImageName();
		String newContent = qnaVO.getContent();

		if (qnaVO.getIsImgExist()) {
			List<String> fileNameList = new ArrayList<String>(Arrays.asList(imgName));
			// content 업데이트
			newContent = newContent.replace("/gettempimage?", "/getimage?qna_no=" + qna_no + "&");
			qnaVO.setContent(newContent);

			List<String> oldFileNameList = getImageFileName(oldContent, qna_no);
			List<String> newFileNameList = getImageFileName(newContent, qna_no);

			logger.info("oldContent={}", oldContent);
			logger.info("newContent={}", newContent);

			logger.info("oldFileNameList={}", oldFileNameList.toString());
			logger.info("newFileNameList={}", newFileNameList.toString());

			// 수정되어서 없어진 이미지 삭제
			delOldFile(qna_no, oldFileNameList, newFileNameList);

			// temp에 올라온 이미지 옮기기
			moveImageDir(fileNameList, qna_no);
		}

		qnaDAO.modQna(qnaVO);

	}
	
	
	@Override
	public boolean getAccessible(Map<String, Object> accessMap) throws DataAccessException {
		
		int accessible = qnaDAO.getAccessible(accessMap);
		
		if(accessible == 0) {
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public void delQna(int qna_no) throws DataAccessException {
	
		qnaDAO.delQna(qna_no);
		
		try {
			
			QNA_IMG_DIR=this.getClass().getResource("").getPath();
			QNA_IMG_DIR=QNA_IMG_DIR.substring(1,QNA_IMG_DIR.indexOf(".metadata"));
			QNA_IMG_DIR=QNA_IMG_DIR.replace("/", "\\");
			QNA_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\"+qna_no;
			
			File qnaDir = new File(QNA_IMG_DIR);
			FileUtils.deleteDirectory(qnaDir);
			
		} catch (Exception e) {
			logger.info("게시글 삭제 중 에러");
			e.printStackTrace();
		}
		
	}

	
	
	// temp 에서 이미지 폴더 이동 하는 메소드
	private void moveImageDir(List<String> fileNameList, int qna_no) {
		// qnano로 폴더 생성
		// tmp에서 qnano폴더로 이동
		try {
			QNA_IMG_DIR = this.getClass().getResource("").getPath();
			QNA_IMG_DIR = QNA_IMG_DIR.substring(1, QNA_IMG_DIR.indexOf(".metadata"));
			QNA_IMG_DIR = QNA_IMG_DIR.replace("/", "\\");
			QNA_IMG_DIR += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages";

			if (fileNameList != null && fileNameList.size() != 0) {
				for (String imgName : fileNameList) {
					File srcFile = new File(QNA_IMG_DIR + "\\temp\\" + imgName);
					File destDir = new File(QNA_IMG_DIR + "\\" + qna_no);
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
	private List<String> getImageFileName(String content, int article_no) {
		List<String> fileNameList = new ArrayList<String>();

		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String[] arr = matcher.group(1).split("getimage\\?qna\\_no=" + article_no + "&amp\\;savedfilename=");
			if (arr.length > 1) {
				logger.info("getImageFileName={}", arr[1]);
				fileNameList.add(arr[1]);
			}
		}

		return fileNameList;
	}

	private void delOldFile(
			int qna_no, List<String> oldFileNameList, List<String> newFileNameList) {

		try {
			QNA_IMG_DIR = this.getClass().getResource("").getPath();
			QNA_IMG_DIR = QNA_IMG_DIR.substring(1, QNA_IMG_DIR.indexOf(".metadata"));
			QNA_IMG_DIR = QNA_IMG_DIR.replace("/", "\\");
			QNA_IMG_DIR += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\" + qna_no + "\\";

			for (String oldFileName : oldFileNameList) {

				if (!newFileNameList.contains(oldFileName)) {
					logger.info("삭제될 파일 이름={}", oldFileName);
					File oldFile = new File(QNA_IMG_DIR + "\\" + oldFileName);
					oldFile.delete();
				}

			}

		} catch (Exception e) {
			logger.info("오래된 이미지 파일 삭제하는 중 에러");
			e.printStackTrace();
		}

	}

}
