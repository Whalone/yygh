# yygh
预约挂号 微服务



## 后端

### 项目后端搭建

#### 1. 父工程 yygh_parent

pom 管理依赖版本和公共依赖 

springboot工程 2.2.1

Group com.whalone.yygh

Artifact yygh_parent

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <modules>
        <module>common</module>
        <module>model</module>
        <module>service</module>
        <module>server-gateway</module>
        <module>service-client</module>
        <module>hospital-manage</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.1.RELEASE</version>
    </parent>

    <groupId>com.whalone</groupId>
    <artifactId>yygh_parent</artifactId>
    <packaging>pom</packaging>
    <version>1.0</version>
    <name>yygh_parent</name>
    <description>预约挂号父工程</description>

    <properties>
        <java.version>1.8</java.version>
        <cloud.version>Hoxton.RELEASE</cloud.version>
        <alibaba.version>2.2.0.RELEASE</alibaba.version>
        <mybatis-plus.version>3.3.1</mybatis-plus.version>
        <mysql.version>5.1.46</mysql.version>
        <swagger.version>2.7.0</swagger.version>
        <jwt.version>0.7.0</jwt.version>
        <fastjson.version>1.2.29</fastjson.version>
        <httpclient.version>4.5.1</httpclient.version>
        <easyexcel.version>2.2.0-beta2</easyexcel.version>
        <aliyun.version>4.1.1</aliyun.version>
        <oss.version>3.9.1</oss.version>
        <jodatime.version>2.10.1</jodatime.version>
    </properties>


    <!--配置dependencyManagement锁定依赖的版本-->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--mybatis-plus 持久层-->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus.version}</version>
            </dependency>

            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>

            <!--swagger-->
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger2</artifactId>
                <version>${swagger.version}</version>
            </dependency>
            <!--swagger ui-->
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger-ui</artifactId>
                <version>${swagger.version}</version>
            </dependency>

            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>${jwt.version}</version>
            </dependency>

            <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpclient</artifactId>
                <version>${httpclient.version}</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson.version}</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>easyexcel</artifactId>
                <version>${easyexcel.version}</version>
            </dependency>

            <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>aliyun-java-sdk-core</artifactId>
                <version>${aliyun.version}</version>
            </dependency>

            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>${oss.version}</version>
            </dependency>

            <!--日期时间工具-->
            <dependency>
                <groupId>joda-time</groupId>
                <artifactId>joda-time</artifactId>
                <version>${jodatime.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

</project>

```

ps: 如果提示Dependency not found，先去掉dependencyManagement标签，让pom进行下载，再加回来就ok了

#### 2. 在父项目yygh_parent 中新建module common

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>yygh-parent</artifactId>
        <groupId>com.whalone.yygh</groupId>
        <version>1.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <modules>
        <module>common_util</module>
        <module>service_util</module>
    </modules>
    <artifactId>common</artifactId>


</project>
```

#### 3. 在common module中新建module common_util

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>common</artifactId>
        <groupId>com.whalone.yygh</groupId>
        <version>1.0</version>
    </parent>


    <artifactId>common_util</artifactId>
    <version>1.0</version>
    <name>common-util</name>
    <packaging>pom</packaging>
    <description>common-util</description>

    <dependencies>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
        </dependency>

        <!-- 日期工具栏依赖 -->
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 4. 在common module中新建module service_util

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>common</artifactId>
        <groupId>com.whalone.yygh</groupId>
        <version>1.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <artifactId>service_util</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.whalone.yygh</groupId>
            <artifactId>common_util</artifactId>
            <version>1.0</version>
        </dependency>
        <!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!-- spring2.X集成redis所需common-pool2-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.6.0</version>
        </dependency>
    </dependencies>

</project>
```

#### 5. 在父项目yygh_parent 中新建module model

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>yygh-parent</artifactId>
        <groupId>com.whalone.yygh</groupId>
        <version>1.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <artifactId>model</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <!--mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <scope>provided </scope>
        </dependency>
        <!--swagger-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <scope>provided </scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <scope>provided </scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
            <scope>provided </scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <scope>provided </scope>
        </dependency>
    </dependencies>


</project>
```

#### 6. 在父项目yygh_parent 中新建module service

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>yygh-parent</artifactId>
        <groupId>com.whalone.yygh</groupId>
        <version>1.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <artifactId>service</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.whalone.yygh</groupId>
            <artifactId>service_util</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.whalone.yygh</groupId>
            <artifactId>model</artifactId>
            <version>1.0</version>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--开发者工具-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- 服务调用feign -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!-- 服务注册 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!-- 流量控制 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes> <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>


</project>
```



### 接口开发

#### 医院设置需求

##### 需求

医院设置主要是用来保存开通医院的一些基本信息，每个医院一条信息，保存了医院编号（平台分配，全局唯一）和接口调用相关的签名key等信息，是整个流程的第一步，只有开通了医院设置信息，才可以上传医院相关信息。

我们所开发的功能就是基于单表的一个CRUD、锁定/解锁和发送签名信息这些基本功能。



##### 表结构

hosname：医院名称

hoscode：医院编号（平台分配，全局唯一，api接口必填信息）

api_url：医院回调的基础url（如：预约下单，我们要调用该地址去医院下单）

sign_key：双方api接口调用的签名key，有平台生成

contacts_name：医院联系人姓名

contacts_phone：医院联系人手机

status：状态（锁定/解锁）







## 杂

### MyBatis-Plus



#### mp是什么

MyBatis的增强工具。只做增强不做改变，为简化开发，提高效率而生。



#### mp实现添加 修改 删除 查询

##### **环境准备与测试**

1. 创建表

   ```sql
   CREATE TABLE USER (
       id BIGINT(20) NOT NULL COMMENT '主键ID',
       NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
       age INT(11) NULL DEFAULT NULL COMMENT '年龄',
       email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
       PRIMARY KEY (id)
   )
   ```

2. 创建springboot工程

3. pom文件修改springboot版本为2.2.1版本

   ```xml
   <properties>
   	<java.version>1.8</java.version>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
       <spring-boot.version>2.2.1.RELEASE</spring-boot.version>
   </properties>
   ```

4. 添加mp相关依赖

   ```xml
   <!--mybatis-plus-->
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-boot-starter</artifactId>
       <version>3.3.1</version>
   </dependency>
   <!--mysql依赖-->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.18</version>
   </dependency>
   <!--lombok用来简化实体类-->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <optional>true</optional>
   </dependency>
   ```

5. 数据库配置

   在application.properties中进行配置

   ```properties
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8
   spring.datasource.username=root
   spring.datasource.password=xxxx
   ```

6. 创建实体类

   ```java
   @Data
   public class User {
       private Long id;
       private String name;
       private Integer age;
       private String email;
   }
   ```

7. 创建包mapper编写Mapper接口：UserMapper.java

   ```java
   @Repository
   public interface UserMapper extends BaseMapper<User> {
       
   }
   ```

   继承的BaseMapper里面提供了各种封装好的sql方法

   在启动类加上@MapperScan("com.test.demomptest.mapper")注解 路径就是你mapper的路径

   在测试类中调UserMapper中的方法

   ```java
   @SpringBootTest
   class DemomptestApplicationTests {
   
       // 这里报错的话就是UserMapper没有加@Repository注解
       @Autowired
       private UserMapper userMapper;
   
       @Test
       public void findAll() {
           List<User> users = userMapper.selectList(null);
           System.out.println(users);
       }
   
   }
   ```

   run

   查看sql输出日志

   ```properties
   mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
   ```

   

##### **添加**

```java
@Test
public void testInsert(){
    User user = new User();
    user.setAge(15);
    user.setEmail("123@test.com");
    user.setName("test");
    int result = userMapper.insert(user);
    System.out.println(result);

}
```

通过@TableId(type = IdType.AUTO)来设置id生成策略

IdType.AUTO 自增

IdType.ASSIGN_ID 雪花算法 生成随机19位id

IdType.ASSIGN_UUID 用UUID

```java
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```



##### **更新**

```java
@Test
public void testUpdate(){
    User user = new User();
    user.setId(1375072750809374722L);
    user.setName("test123");
    int count = userMapper.updateById(user);
    System.out.println(count);
}
```



##### **批量查询**

```java
@Test
public void testSelectByBatchId(){
    // batch id的参数是collection 一般传list
    List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
    System.out.println(users);
}
```



##### **简单条件查询**

```java
// 简单条件查询
@Test
public void testSelectByBatchId2(){
    Map<String, Object> columnMap = new HashMap<>();
    columnMap.put("name","jack");
    List<User> users = userMapper.selectByMap(columnMap);
    System.out.println(users);
}
```



##### **简单删除**

```java
// 简单删除
@Test
public void testDeleteById(){
    int rows = userMapper.deleteById(1L);
    System.out.println(rows);
}
```



##### **批量删除**

```java
// 批量删除
@Test
public void testDeleteByBatchId(){
    int rows = userMapper.deleteBatchIds(Arrays.asList(2,3));
    System.out.println(rows);
}
```



**简单条件删除**

```java
// 简单条件删除
@Test
public void testDelectByMap(){
    Map<String,Object> map = new HashMap<>();
    map.put("name","tom");
    map.put("id","2");
    int rows = userMapper.deleteByMap(map);
    System.out.println(rows);
}
```



#### mp自动填充

##### **自动填充**

1. 准备工作

   在表中添加两个字段 create_time update_time

   在表对应的实体类中添加对应的属性

   ```java
   private Date createTime;
   private Date updateTime;
   ```

2. 在实体类要进行自动填充的属性添加注解

   ```java
   // INSERT操作的时候自动填充
   @TableField(fill = FieldFill.INSERT)
   private Date createTime;
   // UPDATE操作的时候自动填充
   @TableField(fill = FieldFill.INSERT_UPDATE)
   private Date updateTime;
   ```

3. 创建类实现接口，实现接口的两个方法，一个方法添加执行，一个方法修改执行，设置添加什么值

   ```java
   @Component
   public class MyMetaObjectHandler implements MetaObjectHandler {
       @Override
       public void insertFill(MetaObject metaObject) {
           this.setFieldValByName("createTime",new Date(),metaObject);
           this.setFieldValByName("updateTime",new Date(),metaObject);
       }
   
       @Override
       public void updateFill(MetaObject metaObject) {
           this.setFieldValByName("updateTime",new Date(),metaObject);
       }
   }
   ```

   

#### **乐观锁**

##### **乐观锁是什么**

防止丢失更新



##### **实现思路**

添加字段 如版本号 每次更新数据时比较版本号 若版本号不同则不允许更新



##### **乐观锁实现**

在表添加字段作为版本号，在表对应实体类添加版本号属性

在实体类进行版本号操作属性上面添加注解

```java
@Version
private Integer version;
```

配置乐观锁插件

```java
package com.test.demomptest.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.test.demomptest.mapper")
public class MyConfig {

    // 乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
```

测试

```java
@Test
public void testOptimisticLocker(){
    User user = userMapper.selectById(1375091837753032705L);
    user.setName("test121212");
    userMapper.updateById(user);
}
```

#### mp逻辑删除

##### **逻辑删除**

在表添加字段，作为逻辑删除的标志，每次删除时，修改标志位

0 没有删除

1 删除

 在实体类中添加上注解

```java
@TableLogic
@TableField(fill = FieldFill.INSERT)
private Integer deleted;
```

执行查询操作的时候，会自动将已经进行逻辑删除的数据进行屏蔽



#### **mp分页查询**

##### **分页查询**

需要先配置分页查询插件 在MyConfig.java

```java
// 分页查询插件
@Bean
public PaginationInterceptor paginationInterceptor(){
    return new PaginationInterceptor();
}
```

```java
// 分页查询
@Test
public void testSelectByPage(){
    Page<User> page = new Page<>(1,3);
    Page<User> userPage = userMapper.selectPage(page,null);
    // 返回对象得到分页所有数据
    long Pages = userPage.getPages(); // 总页数
    long current = userPage.getCurrent(); // 当前页
    List<User> records = userPage.getRecords(); // 查询数据集合
    long total = userPage.getTotal(); // 总记录数
    boolean hasNext = userPage.hasNext(); // 当前页是否有下一页
    boolean hasPrevious = userPage.hasPrevious(); // 当前页是否有上一页

    System.out.println(Pages);
    System.out.println(current);
    System.out.println(records);
    System.out.println(total);
    System.out.println(hasNext);
    System.out.println(hasPrevious);
}
```



#### 条件构造器和常用接口

##### 复杂查询

```java
// 复杂查询
@Test
public void testSelectByWrapper(){

    // ge 大于等于
    // gt 大于
    // le 小于等于
    // lt 小于
    QueryWrapper<User> wrapper1 = new QueryWrapper<>();
    wrapper1.ge("age",21);
    List<User> users1 = userMapper.selectList(wrapper1);
    System.out.println(users1);

    // eq ne
    QueryWrapper<User> wrapper2 = new QueryWrapper<>();
    wrapper2.eq("name","tom");
    List<User> users2 = userMapper.selectList(wrapper2);
    System.out.println(users2);

    // between notBetween
    QueryWrapper<User> wrapper3= new QueryWrapper<>();
    wrapper3.between("age",21,22);
    List<User> users3 = userMapper.selectList(wrapper3);
    System.out.println(users3);

    // like notLike likeLeft likeRight
    // left和right代表%在左边还是右边
    QueryWrapper<User> wrapper4= new QueryWrapper<>();
    wrapper4.like("name","tom");
    List<User> users4 = userMapper.selectList(wrapper4);
    System.out.println(users4);

    // orderBy orderByDesc orderByAsc
    wrapper4.orderByDesc("id");

}
```

