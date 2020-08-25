package com.lhx.s3.service;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.InputStream;

public class ObjectService extends BucketService{


    public S3ObjectSummary createObject(String bucketName, String objectName, InputStream inputStream){
        S3ObjectSummary object = new S3ObjectSummary();
        amazonS3.putObject(bucketName, objectName, inputStream, new ObjectMetadata());
        return object;
    }

    public ObjectListing getObject(String bucketName){
        ObjectListing objects = new ObjectListing();
        objects = amazonS3.listObjects(bucketName);
        return objects;
    }
}
