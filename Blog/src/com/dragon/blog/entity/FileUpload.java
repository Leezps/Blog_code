package com.dragon.blog.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload {
	private Map<String, String> params;
	private Map<String, FileItem> files;

	public FileUpload() {
		params = new HashMap<String, String>();
		files = new HashMap<String, FileItem>();
	}

	public void setMap(HttpServletRequest request, String key) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		upload.setProgressListener(new Progress(request, key));// 设置进度监听
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					params.put(name, value);
				} else {
					String name = item.getFieldName();
					files.put(name, item);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}

	public Map<String, String> getParams() {
		return params;
	}

	public Map<String, FileItem> getFiles() {
		return files;
	}

	public String getFileName(FileItem item) {
		String fName = item.getName();
		int lastIndex = fName.lastIndexOf("\\");
		fName = fName.substring(lastIndex + 1);
		return fName;
	}

}
