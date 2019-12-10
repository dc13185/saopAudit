package com.asiainfo.crm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class TestAny {

	public static boolean uploadFile(String url, int port, String username, String password, String path,
			String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);

			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	public static void main(String[] args) {
		try {
			FileInputStream in = new FileInputStream(
					new File("D:/create4G/60010300011000000036BUS6002420171111U001.txt"));
			boolean flag = uploadFile("133.37.135.34", 21, "saop_audit", "saop_audit", "/data/upload",
					"60010300011000000036BUS6002420171111U001.txt", in);
			System.out.println(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
