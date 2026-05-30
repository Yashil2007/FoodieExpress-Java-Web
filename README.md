# FoodieExpress-Java-Web 🍔

FoodieExpress is a Java Web Application developed using Servlets, JSP, and HTML that allows users to register, log in, browse food items, place orders, and view their order history.

## Features

- User Registration
- User Login Authentication
- Food Menu Display
- Order Placement
- View Order History
- Welcome Dashboard
- Servlet-Based Backend Processing

## Project Structure

```
FoodieExpress-Java-Web/
│
├── src/
│   ├── WelcomeServlet.java
│   ├── RegisterServlet.java
│   ├── LoginServlet.java
│   └── OrderServlet.java
│
├── web/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── menu.html
│   └── ViewOrders.jsp
│
├── WEB-INF/
│   └── web.xml
│
├── build.xml
└── README.md
```

## Technologies Used

- Java
- Java Servlets
- JSP
- HTML5
- Apache Tomcat
- Apache Ant

## Prerequisites

Before running the project, install:

- JDK 8 or higher
- Apache Tomcat 9+
- Apache Ant
- NetBeans IDE (Optional)

## How to Run

### 1. Clone Repository

```bash
git clone https://github.com/Yashil2007/FoodieExpress-Java-Web.git
```

### 2. Open Project

Import the project into NetBeans or Eclipse.

### 3. Configure Server

Set up Apache Tomcat and configure it as the target server.

### 4. Build Project

```bash
ant build
```

### 5. Deploy

Deploy the generated WAR file to Tomcat.

### 6. Run Application

Open:

```
http://localhost:8080/OnlineFoodOrder/
```

## Application Workflow

1. User registers a new account.
2. User logs in.
3. User views available food items.
4. User places an order.
5. Order details are processed and stored.
6. User can view placed orders.

## Future Enhancements

- Database Integration (MySQL)
- Payment Gateway
- Admin Dashboard
- Order Tracking
- Responsive UI
- Email Notifications

## Author

**Yashil Pandya**

B.Tech Information Technology

## License

This project is developed for educational and learning purposes.
