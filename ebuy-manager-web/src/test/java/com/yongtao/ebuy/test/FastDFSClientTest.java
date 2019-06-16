package com.yongtao.ebuy.test;


import com.yongtao.ebuy.util.fdfs.FastDFSClient;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class FastDFSClientTest
{
    //fdfs_client 核心配置文件
    private String conf_filename = "src/main/resources/fdfs_client.conf";

    //@Test
    public void testFastDFSClient() {
        FastDFSClient client = new FastDFSClient();
        try {
            File file = new File("E:\\MyDocuments\\Pictures\\电脑壁纸\\timg (2).jpg");
            String name = client.uploadFile(file.getAbsolutePath(), "jpg");
            System.out.println(name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //@Test
    public void testUpload() {    //上传文件
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            ClientGlobal.init(conf_filename);
            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //要上传的文件路径
            String local_filename = "D:/Users/xiaorui/test1.png";
//            这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
//            NameValuePair nvp [] = new NameValuePair[]{
//                    new NameValuePair("age", "18"),
//                    new NameValuePair("sex", "male")
//            };

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//          String fileIds[] = storageClient.upload_file(local_filename, "png", nvp);
            String fileIds[] = storageClient.upload_file(local_filename, "png", null);

            System.out.println(fileIds.length);
            System.out.println("组名：" + fileIds[0]);
            System.out.println("路径: " + fileIds[1]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //@Test
    public void testDownload() {    //下载文件
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/wKgWiV0EcviAdSGcAACawXPjLDA421.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] bytes = storageClient.download_file(groupName, filePath);

            String storePath = "D:/Users/xiaorui/download.png";
            OutputStream out = new FileOutputStream(storePath);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //@Test
    public void testGetFileInfo() { //获取文件信息
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/wKgWiV0EcviAdSGcAACawXPjLDA421.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo file = storageClient.get_file_info(groupName, filePath);
            System.out.println("ip--->" + file.getSourceIpAddr());
            System.out.println("文件大小--->" + file.getFileSize());
            System.out.println("文件上传时间--->" + file.getCreateTimestamp());
            System.out.println(file.getCrc32());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //@Test
    public void testGetFileMate() { //获取文件的原数据类型
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/wKgWiV0EcviAdSGcAACawXPjLDA421.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);

            //这个值是上传的时候指定的NameValuePair
            NameValuePair nvps[] = storageClient.get_metadata(groupName, filePath);
            if (null != nvps && nvps.length > 0) {
                for (NameValuePair nvp : nvps) {
                    System.out.println(nvp.getName() + ":" + nvp.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //@Test
    public void testDelete() { //删除文件
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/wKgWiV0AcxWAavvJCXpxcBcACBQ.tar.gz";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            int i = storageClient.delete_file(groupName, filePath);
            System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}