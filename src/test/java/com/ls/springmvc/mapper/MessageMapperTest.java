package com.ls.springmvc.mapper;

import com.ls.springmvc.dao.MessageMapper;
import com.ls.springmvc.entity.Message;
import com.ls.system.user.dao.UserMapper;
import com.ls.system.user.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Auther: chenjun
 * @Date 2020年01月03日 10:53
 * @Description:
 */

public class MessageMapperTest {

    private ApplicationContext applicationContext;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;


    @Before
    public void setUp() throws Exception {

        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
//        messageMapper = applicationContext.getBean(MessageMapper.class,UserMapper.class);
          userMapper = applicationContext.getBean(UserMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws  Exception{
        Message message = new Message();
        message.setCommand("吃饭");
        message.setContent("睡觉");
        message.setDescription("打豆豆");
        int result = messageMapper.insert(message);
        System.out.println(result);
        assert (result == 1);
    }

    @Test
    public void select() throws  Exception{

      List<User> userList= userMapper.selectUserByUserName("孙");
        System.out.println(userList);
    }
}