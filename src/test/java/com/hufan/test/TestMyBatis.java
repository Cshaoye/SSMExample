package com.hufan.test;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hufan.pojo.User;
import com.hufan.service.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:springmvc-mybatis.xml"})
public class TestMyBatis {
	private static Log log = LogFactory.getLog(TestMyBatis.class);
//	private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;

	@Test
	public void test1() {
		User user = userService.getUserById(3);
		log.info(JSON.toJSONString(user));
	}
}
