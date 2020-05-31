### 安装
启动需要JAVA_HOME, windows 启动 win64 文件夹中的.bat文件
http://localhost:8161/admin/   admin/admin

### 普通使用
ConnectFactory
Session
Producer
Consumer
Message

recevie的时候要开启connection

### spring 使用
#### template
jmsTemplate
convertAndSend
receiveAndConvert
MessageConvert

#### message-driven
Message-Driven POJO / mdp
MappingJackson2MessageConverter 自带convert

 @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }
    
    与
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
                                                                    Destination destination,
                                                                    SpringReceiver receiver){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestination(destination);
        container.setMessageListener(receiver);
        return container;
    }
    
    作用类似, 前者更简单. 主要思路就是把Listener 放到监听容器中, 实现异步消息驱动
   前者无需实现 MessageListener 接口, 但是需要注解
   
   参考:
   https://spring.io/guides/gs/messaging-jms/
   https://www.baeldung.com/spring-jms
   
   Demo:  customSpringBootStarterDemo com.beyond.demo.playground.spring.jms
    
