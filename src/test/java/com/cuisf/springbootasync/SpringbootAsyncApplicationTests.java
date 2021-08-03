package com.cuisf.springbootasync;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootAsyncApplicationTests {


    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("cuisf 你好啊");
        mailMessage.setText("加油啊");

        mailMessage.setTo("workercsf@163.com");
        mailMessage.setFrom("workercsf@163.com");

        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads1() throws MessagingException {


    }


    /**
     * //邮件抽离成公共方法
     * @param html  是否开启标签语言
     * @param title  邮件标题
     * @param text   邮件内容
     * @param mailTo  发送给谁
     * @param MailFrom  谁发送的
     * @throws MessagingException
     */
    public  void sendMail(Boolean html,String title,String text,String mailTo,String MailFrom) throws MessagingException {
        //一个复杂的邮件

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装邮件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, html);

        //设置主题
        helper.setSubject(title);
        //设置文本 设置为true  可以开启标签语言
        helper.setText(text,true);

        //设置附件
        helper.addAttachment("Swagger使用.txt",new File("C:\\Users\\Administrator\\Desktop\\Swagger使用.txt"));


        helper.setTo(mailTo);
        helper.setFrom(MailFrom);

        mailSender.send(mimeMessage);

    }


}
