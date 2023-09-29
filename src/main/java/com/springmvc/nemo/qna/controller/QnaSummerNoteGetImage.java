package com.springmvc.nemo.qna.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("qnaSummerNoteGetImage")
public class QnaSummerNoteGetImage {

private static String QNA_IMG_DIR;
	
	@RequestMapping(value = "/qnasngetimage/gettempimage")
	public void getReviewImage(
			@RequestParam("savedfilename") String savedFileName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String filePath;
		QNA_IMG_DIR=this.getClass().getResource("").getPath();
		QNA_IMG_DIR=QNA_IMG_DIR.substring(1,QNA_IMG_DIR.indexOf(".metadata"));
		QNA_IMG_DIR=QNA_IMG_DIR.replace("/", "\\");
		QNA_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\temp\\";
		filePath=QNA_IMG_DIR+savedFileName;
		getImage(filePath, response);
		
	}
	
	@RequestMapping(value = "/qnasngetimage/getimage")
	public void getImage(
			@RequestParam("savedfilename") String savedFileName,
			@RequestParam("qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String filePath;
		QNA_IMG_DIR=this.getClass().getResource("").getPath();
		QNA_IMG_DIR=QNA_IMG_DIR.substring(1,QNA_IMG_DIR.indexOf(".metadata"));
		QNA_IMG_DIR=QNA_IMG_DIR.replace("/", "\\");
		QNA_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\"+qna_no+"\\";
		filePath=QNA_IMG_DIR+savedFileName;
		getImage(filePath, response);
		
	}
	
	
	private void getImage(String filePath, HttpServletResponse response) throws IOException{
		File file=new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream in = new BufferedInputStream(fis);;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		
		int imgByte;
		while ((imgByte = in.read()) != -1) {
			bStream.write(imgByte);
		}
		String type = "";
		String ext = FilenameUtils.getExtension(file.getName());
		if (ext != null && !"".equals(ext)) {
			if ("jpg".equals(ext.toLowerCase())) {
				type = "image/jpeg";
			} else {
				type = "image/" + ext.toLowerCase();
			}	
		}

		response.setHeader("Content-Type", type);
		response.setContentLength(bStream.size());

		bStream.writeTo(response.getOutputStream());

		response.getOutputStream().flush();
		response.getOutputStream().close();
		bStream.close();
		in.close();
		fis.close();
	}
	
}
