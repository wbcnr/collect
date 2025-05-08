
这是一个测试demo，实现java的自定义注解和处理器；
编译命令：
javac -encoding UTF-8 LoggableProcessorByProcessor.java
javac -encoding UTF-8 Loggable.java
javac -encoding UTF-8 -processor LoggableProcessorByProcessor CustomAnnotationServiceImpl.java
执行结果：
C:\Users\Administrator\Desktop\p>javac -encoding UTF-8 -processor LoggableProcessorByProcessor CustomAnnotationServiceImpl.java
Found @Loggable on method: login
Found @Loggable on method: logout
注: element name: login
注: element name: logout

证明：
自定义注解生效。
