# 微信小程序后端项目

## 项目简介

这是一个基于 **Spring Boot** 和 **MyBatis-Plus** 框架开发的微信小程序后端项目。项目实现了前后端分离，提供了一套完整的后端接口供微信小程序前端调用，功能涵盖用户管理、商品浏览、订单处理等。这里的代码覆盖了所有后端的内容。
小程序主要用于小程序点餐服务，功能包括：普通用户：预览菜单、加购物车、支付（支持多种支付方式）、优惠券领取；用户管理员：管理商品，上菜和优惠券发放。

## 技术栈

- **Spring Boot**：用于构建后端服务。
- **MyBatis-Plus**：简化数据库操作，提供高效的 CRUD 支持。
- **MySQL**：数据库存储用户、商品和订单等数据。
- **Maven**：用于项目依赖管理和构建。

## 项目结构

```
src/
 ├── main/
 │    ├── java/
 │    │    └── com/
 │    │         └── example/
 │    │             ├── controller/   # 控制器层，处理前端请求
 │    │             ├── service/      # 业务逻辑层
 │    │             ├── mapper/       # MyBatis 数据库映射层
 │    │             └── model/        # 数据模型层
 │    ├── resources/
 │    │    └── application.yml       # 配置文件
 ├── pom.xml                           # Maven 配置文件
```

## 安装与运行

1. **克隆项目**  
   使用以下命令将项目克隆到本地：
   ```bash
   git clone <项目仓库地址>
   ```

2. **配置数据库**  
   在 MySQL 中创建数据库并导入所需的表结构。修改 `src/main/resources/application.yml` 中的数据库连接配置：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/weapp_db
       username: root
       password: 你的密码
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

3. **构建并启动项目**  
   使用 Maven 构建项目：
   ```bash
   mvn clean install
   ```
   启动 Spring Boot 应用：
   ```bash
   mvn spring-boot:run
   ```
   后端服务会在 `http://localhost:8080` 上运行。

## 功能

- 用户注册与登录
- 商品列表展示与搜索
- 购物车管理
- 订单创建与管理

## 贡献

如果你有任何改进建议或发现了 bug，欢迎提交 PR 或 Issues。
前端部分由Shuyi Jin and Zhuoting lyu完成，并没有放到这里，但仍旧感谢他们的贡献。

## 联系方式

- 项目负责人：[Kyennxia]
- Email：[qinixia77@gmail.com]
