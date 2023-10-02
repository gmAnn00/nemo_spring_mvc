package com.springmvc.nemo.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.springmvc.nemo.user.vo.KakaoVO;

public class GetKakaoImage {

	private static String USER_IMG_REPO;

	public void getImage(KakaoVO kakaoVO, String user_img) throws IOException{

		// 이미지 파일 업로드
		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\userImages\\";
		USER_IMG_REPO += kakaoVO.getUser_id();

		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		String kakaoURL = kakaoVO.getKakao_img();

		try {
			
			File file = new File(USER_IMG_REPO);
			file.mkdir();
			FileUtils.cleanDirectory(file);

			url = new URL(kakaoURL);
			in = url.openStream();
			out = new FileOutputStream(USER_IMG_REPO + "\\" + user_img);

			while (true) {
				int data = in.read();

				if (data == -1) {
					break;
				}
				
				out.write(data);
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			
			if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            
		} // end of finally

	}

}
