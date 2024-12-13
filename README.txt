Overview
The Inventory Management System is a RESTful API built using Spring Boot that allows users to manage products, categories, and inventory levels. The system supports CRUD operations, search and filter functionalities, and generates reports for low-stock products.

Features
Category Management: Create, read, update, and delete categories.

Product Management: Create, read, update, delete, search, and filter products.

Inventory Management: Add and deduct stock levels, and generate low-stock reports.

Technologies Used
Spring Boot

Spring Data JPA

MySQL

Hibernate Validator

Springdoc OpenAPI (Swagger) for API documentation

Setup
Prerequisites
Java 17 or higher

Maven

MySQL


===========STEPS============


1) Clone the Repository: 
git clone https://github.com/yourusername/inventory-management.git
cd inventory-management

2) Set up the Database
Create a new database in MySQL: CREATE DATABASE inventory_management;


3) Update application.properties
Configure your database connection in src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html


4) Build and Run the Application:
mvn clean install
mvn spring-boot:run


========= API Documentation ===================

The API documentation is available at: http://localhost:8080/swagger-ui.html



------------------------------------------------------------------- USAGE INSTRUCTION ----------------------------------------------------------------------------

================== Category Management ================


1) Create a Category
Request: POST /api/categories
Body: 
{
    "name": "Electronics"
}

-----------------------------
2) Get All Categories: 
Request: GET /api/categories

------------------------------------
3) Get a Category by ID:
id == int
Request: GET /api/categories/{id}

--------------------------------------
4) Update a Category
Request: PUT /api/categories/{id}
Body:
{
    "name": "Updated Electronics"
}

--------------------------------------------

5) Delete a Category
Request: DELETE /api/categories/{id}


================== Product Management ================

1) Create a Product

Request: POST /api/products
Body:
{
    "name": "Laptop",
    "description": "A high-performance laptop",
    "price": 1200.00,
    "stockLevel": 100,
    "categoryId": 1
}

-----------------------------
2) Get All Products
Request: GET /api/products

------------------------------------
3) Get a Product by ID
id == int
Request: GET /api/products/{id}

--------------------------------------
4) Update a Product
Request: PUT /api/products/{id}
Body:
{
    "name": "Updated Laptop",
    "description": "An updated high-performance laptop",
    "price": 1250.00,
    "stockLevel": 90,
    "categoryId": 1
}


--------------------------------------------
5) Delete a Product
Request: DELETE /api/products/{id}


------------------------------------
6) Filter Products by Category
Request: GET /api/products/category/{categoryId}

--------------------------------------
7) Search Products by Name
Request: GET /api/products/search?name={name}

--------------------------------------------
8) Get Low Stock Products
Request: GET /api/products/low-stock?stockLevel={stockLevel}

================== Inventory Management ================

1) Add Stock to a Product

Request: POST /api/inventory/add/{productId}
Parameters: quantity
Example: POST http://localhost:8080/api/inventory/add/1?quantity=10

-----------------------------
2) Deduct Stock from a Product
Request: POST /api/inventory/deduct/{productId}
Parameters: quantity
Example:POST http://localhost:8080/api/inventory/deduct/1?quantity=5

---------------------------------
3)Get Stock Level of a Product
Request: GET /api/inventory/{productId}
Example: GET http://localhost:8080/api/inventory/1




========================Logging======================================
The application uses SLF4J for logging. Log files are created in the logs directory:
Application Logs: logs/application.log
Configuring Logging Levels

You can configure logging levels in application.properties:
logging.level.root=INFO
logging.level.com.inventory=DEBUG
logging.file.name=logs/application.log


License
This project is licensed under the MIT License.








