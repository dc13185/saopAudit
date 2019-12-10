package com.asiainfo.crm.ftp.common;

public interface IFtpClient {

	/**
	 * @author zhouyong
	 * @param localPath
	 *            本地文件生产的目录
	 * @param remotePath
	 *            上传到服务器的目录
	 * @param flieName
	 *            文件名
	 * @return true success | false fail
	 * @throws Exception
	 */
	public  boolean uploadFileToFtpServ(String localPath, String remotePath, String flieName)
			throws Exception ;
	
	/**
	 * @author zhouyong
	 * @param localPath
	 *            本地文件生产的目录
	 * @param remotePath
	 *            上传到服务器的目录
	 * @param flieName
	 *            文件名
	 * @return true success | false fail
	 * @throws Exception
	 */
	public void downloadFileFromFtpServ(String localPath, String remotePath, String flieName)
			throws Exception ;
	
}
