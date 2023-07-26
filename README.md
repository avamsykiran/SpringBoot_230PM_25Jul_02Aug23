Spring Boot 2.x as packed with Spring 5.x
----------------------------------------------------------------------------------------------------------

    Pre-Requisites
        JDK 1.8
        JUnit 5
        Maven
        Spring Core (Spring Beans, Spring Context, IoC, Spring Expression Language)
        JPA & Hibernate (ORM)

    Lab
        JDK 1.8
        STS latet (Spring Tool Suite) as IDE
        MySQL Community Server 8 or later

    Dependency Injection

        Dependency? --> Functional Dependency??

            is where a method of a class needs to invoke a method of another class
            to complete the task.

            DAO   <------------- Service

                EmployeeDAO
                EmployeeDAOJdbcImpl
                EmployeeDAOJpaImpl

                EmployeeService
            /*    public class EmployeeServiceImpl {

                    private EmployeeDAO empDAO;

                    pubnlic EmployeeServiceImpl(){
                        //this.empDAO = new EmployeeDAOJdbcImpl();
                        this.empDAO = new EmployeeDAOJpaImpl();
                    }
                }*/

                public class EmployeeServiceImpl {

                    private EmployeeDAO empDAO;

                    public EmployeeServiceImpl(){

                    }

                    public EmployeeServiceImpl(EmployeeDAO empDAO) {
                        this.empDAO = empDAO;
                    }

                    public void setEmpDAO(EmployeeDAO empDAO) {
                        this.empDAO = empDAO;
                    }
                }


        If the construcotr injection or setter inejction is doen automatically by any engine, then
        we call it IoC (Inversion of Control);

        Context/Container       is the engine that does the IoC (does the dependency injection)
                                1. can create an object of a class
                                2. it can supply the object of the class when demanded
                                3. it can inject all needed dependeices of that object.
                                4. it can destroy the object once it's no more in use.

        Component               the class to which the contaienr creates and manages the objects is called a Component.
        
        Bean                    the object of components managed by contaienr are called beans.

        Contaienrs Offerd By Spring are
            BeanFactory from Spring Beans Module
            ApplicationContext from Spring Context Module

        ApplicationContext is a little enchanced one than the BeanFactory.

        Spring Bean Configuaration
            1. Through XML
            2. Through Annotations
            3. Through Java Code

        Java Based Configuaration

            @Configuration
            public class MyBeansConfig {

                @Bean
                public Scanner scan(){
                    return new Scanner(System.in);
                }

                @Bean
                @Scope("prototype")
                public LocalDateTime timeNow(){
                    return LocalDateTime.now();
                }
            }
    
            Scanner scan = (Scanner) context.getBean("scan");
            LocalDateTime dt1 = (LocalDateTime) context.getBean("timeNow");
            LocalDateTime dt2 = (LocalDateTime) context.getBean("timeNow");
            LocalDateTime dt3 = (LocalDateTime) context.getBean("timeNow");

        Annotation Based Cofniguaration

        
            @Configuration
            @ComponentScan(basePackage="com.cts.hrapp")
            public class MyBeansConfig {

            }

            package com.cts.hrapp.util;
            @Component
            public class Convertor{

            }

            @Component
                @Repository
                @Service
                @Controller
                @RestController
                @Advice ....et.,

            @Scope("singleton|prototype|reqeust|session|global-session")

            injecting primitive fields

                classpath/data.properties
                    banking.min.bal=4500


                @Configuration
                @ComponentScan(basePackage="com.cts.hrapp")
                @PropertySource("classpath:data.properties")
                public class MyBeansConfig {

                }

                @Service
                public class BankingService {
                    
                    @Value("${banking.min.bal}")
                    private double min_bal;

                    //......
                } 


            injecting other beans

                @Repository
                public class EmployeeDAOImpl implements EmployeeDAO {

                }

                @Service
                public class EmployeeServiceImpl implements EmployteeService {
                    
                    @Autowired
                    private EmployeeDAO empDAO;     //field injection

                    //......
                } 

                @Service
                public class EmployeeServiceImpl implements EmployteeService {
                    
                    private EmployeeDAO empDAO;     

                    @Autowired
                    public EmployeeServiceImpl(EmployeeDAO empDAO){     //constructor injection
                        this.empDAO = empDAO;
                    }
                } 

                @Service
                public class EmployeeServiceImpl implements EmployteeService {
                    
                    private EmployeeDAO empDAO;     

                    public EmployeeServiceImpl(){

                    }
                    
                    @Autowired
                    public void setEmpDAO(EmployeeDAO empDAO){     //setter injection
                        this.empDAO = empDAO;
                    }
                } 


    Spring Boot

        is a spring framework module that offer RAD through auto-configuration.

        RAD - Rapid Application Development.

        spring boot chooses standard project strucutre over customization

            @SpringBootApplication  =   @Configuration
                                        @ComponentScan  //the package in which the sping application exists is base package
                                        @PropertySource //application.proeprties from classpath
                                        @AutoConfig

        spring boot also depends on specail spring modules likes starter-modules
        Where each starter module is a min config + the actual module.

        Spring Boot offers Embed Server.

        As as result we end up developing server-less application.

        Spring Boot Application can be created in three ways:

        1. STS spring starter project wizard.
        2. start.spring.io
        3. spring boot cli


        SpringApplication.run(SpringIocBootDemoApplication.class, args);

                1. Create an appropriate ApplicationContext
                2. Search and Execute any Spring Runners
                3. Identify and Start any embeded server
                4. Once the server gets stopped
                5. The ApplicationContext is closed and the application terminates.

        Spring Runners
            CommandLineRunner       public void run(String args[])
            ApplicationRunner       public void run(ApplicationArgs args)

    Spring Web MVC on Spring Boot

        M - Model
        V - View
        C - Controller

        MVC Archetecture

        Database <-SQL--> Rep(S)  <--model--> Service(S) <--model-->  Controller(S)    <----REQ---- WebClient
                                                                        |                               ↑
                                                                        |}model                         |
                                                                        |                               |
                                                                        |                               |
                                                                        ↓                               |
                                                                      View      -----(html) Resp -----> |


        Single Front Controller MVC Archetecture  Offered By Spring Web Module  

        Database <-SQL-> Rep(S)  <-model-> Service(S) <-model->  Controller(S) <--> FrontController   <----REQ---- WebClient
                                                                                        |                               ↑
                                                                                        |}model                         |
                                                                                        |                               |
                                                                                        |                               |
                                                                                        ↓                               |
                                                                                      View      -----(html) Resp -----> |
                                                                                