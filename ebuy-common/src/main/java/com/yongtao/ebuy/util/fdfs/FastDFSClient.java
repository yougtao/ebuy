package com.yongtao.ebuy.util.fdfs;


import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FastDFSClient
{
    private String confPath;


    public FastDFSClient() {
        try {
            ClientGlobal.init("E:\\Study Home\\Java Study\\com.ebuy\\ebuy-common\\src\\main\\resources\\fdfs_client.conf");
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }


    public String uploadFile(String fileName, String ext) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        String[] infos = storageClient.upload_file(fileName, ext, null);

        return infos[0] + "/" + infos[1];
    }

    public String uploadFile(byte[] fileData, String ext) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        String[] infos = storageClient.upload_file(fileData, ext, null);
        return infos[0] + "/" + infos[1];
    }

}
