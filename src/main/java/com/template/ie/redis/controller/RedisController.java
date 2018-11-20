package com.template.ie.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.template.ie.redis.utils.RedisUtil;

@RestController
@RequestMapping("/redisController")
public class RedisController {

	@Autowired
	RedisUtil redisUtil;
	
	/**
     *@Description: 测试redis
     */
    @RequestMapping(value ="/testRedisAdd",method = RequestMethod.GET)
    @ResponseBody
    public Object testAdd(){
        redisUtil.set("Test","122345");
        return redisUtil.get("Test");
    }

}