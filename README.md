# 🧜‍♀️ Welcome to the Enchanted Coral Kingdom

A mystical Spring Boot application that brings the magic of the underwater realm to your development journey.

## 🌊 The Siren's Song - Project Overview

This is a simple yet enchanting Hello World Spring Boot application, upgraded to the latest Spring Boot 2.7.17 and Java 17. It demonstrates the fundamental concepts of building web applications in the magical waters of Spring Boot.

## 🐚 Pearl Requirements, Dear Maiden

Before diving into these enchanted waters, ensure you have:

- **Java Development Kit (JDK)**: Version 17 or higher (the modern waters require modern tools)
- **Maven**: Version 3.6.0 or higher (to gather the pearls of dependency)
- **Git**: For cloning this mystical repository

## 🌙 Moonlit Build Instructions - Hoist the Sails

### Clone the Repository
```bash
git clone git@github.com:lnealer/test_spring_upgrade_repo.git
cd test_spring_upgrade_repo
```

### Build the Application
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will awaken and listen on `http://localhost:8080`

## 🧜‍♀️ The Mermaid's Treasures - Available Endpoints

Once the application is swimming in the waters, visit these enchanted endpoints:

- **Hello World Endpoint**: `http://localhost:8080/hello`
  - Returns a greeting from the depths of the ocean

- **Health Check**: `http://localhost:8080/actuator/health`
  - Confirms the application's vital signs

## 💎 The Treasure Vault - Database Configuration

This application uses an in-memory H2 database, perfect for development and testing in the shallow coral reefs.

### Database Access
- **Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

## 🐠 The Coral Reef - Sample Data

The application comes pre-populated with mystical sea creatures:

- **Marina** - The Wise Siren (Age: 150)
- **Nerida** - The Swift Dolphin (Age: 25)
- **Aquamarine** - The Ancient Leviathan (Age: 500)

## 🗺️ The Ship's Blueprint - Project Structure

```
test_spring_upgrade_repo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── HelloWorldController.java
│   │   │       ├── Person.java
│   │   │       └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/
│               └── ApplicationTests.java
├── pom.xml
└── README.md
```

## ⚔️ The Arsenal - Technologies Used

- **Spring Boot**: 2.7.17 (The modern enchantment)
- **Java**: 17 (The language of the deep)
- **Maven**: Build automation (The pearl gatherer)
- **H2 Database**: In-memory database (The shallow waters)
- **Spring Data JPA**: Data persistence (The memory of the sea)
- **Jakarta Persistence**: Modern JPA annotations (The new magic)

## 🧪 Testing the Magic

Run the test suite to ensure all spells are working:

```bash
mvn test
```

## 📚 Davy Jones' Locker - Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [Jakarta EE Documentation](https://jakarta.ee/)
- [AWS Spring Boot Guide](https://aws.amazon.com/blogs/opensource/category/java/)

## 🌙 The Mermaid's Blessing

*"May your code flow as smoothly as the ocean currents, and may your applications swim gracefully through the digital seas. Dive deep, code true, and let the magic of Spring Boot guide your journey through the enchanted waters of development."* 🧜‍♀️✨

---

**Happy coding in the Coral Kingdom!** 🌊💙
