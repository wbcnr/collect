/**
    操作的注意事项：
    （1）需要首先修改163邮箱的设置，打开SMTP，网易会要求发送短信验证，然后提示一个“验证码”，注意，这个验证码只能提示一次，关闭就没有了，
一定要记录下来。然后将邮箱的密码使用这个验证码即可。有效期好像是180天。
    （2）邮件的内容要尽量正规，不要写“test”、“测试”等内容，这些内容可能会被识别为垃圾邮件（可能）导致邮件发送失败。
    最后一次修改时间：2024年10月18日
*/
public class SendMail {

    private static SendMail instance = null;
    //发件人地址
    private  String addrFromMail = "xxxx@163.com";
    //发件人密码
    private  String pwMail = "xxxx";
    //收件人地址
    private  String[] addrToMail = {"xxx@qq.com"};

    private SendMail() {

    }

    public static SendMail getInstance() {
        if (instance == null) {
            instance = new SendMail();
        }
        return instance;
    }

    public void send() {
        try {

            // 接收者的邮箱
            //String to[] = { "xx@qq.com" };

            // 配置发送邮箱的配置--
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.transport.protocol", "smtp");
            p.put("mail.smtp.host", "smtp.163.com");
            p.put("mail.smtp.port", "25");

            // 建立会话
            Session session = Session.getInstance(p);
            // 建立信息
            Message msg = new MimeMessage(session);
            // 发件人
            msg.setFrom(new InternetAddress(addrFromMail));
            // 收件人
            String toList = getMailList(addrToMail);
            InternetAddress[] iaToList = InternetAddress.parse(toList);
            msg.setRecipients(Message.RecipientType.TO, iaToList);
            // 发送日期
            msg.setSentDate(new Date());
            // 主题
            msg.setSubject("测试邮件");
            // 内容
            msg.setText("注意，这是测试程序发的，请不要回复！");
            // 邮件服务器进行验证
            Transport tran = session.getTransport("smtp");
            System.out.println("连接邮件服务器");
            // *配置发送者的邮箱账户名和密码
            tran.connect("smtp.163.com", addrFromMail, pwMail);
            // 发送
            tran.sendMessage(msg, msg.getAllRecipients());
            System.out.println("邮件发送成功");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String getMailList(String[] mailArray) {

        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }
            }
        }
        return toList.toString();

    }

    public static void main(String[] args) {
        System.out.println("开始发送邮件");
        SendMail sendMail = SendMail.getInstance();
        sendMail.send();
        // System.out.println(System.nanoTime());
        // UUID uuid = UUID.randomUUID();
        // System.out.println(uuid);
    }
}
