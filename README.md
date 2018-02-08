# 微信网页开发服务端示例

本示例是一个基于`spring-boot`开发的项目，集成了`Swagger2`，`QueryDsl`，`MyBatis`以及`spring-boot-web`中自带的`Hibernate`。启动项目后，在http://localhost:8080/spring-boot-wechat/swagger-ui.html中查看接口。

## 注意事项

### 数据库

1. 创建数据库的`test.sql`文件在`/src/main/resources/static`下，请执行`test.sql`文件创建数据库后查看`/src/main/resources/application.properties`中数据源地址是否需要修改后再进行测试。创建的数据库表结构请参考`test.sql`文件。

### 资源文件

1. 启动项目前需要生成`QueryDsl`相应的类文件，所以在启动项目前先执行`Maven`命令：`mvn generate-sources`
2. `MyBatis`的`*Mapper.xml`文件存放在`/spring-boot-wechat-demo/src/main/resources/com/example/demo/mapper`目录下；`*Mapper.java`文件存放在`/spring-boot-wechat-demo/src/main/java/me/frank/spring/boot/wechat/mapper`目录下
3. 名称带有`dev`的`*.properties`文件表示测试环境下的配置文件；带有`prod`的`*.properties`文件表示生产环境下的配置文件

### 配置

#### 系统配置

1. 系统相关的参数配置在`application.properties`文件中，相应的配置都已有注释描述
2. 修改项目名的时候注意修改`server.context-path`参数
3. 修改包名的时候注意修改`mybatis.type-aliases-package`, `mybatis.mapper-locations`参数
4. 修改数据库源的时候注意修改`spring.datasource`, `hikari.datasource`相应的参数
5. `spring.datasource`, `hikari.datasource`的部分参数需要保持一致，具体参考现有的配置

#### 微信配置

**注：测试环境下相应的配置可打开[微信公众平台测试号网站](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)（以下简称网站），用微信扫一扫登陆，获取和设置相应的配置**

1. 微信相关的配置存放在`app-wechat-*.properties`文件中，注意测试环境与生产环境的区分
2. `wechat.app-id`对应网站中的"测试号信息"模块的`appID`字段
3. `wechat.app-secret`对应网站中的"测试号信息"模块的`appsecret`字段
4. `wechat.token`参数对应网站中的"接口配置信息"模块的`Token`字段，改字段为自定义的一串字符串，只要保持`wechat.token`参数和`Token`字段一致即可
5. `wechat.page-url-domain`参数使用内网穿透工具获取到的域名
6. `wechat.page-url-prefix`参数不需要修改
7. 网站中的"接口配置信息"模块的`URL`字段只支持域名，不支持ip地址。格式为：`http://${wechat.page-url-domain}${server.context-path}/api/wechat/portal`
8. 网站中的"JS接口安全域名"模块的`域名`字段和`wechat.page-url-domain`参数保持一致
9. 在网站中的"体验接口权限表"模块中点击`网页服务-网页帐号-网页授权获取用户基本信息-修改`，在弹出的`OAuth2.0网页授权`窗口中修改`授权回调页面域名`，注意和`wechat.page-url-domain`参数保持一致
10. 扫描网站中的"测试号二维码"模块中的二维码关注测试公众账号

### 项目结构

**注：以下包名的前缀统一为`me.frank.spring.boot.wechat.`**

1. `aspect`包中存放切面操作相关类`MethodLogger`类会在`Controller`和`Service`方法执行前在控制台中输出方法名和参数，修改项目包名后注意将切入点的包名和项目包名保持一致
2. `builder`包中存放项目的构建类，已有的类不需要修改
3. `config`包中存放项目的配置类，已有的类不需要修改
4. `controller`包中存放项目的控制层类：`WechatMenuController`类用来操作微信公众号自定义菜单栏；`LoginController`类用来处理登陆相关的接口，注意每次进入微信网页时，需要调用`/api/no-auth/refresh-token/{code}`接口，用`code`换取`token`，换取失败，则说明用户尚未绑定
5. `dto`包中存放数据传输对象，已有的类不需要修改，请求相关的数据传输对象可放在`dto.request`包中
6. `entity`包中存放实体类，对应数据库中的表。如果数据库中用户表结构与示例中的表结构不一致，注意修改`AppUser`类
7. `exception`包中存放异常类，注意处理请求时，有业务异常的，抛出的`ServiceException`会被自动捕获，并返回相应的错误信息给前端
8. `handler`包中存放事件处理类，一般需要修改的类为：`SubscribeHandler`-用户订阅事件类, `UnsubscribeHandler`-用户取消订阅事件类
9. `mapper`包中存放`MyBatis`对数据库表操作接口，不需要实现，注意方法名和相应的`*Mapper.xml`文件里的操作id保持一致
10. `properties`包中存放项目配置类，包含`*.properties`文件相对应的类以及一些常量类，已有的类不需要修改
11. `repo`包中存放`QueryDsl`对数据库表操作的接口，需要继承`JpaRepository`和`QueryDslPredicateExecutor`接口，会根据方法名自动生成查询`sql`，不需要实现接口
12. `security`包中存放安全相关类，已有的类不需要修改
13. `service`包中存放业务相关类，`IWechatService`中有生成微信转向链接的方法，参数为`wechat.page-url-prefix`之后的`url`
14. `util`包中存放工具类，已有的类不需要修改
15. 项目运行生成的`*.log`文件在`/spring-boot-wechat-demo/logs`目录下