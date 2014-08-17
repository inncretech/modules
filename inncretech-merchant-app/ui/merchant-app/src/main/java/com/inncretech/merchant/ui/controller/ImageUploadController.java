package com.inncretech.merchant.ui.controller;

import static com.inncretech.merchant.utility.ImageUtil.getExtension;
import static com.inncretech.merchant.utility.ImageUtil.getFileName;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inncretech.merchant.constants.UrlConstants;
import com.inncretech.merchant.ui.bean.ImageBean;

@Controller
public class ImageUploadController {

	private static final String IMAGE_TEMP_STORE = "/var/www/images/";
	private static final String IMAGE_URL = "http://localhost/";

	@RequestMapping(value = UrlConstants.UPLOAD_IMAGE, method = { RequestMethod.POST })
	public @ResponseBody ImageBean uploadMedia(@RequestParam("image") MultipartFile image) throws Exception {

		String extension = getExtension(image.getOriginalFilename());
		BufferedImage tempimage = ImageIO.read(image.getInputStream());
		String fileName = getFileName(extension);
		ImageIO.write(tempimage, extension, new File(IMAGE_TEMP_STORE + fileName));
		ImageBean imageBean = new ImageBean();
		imageBean.setImageUrl(IMAGE_URL + fileName);
		return imageBean;
	}

}
