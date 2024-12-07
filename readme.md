# WeChat Mini Program Backend Project

## Project Overview

This project is a backend for a WeChat Mini Program, developed using the **Spring Boot** and **MyBatis-Plus** frameworks. It implements a separated frontend-backend architecture, providing a complete set of backend APIs for the Mini Program frontend. The functionality covers user management, product browsing, order processing, and more. The backend code here handles all the necessary operations.

The Mini Program is mainly used for an online food ordering service. Its features include:  
- **Regular Users**: Viewing the menu, adding items to the cart, making payments (supports multiple payment methods), receiving coupons.
- **Admin Users**: Managing products, order processing, and distributing coupons.

## Technology Stack

- **Spring Boot**: Used to build the backend service.
- **MyBatis-Plus**: Simplifies database operations and provides efficient CRUD support.
- **MySQL**: Database for storing user, product, and order data.
- **Maven**: Used for dependency management and project builds.

## Project Structure

```
src/
 ├── main/
 │    ├── java/
 │    │    └── com/
 │    │         └── example/
 │    │             ├── controller/   # Controller layer, handles frontend requests
 │    │             ├── service/      # Business logic layer
 │    │             ├── mapper/       # MyBatis database mapping layer
 │    │             ├── model/        # Data model layer
 │    │             │    ├── dto/     # Data Transfer Objects
 │    │             │    ├── domain/  # Entity classes
 │    │             └── common/       # Common modules (utility classes, configurations, etc.)
 │    │                 ├── config/   # Configuration classes
 │    │                 └── utils/    # Utility classes
 │    ├── resources/
 │    │    ├── application.yml       # Configuration file
 │    │    └── mapper/               # MyBatis Mapper XML files
 ├── target/                           # Compiled output, generated class files, and resources
 ├── .git/                             # Git version control folder, ignored
 ├── .idea/                            # IntelliJ IDEA project configuration folder, ignored
 ├── pom.xml                           # Maven configuration file
 └── README.md                         # Project documentation
```

## Installation & Setup

1. **Clone the Project**  
   Clone the repository to your local machine using the following command:
   ```bash
   git clone <repository_url>
   ```

2. **Configure the Database**  
   Create a database in MySQL and import the required schema. Then, modify the database connection settings in `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/weapp_db
       username: root
       password: your_password
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

3. **Build and Run the Project**  
   Build the project using Maven:
   ```bash
   mvn clean install
   ```
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
   The backend service will be available at `http://localhost:8080`.

## Features

- User Registration and Login
- Product List Display and Search
- Shopping Cart Management
- Order Creation and Management

## Contribution

If you have any improvement suggestions or find bugs, feel free to submit a pull request (PR) or create an issue.  
The frontend was developed by Shuyi Jin and Zhuoting Lyu and is not included in this repository, but we appreciate their contribution.

## Contact

- Project Lead: [Kyennxia]
- Email: [qinixia77@gmail.com]
