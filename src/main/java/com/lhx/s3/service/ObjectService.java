package com.lhx.s3.service;

import com.amazonaws.services.s3.model.*;

import java.io.File;

public class ObjectService extends BucketService{

    /***
    * @Description: 创建对象
    * @Param: [bucketName, objectName, file]
    * @return: com.amazonaws.services.s3.model.S3ObjectSummary
    * @Author: Garo
    * @Date: 2020/9/15
    */
    public S3ObjectSummary createObject(String bucketName, String objectName, File file){
        S3ObjectSummary object = new S3ObjectSummary();
        amazonS3.putObject(bucketName, objectName, file);
        return object;
    }

    /**
    * @Description: 获取对象列表
    * @Param: [bucketName]
    * @return: com.amazonaws.services.s3.model.ObjectListing
    * @Author: Garo
    * @Date: 2020/9/15
    */
    public ObjectListing getObjects(String bucketName){
        ObjectListing objects = new ObjectListing();
        objects = amazonS3.listObjects(bucketName);
        return objects;
    }

    public void deleteObject(String objectName){

    }

    public boolean isObjectExists(String objectName){
        return false;
    }
}
