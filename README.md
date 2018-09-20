### 进入[Spring脚手架](https://start.spring.io/) 创建Web工程

![SpringInitialize.png](https://upload-images.jianshu.io/upload_images/12034021-0c96e27b5d082864.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**还有其他功能可以选择性勾选**

- 点击Generate Project后得到一个压缩包，解压，然后导入解压后的工程

- 添加Maven依赖，配置pom.xml
```
        <!-- 热部署 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
            <scope>true</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

- 配置application.properties
```
#数据库连接配置
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=******
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

#mybatis.typeAliasesPackage：为实体对象所在的包，跟数据库表一一对应
#mybatis.mapperLocations：mapper文件的位置
mybatis.mapperLocations=classpath:mapper/*.xml
mybatis.typeAliasesPackage=com.example.demo.model

#配置执行SQL语句打印
logging.level.com.example.demo.dao=DEBUG
```

- SpringBoot Application
```
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

- Controller
```
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello Spring";
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void insertUser(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        userService.insertUser(user);
    }
}
```
- Service
```
package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    public void insertUser(User user);

}
```
- Service Impl
```
package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(User user) {
        userDao.addUserInfo(user);
    }
}

```

- Dao

```
package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(User user) {
        userDao.addUserInfo(user);
    }
}

```

- Model
```
package com.example.demo.model;

public class User {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

```

- Mapper
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.dao.UserDao">
    <insert id="addUserInfo" parameterType="com.example.demo.model.User">

		insert into user (name, sex) values (#{name,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR})

	</insert>
</mapper>
```

- 最后目录如下

![目录.png](https://upload-images.jianshu.io/upload_images/12034021-7101500a591be0b5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 至此，完成了一个具有Restful接口、集成Mybatis的Spring Boot Web工程

