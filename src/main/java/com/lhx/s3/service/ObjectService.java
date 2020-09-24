package com.lhx.s3.service;

import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringUtils;

import java.io.File;
import java.util.List;

public class ObjectService extends BucketService{

    /***
    * @Description: 创建对象
    * @Param: [bucketName, objectName, file]
    * @return: com.amazonaws.services.s3.model.S3ObjectSummary
    * @Author: Garo
    * @Date: 2020/9/15
    */
    public PutObjectResult createObject(String bucketName, String objectName, File file){
        return amazonS3.putObject(bucketName, objectName, file);
    }

    /**
    * @Description: 获取对象列表
    * @Param: [bucketName]
    * @return: com.amazonaws.services.s3.model.ObjectListing
    * @Author: Garo
    * @Date: 2020/9/15
    */
    public ObjectListing getObjects(String bucketName){
        return amazonS3.listObjects(bucketName);
    }

    /**
    * @Description: 获取指定对象
    * @Param: [bucketName, objectName]
    * @return: com.amazonaws.services.s3.model.S3Object
    * @Author: Garo
    * @Date: 2020/9/22
    */
    public S3Object getObject(String bucketName, String objectName){
        return amazonS3.getObject(bucketName, objectName);
    }

    /**
    * @Description: 根据object名字删除object
    * @Param: [bucketName, objectName]
    * @return: void
    * @Author: Garo
    * @Date: 2020/9/24
    */
    public void deleteObject(String bucketName, String objectName){

        try {
            if(isObjectExists(bucketName, objectName)){
                amazonS3.deleteObject(bucketName, objectName);
                logger.info("已删除的object");
            }else {
                logger.error("要删除的object不存在！");
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isObjectExists(String bucketName, String objectName){
        ObjectListing objects = getObjects(bucketName);
        for(S3ObjectSummary objectSummary : objects.getObjectSummaries()){
            return objectSummary.getKey().equals(objectName);
        };
        return false;
    }
}
