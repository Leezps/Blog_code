package com.dragon.blog.entity;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class Progress implements ProgressListener {

	private HttpSession session;

	private long length = 0;

	private long currentLength = 0;

	private boolean isComplete = false;

	private double megaBytes = -1;

	private String key;

	public Progress(HttpServletRequest request, String key) {
		this.session = request.getSession();
		this.key = key;
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int items) {
		this.currentLength = pBytesRead;
		double mBytes = pBytesRead / 1000000;
		double total = pContentLength / 1000000;
		if (megaBytes == mBytes) {
			return;
		}
		megaBytes = mBytes;
		if (pContentLength == -1) {

		} else {
			double read = (mBytes / total);
			NumberFormat nf = NumberFormat.getPercentInstance();

			session.setAttribute(key, nf.format(read));
		}
	}

	public long getLength() {
		return length;
	}

	public long getCurrentLength() {
		return currentLength;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setCurrentLength(long currentLength) {
		this.currentLength = currentLength;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
}
