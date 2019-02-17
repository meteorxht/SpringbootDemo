package com.cmcc.hui.goods.controller;

import com.cmcc.hui.goods.bean.TestGoodsBo;
import com.cmcc.hui.goods.dao.ITestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@RestController
//@CacheConfig(cacheNames = "mallTest")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ITestDao dao;

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/consumer")
    public String eurekaConsumer(){

        log.info("调用consumer方法");
        List<ServiceInstance> list = discoveryClient.getInstances("spring-cloud-eureka-provider");
        System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());

        for( String s :  discoveryClient.getServices()){
            System.out.println("services " + s);
            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
            for(ServiceInstance si : serviceInstances){
                System.out.println("    services:" + s + ":getHost()=" + si.getHost());
                System.out.println("    services:" + s + ":getPort()=" + si.getPort());
                System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
                System.out.println("    services:" + s + ":getUri()=" + si.getUri());
                System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
            }

        }

        System.out.println(list.size());
        if (list != null && list.size() > 0 ) {
            System.out.println( list.get(0).getUri()  );
        }

        return null;
    }

    @GetMapping("/provider")
    public String eurekaProvider(){

        return "OK";
    }

    @GetMapping("/insert")
    public String insert(){
        TestGoodsBo bo = new TestGoodsBo();
        bo.setName("手机");
        bo.setPrice(new BigDecimal("3888.88"));
        bo.setStock(3);
        dao.insert(bo);
        return "OK";
    }

    @GetMapping("/get")
    public TestGoodsBo get(long id){
        TestGoodsBo bo = dao.get(id);
        redisTemplate.opsForValue().set("test.goods",bo.getName());
//        redisTemplate.boundValueOps("test.goods").set(bo.getName());
        return bo;
    }

    @GetMapping("/getCache")
    public String getCache(){
        String result = redisTemplate.opsForValue().get("test.goods");
//        String result = redisTemplate.boundValueOps("test.goods").get();
        return result;
    }

}

