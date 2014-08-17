package com.inncretech.merchant.utility;

import java.util.UUID;

public class ImageUtil {

	public static String getExtension(String fileName) {
		String extension = "";
		if (fileName.indexOf(".") != -1) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return extension;
	}

	public static String getFileName(String extension) {
		String fileName = null;
		if (extension != null) {
			fileName = UUID.randomUUID().toString() + "." + extension;
		} else {
			fileName = UUID.randomUUID().toString();
		}

		return fileName;
	}
}
