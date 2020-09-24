package com.lhx.s3.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.lhx.s3.service.BucketService;
import com.lhx.s3.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Bucket> buckets = bucketService.getBuckets();
        return buckets;
    }
}
