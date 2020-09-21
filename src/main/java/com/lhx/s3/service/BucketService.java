package com.lhx.s3.service;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.util.StringUtils;

import java.util.*;

public class BucketService extends BaseService{
    /***
    * @Description: 根据bucket名字创建bucket
    * @Param: [bucketName] 
    * @return: com.amazonaws.services.s3.model.Bucket 
    * @Author: Garo 
    * @Date: 2020/7/9 
    */ 
    public Bucket createBucket(String bucketName){
        Bucket bucket = new Bucket();
        try {
            bucket = amazonS3.createBucket(bucketName);
            logger.info("已创建的bucket：" + bucket.getName() + "\t" + bucket.getOwner() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
        return bucket;
    }

    /***
    * @Description: 查询全部bucket列表
    * @Param: []
    * @return: java.util.List<com.amazonaws.services.s3.model.Bucket> 
    * @Author: Garo 
    * @Date: 2020/7/9 
    */ 
    public List<Bucket> getBuckets(){
        List<Bucket> buckets = new List<Bucket>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Bucket> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Bucket bucket) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Bucket> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Bucket> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Bucket get(int index) {
                return null;
            }

            @Override
            public Bucket set(int index, Bucket element) {
                return null;
            }

            @Override
            public void add(int index, Bucket element) {

            }

            @Override
            public Bucket remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Bucket> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Bucket> listIterator(int index) {
                return null;
            }

            @Override
            public List<Bucket> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        try {
            buckets = amazonS3.listBuckets();
//            for (Bucket bucket : buckets) {
//                logger.info("已存在的bucket：" + bucket.getName() + "\t" + bucket.getOwner() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
//            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
        return buckets;
    }

    /***
    * @Description: 根据bucket名字删除bucket
    * @Param: [bucketName]
    * @return: com.amazonaws.services.s3.model.Bucket
    * @Author: Garo
    * @Date: 2020/7/9
    */
    public Bucket deleteBucket(String bucketName){
        Bucket bucket = new Bucket();
        try {
            if(this.isBucketExists(bucketName)){
                amazonS3.deleteBucket(bucketName);
                logger.info("已删除的bucket：" + bucket.getName() + "\t" + bucket.getOwner() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
            }else {
                logger.error("要删除的bucket不存在！");
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
        return bucket;
    }

    /***
    * @Description: 检查bucket是否存在
    * @Param: [bucketName]
    * @return: boolean
    * @Author: Garo
    * @Date: 2020/7/9
    */
    public boolean isBucketExists(String bucketName){
        List<Bucket> buckets = this.getBuckets();
        for (Bucket bucket : buckets) {
            return bucket.getName().equals(bucketName);
        }
        return false;
    }
}
