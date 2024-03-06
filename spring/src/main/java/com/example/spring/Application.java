package com.example.spring;

import com.example.spring.aop.OrderService;
import com.example.spring.boot.autoconfig.Config1;
import com.example.spring.event.listener.CustomEvent;
import com.example.spring.event.listener.HelloAsync;
import com.example.spring.ioc.importtest.BeanImporter;
import com.example.spring.ioc.importtest.EnableImporter;
import com.example.spring.ioc.properties.TcRpcProperties;
import org.aspectj.lang.annotation.Pointcut;
import org.javaboy.boot.autoconfig.Config1AutoConfiguration;
import org.javaboy.boot.autoconfig.HelloService;
import org.javaboy.boot.monitor.DruidDataSourceMonitor;
import org.javaboy.boot.runlistener.RunListener;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
/**
 * 注入了一个后置处理器，用来增强代理。
 */
@EnableAsync
@EnableImporter(packages = {"com.example.spring"})
@EnableConfigurationProperties({TcRpcProperties.class})
public class Application {

    @Autowired
    TcRpcProperties tcRpcProperties;


/*    @Autowired
    Config1AutoConfiguration config1AutoConfiguration;*/

    @Autowired
    HelloService helloService;

    @Autowired
    OrderService orderService;


    @PostConstruct
    public void execute() {
//        System.out.println(tcRpcProperties);
//        testAsync();
//        aopTest();

        MDC.put("name","zs");
    }


    @Autowired
    HelloAsync async;

    void testAsync() {
        async.test();
        ;
    }

    void aopTest() {
        orderService.create();
        System.out.println("-------------分------------");
        orderService.payed();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        ConfigurableEnvironment environment = context.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();
        System.out.println(propertySources);


    }

    /**
     * 当无法走到自动装配时,
     * 调用druidDataSourceMonitorClass.getGenericSuperclass()会去加载DruidDataSource类
     * 此类是不存在的
     */
    static void testType() {
        Class<DruidDataSourceMonitor> druidDataSourceMonitorClass = DruidDataSourceMonitor.class;
        System.out.println(druidDataSourceMonitorClass);
        ParameterizedType genericSuperclass = (ParameterizedType) (druidDataSourceMonitorClass.getGenericSuperclass());
        Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
        System.out.println(actualTypeArgument);
    }

    static void testEvent(ApplicationContext context) {
        context.publishEvent(new CustomEvent(context));
        System.out.println("-----devtools---");
        // RestartClassLoader
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(context.getClassLoader());
        System.out.println(Object.class.getClassLoader());
    }

    static void testEnvironment(ApplicationContext context) {
        /**
         *
         * ApplicationServletEnvironment
         */
        Environment environment = context.getEnvironment();
        System.out.println("env:" + environment);

        String property = environment.getProperty("spring.profiles.active");
        System.out.println(property);
    }

//    @Bean
//    public SimpleApplicationEventMulticaster applicationEventMulticaster() {
//        配置异步触发监听器
//        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
//        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>(1000);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, blockingQueue);
//        simpleApplicationEventMulticaster.setTaskExecutor(threadPoolExecutor);
//        return simpleApplicationEventMulticaster;
//    }
}
