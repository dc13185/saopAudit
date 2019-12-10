package com.asiainfo.crm.ftp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.crm.bcomm.exception.WarmMsg;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 适用于SFTP
 * @author yong
 *
 */
public class SFTPUtil implements IFtpClient{
	private static final Logger logger = LoggerFactory.getLogger(SFTPUtil.class);
	Session session = null;
	
	public SFTPUtil(String ip, String user, String psw,int port) throws Exception{
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}
			session.setPassword(psw);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(60000);
		} catch (Exception e) {
			logger.error("连不上服务器，请检查IP和端口是否正常或者密码是否正确",e);
			throw new Exception("连不上服务器，请检查IP和端口是否正常或者密码是否正确");
		}
	}
	
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
			throws Exception {
		boolean result = false;
		Channel channel = null;
		try {
			// 设置登陆主机的密码
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(60000);
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.cd(remotePath);
			logger.debug("uploading file="+flieName);
			// 上传文件的时候用ing，上传完毕恢复
			OutputStream outstream = sftp.put(flieName + ".ing");
			InputStream instream = new FileInputStream(new File(localPath
					+ flieName));
			byte b[] = new byte[1024];
			int n;
			while ((n = instream.read(b)) != -1) {
				outstream.write(b, 0, n);
			}
			outstream.flush();
			outstream.close();
			instream.close();
			// 恢复文件名
			sftp.rename(flieName + ".ing",flieName);
			logger.debug("uploaded file="+flieName+" successed!");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读写文件异常,请检查硬盘空间和权限", e);
			throw new WarmMsg("CRM_FTP_001","读写文件异常,请检查硬盘空间和权限以及文件是否存在");
		} catch (JSchException e) {
			e.printStackTrace();
			logger.error("请检查用户名密码是否正确", e);
			throw new WarmMsg("CRM_FTP_001","请检查用户名密码是否正确");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("其他异常", e);
			throw new WarmMsg("CRM_FTP_001","其他异常"+e.getMessage());
		} finally {
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}
		result = true;
		return result;
	}
	
	/**
	 * @author zhouyong
	 * @param ip
	 * @param user
	 * @param psw
	 * @param port
	 * @param localPath
	 *            本地文件生产的目录
	 * @param remotePath
	 *            上传到服务器的目录
	 * @param flieName
	 *            文件名
	 * @return true success | false fail
	 * @throws Exception
	 */
	public static boolean uploadFileToFtpServ(String ip, String user, String psw,
			int port, String localPath, String remotePath, String flieName)
			throws Exception {
		boolean result = false;
		Session session = null;
		Channel channel = null;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}
		} catch (Exception e) {
			logger.error("连不上服务器，请检查IP和端口是否正常",e);
			throw new WarmMsg("CRM_FTP_001","连不上服务器，请检查IP和端口是否正常");
		}
		try {
			// 设置登陆主机的密码
			session.setPassword(psw);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(60000);
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(60000);
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.cd(remotePath);
			// 上传文件的时候用ing，上传完毕恢复
			OutputStream outstream = sftp.put(flieName + ".ing");
			InputStream instream = new FileInputStream(new File(localPath
					+ flieName));
			byte b[] = new byte[1024];
			int n;
			while ((n = instream.read(b)) != -1) {
				outstream.write(b, 0, n);
			}
			outstream.flush();
			outstream.close();
			instream.close();
			// 恢复文件名
			sftp.rename(flieName + ".ing",flieName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读写文件异常,请检查硬盘空间和权限", e);
			throw new WarmMsg("CRM_FTP_001","读写文件异常,请检查硬盘空间和权限以及文件是否存在");
		} catch (JSchException e) {
			e.printStackTrace();
			logger.error("请检查用户名密码是否正确", e);
			throw new WarmMsg("CRM_FTP_001","请检查用户名密码是否正确");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("其他异常", e);
			throw new WarmMsg("CRM_FTP_001","其他异常"+e.getMessage());
		} finally {
			if (session != null)
				session.disconnect();
			if (channel != null)
				channel.disconnect();
		}
		result = true;
		return result;
	}

	public static void sshSftp(String ip, String user, String psw
			, int port, String privateKey, String passphrase) throws Exception {
				Session session = null;
				Channel channel = null;
				JSch jsch = new JSch();
				// 设置密钥和密码
				if (privateKey != null && !"".equals(privateKey)) {
					if (passphrase != null && "".equals(passphrase)) {
						// 设置带口令的密钥
						jsch.addIdentity(privateKey, passphrase);
					} else {
						// 设置不带口令的密钥
						jsch.addIdentity(privateKey);
					}
				}
				if (port <= 0) {
					// 连接服务器，采用默认端口
					session = jsch.getSession(user, ip);
				} else {
					// 采用指定的端口连接服务器
					session = jsch.getSession(user, ip, port);
				}
				// 如果服务器连接不上，则抛出异常
				if (session == null) {
					throw new Exception("服务器连接不上，请检查网络或者用户名密码！");
				}
				// 设置登陆主机的密码
				session.setPassword(psw);// 设置密码
				// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
				session.setConfig("StrictHostKeyChecking", "no");
				// 设置登陆超时时间
				session.connect(60000);
				try {
					// 创建sftp通信通道
					channel = (Channel) session.openChannel("sftp");
					channel.connect(60000);
					ChannelSftp sftp = (ChannelSftp) channel;
					// 进入服务器指定的文件夹
					sftp.cd("domains");
					// 列出服务器指定的文件列表
					Vector v = sftp.ls("*.txt");
					// 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
					OutputStream outstream = sftp.put("1.txt");
					InputStream instream = new FileInputStream(new File("c:/print.txt"));
					byte b[] = new byte[1024];
					int n;
					while ((n = instream.read(b)) != -1) {
						outstream.write(b, 0, n);
					}
					outstream.flush();
					outstream.close();
					instream.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.disconnect();
					channel.disconnect();
				}

			}

	@Override
	public void downloadFileFromFtpServ(String localPath, String remotePath,
			String flieName) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
