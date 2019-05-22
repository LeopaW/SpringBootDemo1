package com.xa.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMailTest {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知:今晚开会");
        message.setText("今晚7:30开会");

        message.setTo("leopalyn@163.com");
        message.setFrom("378857905@qq.com");

        mailSender.send(message);
    }

    @Test
    public void test03() throws MessagingException {
        //1.创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("通知:今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会 </b>",true);

        helper.setTo("leopalyn@163.com");
        helper.setFrom("378857905@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\w'w'w\\Desktop\\1.JPG"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\w'w'w\\Desktop\\2.JPG"));

        mailSender.send(mimeMessage);
    }

}
