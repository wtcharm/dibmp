package cn.mldn.util.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FastDFS {
	public static String upload(MultipartFile pic) {
		try {
			// 需要获得文件的后缀的名称信息，所有的上传文件都有mime类型；
			String ext = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf(".") + 1);
			// 通过ClassPath路径获取要使用的配置文件，实现了资源的定位 
			ClassPathResource classPathResource = new ClassPathResource("config/fastdfs.properties");
			// 进行客户端访问的整体配置，需要知道配置文件的完整路径
			ClientGlobal.init(classPathResource.getClassLoader().getResource("config/fastdfs.properties").getPath());
			// FastDFS的核心操作在于tracker处理上，所以此时需要定义Tracker客户端
			TrackerClient trackerClient = new TrackerClient();
			// 定义TrackerServer的配置信息
			TrackerServer trackerServer = trackerClient.getConnection();
			// 在整个FastDFS之中真正负责干活的就是Storage
			StorageServer storageServer = null;
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
			// 定义上传文件的元数据
			NameValuePair[] metaList = new NameValuePair[3];
			metaList[0] = new NameValuePair("fileName", pic.getName());
			metaList[1] = new NameValuePair("fileExtName", ext);
			metaList[2] = new NameValuePair("fileLength", String.valueOf(pic.getSize()));
			// 如果要上传则使用trackerClient对象完成
			String upload_file = storageClient1.upload_file1(pic.getBytes(), ext, metaList);
			trackerServer.close();
			return upload_file;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}