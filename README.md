# library-management-system

这是一个探索使用ddd数据对象传递的项目。  
在这里会详细描述ddd的分层目录。

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
<img alt="img.png" height="200" src="img/img.png" width="350"/>  
这是spring security的basic login，默认用户 : user
密码 : ![img_1.png](img/img_1.png)
登录之后就可以看到swagger页面了。
![img_2.png](img/img_2.png)

### 集成jpa