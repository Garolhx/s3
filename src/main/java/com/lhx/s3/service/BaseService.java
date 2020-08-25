package com.lhx.s3.service;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

import static com.amazonaws.endpointdiscovery.Constants.ENDPOINT;


@Service
public class BaseService {
    protected static AmazonS3 amazonS3 = null;
    private static String accessKey;
    private static String secretKey;
    private static String host;
    protected static Logger logger = LoggerFactory.getLogger(BaseService.class);
    private static Properties properties = new Properties();

    static{
        try{
            properties.load(BaseService.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error("配置文件没找到！");
        }

        accessKey = properties.getProperty("ceph.accessKey");
        secretKey = properties.getProperty("ceph.secretKey");
        host = properties.getProperty("ceph.gateway");
        logger.info("开始初始化ceph配置！\n" + accessKey + host + secretKey);

        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setProtocol(Protocol.HTTP);

        amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(host, ""))
                .build();

        logger.info("ceph配置初始化成功！");
    }
}
