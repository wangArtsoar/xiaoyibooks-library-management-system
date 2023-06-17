# library-management-system

这是一个图书管理系统。  
也是一个探索使用ddd数据对象传递的项目。  
在这里会详细描述ddd的分层目录。  
也会详细的描述系统的流程 : 包括创建、集成、业务等流程。  
以及可能出现的错误，并给出解决方案。

## 创建流程

在 https://start.spring.io/ 中填写信息  
例如：项目名称、版本号、需要的依赖
![img.png](img/img.png)
然后点击 **GENERATE** _下载_ _解压_   
之后 _右键_ 选择 _Open Folder as IntelliJ IDEA Project_  
可能有些人在初始化gradle时会遇到错误 :

```html
Could not initialize class
org.gradle.internal.classloader.FilteringClassLoader
```

查了网上的信息说是 :

```html
这个错误信息表明在初始化
org.gradle.internal.classloader.FilteringClassLoader类时出现了问题。
这个问题可能是由于Gradle的运行环境（例如JDK版本）不兼容或者Gradle版本冲突造成的。
您可以尝试检查您的Gradle和JDK版本是否兼容，或者尝试更新Gradle版本来解决这个问题。
```

事实上是因为相关idea的路径上有中文。修改之后就好了。

## 技术分析

使用的技术包括：  
spring boot 3、 spring security 6、 spring data jpa、Gradle、  
spring data redis、 lombok、springdoc、数据库（PostgreSQL）

## 集成流程

### 集成springdoc

1. 创建一个接口
   创建一个interfaces包，在包中创建一个DemoCtrl类。

```java

@RestController
@RequestMapping("api/v1")
public class DemoCtrl {

	@GetMapping("say")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("hello");
	}
}
```

将springdoc依赖添加到[build.gradle](build.gradle)中。

```groovy
implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.1.0'
```

2. 添加数据库

可以在idea中添加数据源
![img_1.png](img/img_1.png)
在这里选择需要的数据库
![img_2.png](img/img_2.png)
填写用户名和密码，PostgreSQL的默认用户名为**postgres**  
下面的选择的url就是填写在application.yml中的url  
在application.yml中添加

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/demofromddd
    username: postgres
    password: xiaoyi_wyx
    driver-class-name: org.postgresql.Driver
```

然后就可以启动服务器了，在浏览器中打开
http://localhost:8080/swagger-ui/index,html
![img.png](img/img_3.png)  
这是spring security的basic login，默认用户 : user  
密码 : ![img_1.png](img/img_4.png)
登录之后就可以看到swagger页面了。
![img_2.png](img/img_5.png)

### 集成jpa

postgreSQL的jpa配置

```yaml
  # 配置jpa
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

MySQL的jpa配置  
需要根据MySQL的版本进行配置，MySQL57或者MySQL80

```yaml
  # 配置jpa
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: mysql
    database-platform: org.hibernate.dialect.MySQL80Dialect
```

### Jwt

需要添加依赖

```groovy
    implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.5'
runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.5'
runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'
```

编写JwtAuth(Jwt过滤器)和JwtUtils(Jwt的生成与校验)

### MapStruct

添加依赖

```groovy
    implementation group: 'org.mapstruct', name: 'mapstruct', version: '1.5.5.Final'
annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'
```

使用方法

```java

@Mapper(uses = {UserMapper.class, BookMapper.class})
public interface BorrowMapper {
	@Mappings({
					@Mapping(source = "user.name", target = "name"),
					@Mapping(source = "user.email", target = "email"),
					@Mapping(source = "book.assortName", target = "assortName"),
					@Mapping(source = "book.name", target = "bookName")
	})
	BorrowViewModel borrowToBorrowViewModel(Borrow borrow);

	@Mapping(source = "userPo", target = "user")
	@Mapping(source = "bookPo", target = "book")
	Borrow borrowPoToBorrow(BorrowPo borrowPo);

	List<Borrow> borrowPoToBorrowList(List<BorrowPo> borrowPos);

	List<BorrowSimpleVo> borrowViewModelToSimpleList(List<BorrowViewModel> borrows);
}
```

## DDD目录

DDD分为四层

1. infrastructure(基础层)
2. domain(领域层)
3. interfaces(接口层)
4. application(应用层)

## 完善配置

## 业务分析

### 登录&注册

### 退出登录

### 初始化角色

### 添加图书

### 图书的借阅以及查询分页

### 模糊查询

## 说明