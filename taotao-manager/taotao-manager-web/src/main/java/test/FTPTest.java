package test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

/**
 * @author tushengtao
 */
public class FTPTest {

	@Test
	public void testFtpClient() throws Exception {
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接。默认是21端口
		ftpClient.connect("192.168.109.128", 21);
		//登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "luodan");
		//上传文件。
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\tushengtao\\Desktop\\test1.png"));
		//设置上传到图片服务器的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("2.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
	@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\tushengtao\\Desktop\\test1.png"));
		FtpUtil.uploadFile("192.168.109.128", 21, "ftpuser", "luodan", "/home/ftpuser/www/images", "/2019/10/22", "2.jpg", inputStream);
		
	}
}
