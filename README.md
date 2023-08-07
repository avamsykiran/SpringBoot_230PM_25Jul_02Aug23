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

        FrontController?

                    DispatcherServlet serve as the Front Controller.

                    Spring Web MVC on Spring Boot will auto config the DispatcherServlet to receive
                    any req sent to the context root path (/*.*)

                    DispatcherServlet depending on the url-path of the request will pass the request to
                    one of the controllers along with the data extracted from the request.

                    Once a controller completes the request processing, it is expected to return 
                    a ModelAndView object or a viewName as a string to the DispatcherServlet (FC).

                    DispatcherServlet (FC) will pass the model if any to the view identified by
                    the givne viewName, and that view is rendered as htmla nd sent as a response to the client.

        Controller?

                    is any POJO thats marked with @Controller

                    Each controller calss is also marked with @ReqeustMapping("url")

                    Infact, the DispatcherServlet will take the help of SimpleUrlHandletResolver class
                    (which is implementing UrlHandlerResolver interface) to map a controller with its
                    URL. And the SimpleUrlHandlerResolver will scan for @RequestMApping for the same.

                    Each controller can have method to handle request and such methods are called actions.
                    These actions must return a String for viewName or an object of ModelAndView that
                    contain a viewName and models.

                    @Controller
                    @RequestMapping("/emps")
                    public class EmployeeController {

                        @Autowired
                        private EmployeeService empService;

                        @RequestMapping(value="/list",method=RequestMethod.GET)
                        public ModelAndView getAllEmployees(){
                            List<Employee> emps = empService.getAll();
                            return new ModelAndView("emps-list-page","empData",emps);
                        }
                    }

                    As and when the DispatcherServlet gets a request of URL http://localhost:8888/emps/list,
                    the above method gets executed and the modelAndView is received by the DispatcherServlet.

        ViewResolver?

                    ViewResolver interface helps the DispatcherServlet to pick up a view for a given viewName.
                        |
                        |<- MessageBundleResourceViewResolver
                        |<- XmlResoureceViewResolver
                        |<- InternalResourceViewResolver

                    Spring Boot auto configs InternalResourceViewResolver for ThymeLeaf view engine.
                    The below are two properties of InternalResourceViewResolver
                            prefix
                            suffix

                    View = prefix + viewName + suffix

                    if ".jsp" is the suffix and "/WEB-INF/pages" is the prefix then for a viewName "emps-list-page"
                    the view will be "/WEB-INF/pages/emps-list-page.jsp"

    Spring Data JPA
        
        Spring Data moduel spanning into sub modules like Spring Data JPa, Spring Data NoSQL ...etc., are modules
        that offer auto implemented repositories.

        Particularly Spring Data JPA offers auot implemnted JPA based repositories.

        CrudRepository
            | <- JpaRepository
                        List<E> findAll()
                        E findById(PK idValue)
                        E save(E obj);
                        boolean existsById(PK idVlaue);
                        void deleteById(PK idValue);

        @Entity
        @Table(name="emps")
        public class Employee {

            @Id
            private Long empId;
            @Column(name="ename")
            private String name;
            private LcoalDate joinDate;
            private Double salary;
            private String email;

            //construcotrs, getter/setter, ......

        }

        public interface EmployeeRepo extends JpaRepository<Employee,Long> {
            List<Employee> findAllByName(String name);
            Employee findByEmail(String email);
            boolean existsByEmail(String email);

            @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :limit1 AND :limit2")
            List<Employee> getAllEmployeesOfSalaryInRange(double limit1,double limit2);

        }

    Integrating Spring Boot With Thymeleaf

        Thymeleaf is another view engine. Thymeleaf are versatile meaning, we cna use thymelaf on Xml/Html/even plain text documents
        to wrpa the dynamcially generated content over a mdoel of data. Thymeleaf is very light weight and faster in
        rendering compaed to native view engines like JSP/JSF.

    
    Spring Rest API

        REST API is a webservice based on HTTP Protocol, hence the name REpresentational State Transfer.

        Web Service ?

            is a method pubblished on a centralized server that get invoked through a request and returns via a response.

                                                        | <----- a Standalone GUI based APP     <------>  End User
            Database <---> WEB-SERVICE <------------->  | <----- a Web-APP                      <------>  End User
                                                        | <----- a Mobile APP                   <------>  End User

            SOAP Web Service

                + the web services were invokable via SOAP protocol. (Simple Object Access Protocol)
                + xml was used the media of communication for passing aprameters to the web-service and also
                  for the web service to return back data.


                - XML is very limited in media support, for example binary data like images/docuemtns
                    can not transported via XML
                - We do not have a standered concern addressing system like a systme where the compeltion of the request or
                    the abortion of a request and teh relvent error can be reported to the client.

            REST Web Services / REST API

                + This is based on HTTP Protocol.
                + As the http protocol support all sorts of media, It is possible to transport any sort of data
                  like binary/text/numeric/object ...et.
                + HTTP Methods provide us a standrd way of operations using a single end-point.
                + HTTP Protocol offers a re-established wy of communicating the request and resposne status through which
                    the successful completion of operation / errorsome abortion of operation can be passed to the client


                REST API STANDARDS

                Assuming an entity Book, if we have to create a REST API to perform CRUD operations, the below
                standards are expected:

                end-point is :  /books

                                                                                                                            Error Status 
                Operation           Http Method     URL-Pattern             Response            Sucess Status     Client Side           Server Side      
                -----------------------------------------------------------------------------------------------------------------------------------------------
                Retrive All         GET             /books                  JSON/XML            OK-200             400 - BadRequest    500 - Internal 
                                                                                                                                                Server Error

                Retrive By Id       GET             /books/{bookId}         JSON/XML            OK-200             404 - NotFound      500 - Internal 
                                                                                                                                                Server Error

                Add Record          POST            /books                  JSON/XML            CREATED-201        400 - BadReqeust    500 - Internal 
                                                                                                                                                Server Error

                Update              PUT             /books/{bookId}         JSON/XML            ACCEPTED-203       400 - BadReqeust    500 - Internal 
                                                                                                                                                Server Error

                Delete By Id        DELETE          /books/{bookId}                             NOContent-204      404 - NotFound      500 - Internal 
                                                                                                                                                Server Error

            To test our rest api, we can use rest api cleitns like POSTMAN / INSOMNIA ...etc.,

    Spring Profiles

        Profile?
            an isolated set of configuaratiosna nd choice of components for executing our application in specific
            stage or environment.

            dev,testing,deployment/production ...etc.,

        @Profile("")
            is apllied on a component or a configuaration class.

        application.properties
            spring.profiles.active=dev

        application-dev.properties
        application-test.proeprties
        application-prod.properties

        application.properties
            spring.profiles.active=dev

            spring.jpa.show-sql = false
            spring.jpa.hibernate.ddl-auto = update

            #---
            spring.config.activate.on-profile=dev
            server.port = 8888
            spring.datasource.url = jdbc:h2:mem:db
            spring.datasource.driver-class-name=org.h2.driver
            spring.datasource.username = sa
            spring.datasource.password = sa           
            
            #---
            spring.config.activate.on-profile=prod
            server.port = 9999
            spring.datasource.url = jdbc:mysql://localhost:3306/hrappdb
            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
            spring.datasource.username = root
            spring.datasource.password = root           
            spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

    ECommerce CaseStudy - CRUD operation via REST api on the entity:
        Item
            itemId          Long
            name            String
            price           Double
            packageDate     LaocalDate

