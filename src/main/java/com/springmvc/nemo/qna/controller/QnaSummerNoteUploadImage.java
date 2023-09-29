package com.springmvc.nemo.qna.controller;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@Controller("qnaSummerNoteUploadImage")
public class QnaSummerNoteUploadImage {

private static String QNA_IMG_TEMP;
	
	private static final Logger logger = LoggerFactory.getLogger(QnaSummerNoteUploadImage.class);
	
	
	@ResponseBody
	@RequestMapping(value = "/qnasnuploadimage", produces = "application/json; charset=utf8")
	public String SNUploadImage(
			@RequestParam(value = "file") MultipartFile multipartFile,
			HttpServletRequest request) throws Exception{
		
		JsonObject jsonObject = new JsonObject();
		
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//logger.info("contextRoot={}", contextRoot);
		
		QNA_IMG_TEMP=this.getClass().getResource("").getPath();
		QNA_IMG_TEMP=QNA_IMG_TEMP.substring(1,QNA_IMG_TEMP.indexOf(".metadata"));
		QNA_IMG_TEMP=QNA_IMG_TEMP.replace("/", "\\");
		QNA_IMG_TEMP+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\temp\\";
		
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = UUID.randomUUID() + extension;
		
		File targetFile = new File(QNA_IMG_TEMP+savedFileName);
		
		try {
			
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/nemo/qnasngetimage/gettempimage?savedfilename="+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			
			
		} catch (Exception e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		String jsonString = jsonObject.toString();
		//logger.info("jsonString={}",jsonString);
		return jsonString;
		
	}
	
	
}
