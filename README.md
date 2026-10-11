# Arka's Retail Billing System

A full-stack retail billing and management application built using **Java, Spring Boot, React.js, and MySQL**. The application streamlines retail operations by providing product and category management, customer billing, order processing, payment handling, user authentication, and sales dashboard functionality. It integrates **AWS S3** for image storage and **Razorpay** for UPI payments.

## 🚀 Features

* **Authentication & Authorization:** JWT-based authentication, role-based access control, and password encryption using BCrypt.
* **Category Management:** Create, view, search, and delete product categories with image uploads.
* **Item Management:** Manage retail items, prices, descriptions, categories, and product images.
* **Billing & Cart Management:** Add items to the cart, update quantities, remove items, and calculate subtotal, tax, and grand total.
* **Order Management:** Create orders, maintain order details, and view recent orders and order history.
* **Payment Integration:** Support for cash payments and Razorpay-powered UPI payments.
* **Receipt Generation:** Display and print customer receipts containing order details and payment information.
* **Dashboard & Sales Analytics:** View today's sales, daily order count, and recent transactions.
* **User Management:** Register, view, search, and delete users with assigned roles.
* **Cloud Image Storage:** Upload and manage category and item images using AWS S3.
* **REST API Testing:** Test backend endpoints using Postman.
* **Responsive Interface:** Interactive user interface built with React.js, Bootstrap, and custom CSS.

## 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring Web / REST APIs
* Spring Security
* Spring Data JPA
* Hibernate
* Maven
* JWT (JSON Web Token)
* BCrypt Password Encoding
* AWS SDK for Java

### Frontend

* React.js
* JavaScript (ES6+)
* HTML5
* CSS3
* Bootstrap 5
* Bootstrap Icons
* React Router DOM
* React Context API
* Axios
* React Hot Toast
* Vite

### Database & Cloud Services

* MySQL
* AWS S3
* Razorpay Payment Gateway

### Development & Testing Tools

* IntelliJ IDEA
* Visual Studio Code
* MySQL Workbench
* Postman
* Git & GitHub

## 🏗️ Application Architecture

The application follows a client-server architecture:

1. **Frontend:** React.js provides the user interface for billing, inventory management, authentication, and dashboards.
2. **Backend:** Spring Boot exposes REST APIs and handles business logic, authentication, order processing, and payment workflows.
3. **Database:** MySQL stores users, categories, items, orders, and related billing information.
4. **Cloud Storage:** AWS S3 stores uploaded product and category images.
5. **Payment Gateway:** Razorpay supports online UPI payment processing.

## 📂 Project Structure

```text
billing-software/
├── backend/
│   ├── src/main/java/
│   │   └── in/arka/billingsoftware/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── filter/
│   │       ├── io/
│   │       ├── repository/
│   │       ├── service/
│   │       └── BillingsoftwareApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/
    ├── src/
    │   ├── components/
    │   ├── pages/
    │   ├── services/
    │   ├── context/
    │   ├── assets/
    │   ├── App.jsx
    │   ├── main.jsx
    │   └── index.css
    ├── package.json
    └── vite.config.js
```

*Note: The directory structure above is illustrative. Adjust folder names to match your actual repository.*

## ⚙️ Installation and Setup

### Prerequisites

Install the following before running the application:

* Java Development Kit (JDK)
* Maven
* Node.js and npm
* MySQL Server and MySQL Workbench
* AWS account with an S3 bucket
* Razorpay test account
* Postman (for API testing)

### 1. Clone the Repository

```bash
git clone <your-github-repository-url>
cd billing-software
```

### 2. Configure the MySQL Database

Create the database in MySQL:

```sql
CREATE DATABASE billing_app;
```

Update the backend configuration with your local database credentials.

### 3. Configure Backend Properties

In `backend/src/main/resources/application.properties`, configure your database, AWS S3, JWT, and Razorpay settings.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/billing_app
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
server.servlet.context-path=/api/v1.0

aws.access.key=${AWS_ACCESS_KEY}
aws.secret.key=${AWS_SECRET_KEY}
aws.region=${AWS_REGION}
aws.bucket.name=${AWS_BUCKET_NAME}

jwt.secret.key=${JWT_SECRET_KEY}

razorpay.key.id=${RAZORPAY_KEY_ID}
razorpay.key.secret=${RAZORPAY_KEY_SECRET}

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

Replace the placeholders with environment variables or your local configuration. Never commit actual passwords, access keys, payment secrets, or JWT signing secrets to GitHub.

### 4. Run the Spring Boot Backend

Open a terminal in the backend directory:

```bash
cd backend
mvn spring-boot:run
```

The backend runs on:

```text
http://localhost:8080/api/v1.0
```

### 5. Run the React Frontend

Open a separate terminal in the frontend directory:

```bash
cd frontend
npm install
npm run dev
```

Open the local URL printed by Vite, typically:

```text
http://localhost:5173
```

### 6. Test APIs Using Postman

Use Postman to test the backend REST endpoints.

| Method | Endpoint                                  | Purpose                             |
| ------ | ----------------------------------------- | ----------------------------------- |
| POST   | `/api/v1.0/login`                         | Authenticate a user                 |
| GET    | `/api/v1.0/categories`                    | Retrieve categories                 |
| POST   | `/api/v1.0/admin/categories`              | Create a category                   |
| DELETE | `/api/v1.0/admin/categories/{categoryId}` | Delete a category                   |
| GET    | `/api/v1.0/items`                         | Retrieve items                      |
| POST   | `/api/v1.0/admin/items`                   | Create an item                      |
| DELETE | `/api/v1.0/admin/items/{itemId}`          | Delete an item                      |
| POST   | `/api/v1.0/orders`                        | Create an order                     |
| GET    | `/api/v1.0/orders/latest`                 | Retrieve recent orders              |
| GET    | `/api/v1.0/dashboard`                     | Retrieve dashboard data             |
| POST   | `/api/v1.0/payments/create-order`         | Create a Razorpay order             |
| POST   | `/api/v1.0/payments/verify`               | Submit payment verification details |
| GET    | `/api/v1.0/admin/users`                   | Retrieve users                      |
| POST   | `/api/v1.0/admin/register`                | Register a user                     |

For protected endpoints, include the JWT token in the request's **Authorization** header using the Bearer token format. Category and item creation endpoints require multipart form data containing the JSON data and image file.

*Note: These endpoints reflect the application routes; verify HTTP methods and request payloads against the implemented controllers.*

## 🔐 Security and Configuration

* JWT-based authentication for secured API requests.
* Role-based authorization for administrative operations.
* BCrypt password hashing.
* Environment-based configuration for sensitive credentials.
* AWS S3 and Razorpay credentials configured separately from source code.

## 👨‍💻 Author

**Arka Prava De**

B.Tech in Computer Science and Engineering

GitHub: [Add your GitHub profile URL](https://github.com/)

## 📄 License

This project was created for learning and demonstration purposes. Add a suitable open-source license if you intend to distribute it publicly.
