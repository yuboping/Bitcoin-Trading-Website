package com.gsww.qyws.gzbd.web;

import com.gsww.qyws.gzbd.GzbdApplication;
 import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GzbdApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Ignore
public class BaseUnitTest {
}
