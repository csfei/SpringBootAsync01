springboot 整合async

步骤只需两步：


	1： 在异步请求方法上添加注解@Async

	2： 在系统启动类上  开启异步请求


	实例：

			a.异步请求方法上：
				@Service
				public class AsyncService {

				    @Async
				    public void hello(){

				        try {
				            Thread.sleep(3000);
				        } catch (InterruptedException e) {
				            e.printStackTrace();
				        }
				        System.out.println("数据正在处理");

				    }
				}
			b.系统启动类上：
				@EnableAsync
				@SpringBootApplication
				public class SpringbootAsyncApplication {

				    public static void main(String[] args) {
				        SpringApplication.run(SpringbootAsyncApplication.class, args);
				    }

				}

springboot 整合邮件任务

	1： 导入依赖文件：
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>


    2. 在配置文件中 配置：
    	spring.mail.username=*****@163.com
		spring.mail.password=YXJANGZPMTEYNREY
		spring.mail.host=smtp.163.com  
		#163.邮箱不需要开启加密验证  但是QQ邮箱需要开启加密验证
		#spring.mail.properties.mail.smtl.ssl.enable=true


	3: 编写工具类
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


   springboot 整合   定时任务


   1. 启动文件类  开启注解 
	   	@EnableAsync
		@EnableScheduling //开启定时器注解
		@SpringBootApplication
		public class SpringbootAsyncApplication {

		    public static void main(String[] args) {
		        SpringApplication.run(SpringbootAsyncApplication.class, args);
		    }

		}

	2.方法上添加注解：

		public class ScheduledService {


	    @Scheduled(cron = "0 * * * * 0-7")
	    //cron 表达式~
	    //秒  分  时  日  月  周几
	    //   cron = "59 59 23 * * ?"  每天的23：59：59 执行
	    public void  hello(){

	        System.out.println("定时器 开始执行了。。。。。");
	    }
	}




