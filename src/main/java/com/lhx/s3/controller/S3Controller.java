package com.lhx.s3.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.lhx.s3.service.BucketService;
import com.lhx.s3.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/s3")
public class S3Controller {

    @Autowired
    BucketService bucketService;
    @Autowired
    ObjectService objectService;

    @RequestMapping(value = "/getBuckets")
    public List<Bucket> getBuckets(){
        return bucketService.getBuckets();
    }
    @RequestMapping(value = "/getObjects")
    public ObjectListing getObjects(String bucketName){
        return objectService.getObjects(bucketName);
    }
    @RequestMapping(value = "/getObject")
    public S3Object getObject(String bucketName, String objectName){
        return objectService.getObject(bucketName, objectName);
    }

    @RequestMapping(value = "/createBucket")
    public void createBucket(String bucketName){
        bucketService.createBucket(bucketName);
    }
    @RequestMapping(value = "/createObject")
    public void createObject(String bucketName, String objectName, File file){
        objectService.createObject(bucketName, objectName, file);
        //TODO
        //保存文件信息到数据库
    }

    @RequestMapping(value = "/deleteBucket")
    public void deleteBucket(String bucketName){
        bucketService.deleteBucket(bucketName);
    }
    @RequestMapping(value = "/deleteObject")
    public void deleteObject(String bucketName, String objectName){
        objectService.deleteObject(bucketName, objectName);
    }
}
