package com.springmvc.nemo.board.controller;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;


@Controller("summerNoteUploadImage")
public class SummerNoteUploadImage {
	
	private static String BOARD_IMG_TEMP;
	
	private static final Logger logger = LoggerFactory.getLogger(SummerNoteUploadImage.class);
	
	
	@ResponseBody
	@RequestMapping(value = "/snuploadimage", produces = "application/json; charset=utf8")
	public String SNUploadImage(
			@RequestParam(value = "file") MultipartFile multipartFile,
			HttpServletRequest request) throws Exception{
		
		JsonObject jsonObject = new JsonObject();
		
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//logger.info("contextRoot={}", contextRoot);
		
		BOARD_IMG_TEMP=this.getClass().getResource("").getPath();
		BOARD_IMG_TEMP=BOARD_IMG_TEMP.substring(1,BOARD_IMG_TEMP.indexOf(".metadata"));
		BOARD_IMG_TEMP=BOARD_IMG_TEMP.replace("/", "\\");
		BOARD_IMG_TEMP+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\boardImages\\temp\\";
		
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = UUID.randomUUID() + extension;
		
		File targetFile = new File(BOARD_IMG_TEMP+savedFileName);
		
		try {
			
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/nemo/sngetimage/getreviewimage?savedfilename="+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			
			
		} catch (Exception e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		String jsonString = jsonObject.toString();
		logger.info("jsonString={}",jsonString);
		return jsonString;
		
	}
	
	

}
