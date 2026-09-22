# Ahoy! The Hello World Spring Boot Application 🏴‍☠️

Avast ye! A simple Hello World web application built with Java 17 and Spring Boot 2.7.17, fit fer any scallywag developer on the seven seas!

## Prerequisites, Ye Landlubber

- Java 17 (JDK 17) - Aye, the latest treasure from the Java vault!
- Maven 3.x - The trusty tool fer buildin' yer booty

## Build and Run, Shiver Me Timbers!

### Usin' Maven, Ye Salty Sea Dog

```bash
# Build the application - Hoist the colors!
mvn clean package

# Run the application - Set sail!
mvn spring-boot:run
```

### Usin' Java, Ye Bilge Rat

```bash
# Build first - Prepare the cannons!
mvn clean package

# Run the JAR - Fire the cannons!
java -jar target/hello-world-app-1.0.0.jar
```

## Available Endpoints, Ye Scurvy Dogs

Once the application be runnin', access these endpoints on yer voyage:

### Basic Endpoints - The Crow's Nest
- `http://localhost:8080/` - Home port with a hearty welcome message
- `http://localhost:8080/hello` - Ahoy endpoint (default: "Hello, World!")
- `http://localhost:8080/hello?name=YourName` - A personalized greetin' fer yer matey
- `http://localhost:8080/info` - Application information in JSON treasure format

### Database Endpoints - The Treasure Chest
- `GET http://localhost:8080/api/persons` - Plunder all persons from the database vault
- `GET http://localhost:8080/api/persons/search?name=John` - Search fer persons by exact name (case-insensitive, like a true pirate!)
- `GET http://localhost:8080/api/persons/search-partial?name=john` - Search fer persons by partial name match (fer when ye can't remember the full moniker)
- `POST http://localhost:8080/api/persons` - Bury a new person in the database

### H2 Database Console - The Captain's Log
- `http://localhost:8080/h2-console` - H2 database web console fer inspectin' yer booty
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa` (Super Admiral)
  - Password: (leave empty, like a true pirate's honor)

## Testin' Yer Vessel, Ye Scallywag

```bash
# Basic endpoints - Test the waters!
curl http://localhost:8080/
curl http://localhost:8080/hello
curl http://localhost:8080/hello?name=John
curl http://localhost:8080/info

# Database endpoints - Plunder the treasure!
curl http://localhost:8080/api/persons
curl "http://localhost:8080/api/persons/search?name=John"
curl "http://localhost:8080/api/persons/search-partial?name=john"

# Create a new person - Add to yer crew!
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com"}'
```

## Sample Data - The Crew

The application sets sail with the followin' sample crew members:
- John Doe (john.doe@example.com) - The First Mate
- Jane Smith (jane.smith@example.com) - The Navigator
- Bob Johnson (bob.johnson@example.com) - The Quartermaster
- Alice Williams (alice.williams@example.com) - The Bosun
- John Smith (john.smith@example.com) - The Cabin Boy

## Project Structure - The Ship's Layout

```
.
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── helloworld
│   │   │               ├── HelloWorldApplication.java
│   │   │               ├── HelloWorldController.java
│   │   │               ├── Person.java
│   │   │               ├── PersonController.java
│   │   │               ├── PersonService.java
│   │   │               ├── PersonRepository.java
│   │   │               └── DataInitializer.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── PersonServiceTest.java
└── README.md
```

## Technologies Used - The Arsenal

- Java 17 - The latest cutlass in the Java armory!
- Spring Boot 2.7.17 - The mightiest ship on the digital seas!
- Spring Web - Fer buildin' web endpoints like a true corsair
- Spring Data JPA - Fer plunderin' data from the database
- H2 Database - An in-memory database, quick as a cannonball
- Maven - The trusty shipwright's tool
- JUnit 5 & Mockito - Fer testin' yer code like a true buccaneer

## AWS Bedrock
### AWS Docs used:
- https://docs.aws.amazon.com/bedrock/latest/userguide/agents-kb-add.html
- https://docs.aws.amazon.com/bedrock/latest/userguide/knowledge-base.html
- https://docs.aws.amazon.com/bedrock/latest/userguide/knowledge-base-build.html

Gemini Prompts: how to create aws bedrock knowledge base containing html files
s3 bucket created: java-spring-upgrade

---

**Yo ho ho! May yer code compile without errors and yer tests pass with glory! 🏴‍☠️⚓**
