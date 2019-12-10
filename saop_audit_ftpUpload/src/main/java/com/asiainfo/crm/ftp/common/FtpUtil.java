package com.asiainfo.crm.ftp.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 不适合SFTP方式
 * @author yong
 *
 */
public class FtpUtil implements IFtpClient{
	
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	private FTPClient ftpClient = new FTPClient();
	
	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	/**
	 * 初始化FTPClient
	 * @param serverIp
	 * @param ftpUsername
	 * @param ftpPassword
	 * @param port
	 * @throws Exception
	 */
	public FtpUtil(String serverIp, String ftpUsername,
			String ftpPassword,int port,int bufferSize) throws Exception{
		initConnectFtpServ(serverIp,ftpUsername,ftpPassword,port,bufferSize);
	}
	
	// 初始化FTP连接
	public void initConnectFtpServ(String serverIp, String ftpUsername,
			String ftpPassword,int port,int bufferSize) throws Exception {
		try {
			ftpClient.connect(serverIp, port);
			boolean flag = ftpClient.login(ftpUsername, ftpPassword);
			if (!flag) {
				throw new Exception("初始化FTP连接异常:" + "FTP密码不正确");
			}
			ftpClient.setBufferSize(bufferSize);
			ftpClient.setControlEncoding("UTF-8");
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		} catch (IOException e) {
			throw new Exception("初始化FTP连接异常:" + e);
		}
	}
	
	/**
	 * 往上抛异常
	 * 普通FTP上传
	 * @param localPath 本地上传文件目录
	 * @param remoteUploadPath 远程FTP目录
	 * @param fileName 本地上传文件名
	 * @return
	 * @throws BMOException
	 */
	public boolean uploadFileToFtpServ( String loaclPath,String remoteUploadPath, String fileName)
			throws Exception {
		BufferedInputStream input = null;
		boolean success = false;
		File srcFile = new File(loaclPath+fileName);
		try {
			if (ftpClient.isConnected()) {
				if (srcFile.exists()) {
					logger.debug("uploading file="+fileName);
					input = new BufferedInputStream(new FileInputStream(srcFile));
					boolean flag = ftpClient.changeWorkingDirectory(remoteUploadPath);
					if (flag){
						logger.info("----------------------------ftp文件上传成功----------------------------------------");
					}else{
						logger.error("-------------------------ftp文件上传失败，请检查文件路径----------------------------");
					}
					ftpClient.enterLocalPassiveMode();
					// 要求上传过程中为.ing的文件，上传结束后为.txt的文件
					ftpClient.storeFile(srcFile.getName() + ".ing", input);
					ftpClient.rename(srcFile.getName() + ".ing", srcFile
							.getName());
					logger.debug("uploaded file="+fileName+" successed!");
					success = true;
				}else{
					throw new Exception(srcFile+"文件不存在");
				}
			}
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return success;
	}
	
	// 关闭FTP连接
	public static void closeFtpConnection(FtpUtil ftpUtil) throws Exception {
		if (ftpUtil.getFtpClient() != null && ftpUtil.getFtpClient().isConnected()) {
			try {
				logger.debug("关闭FTP连接");
				ftpUtil.getFtpClient().disconnect();
			} catch (IOException e) {
				throw new Exception("关闭FTP连接异常:" + e);
			}
		}
	}


	@Override
	public void downloadFileFromFtpServ(String localPath, String remotePath, String flieName)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
