# Ahoy! The Pirate's Hello World Spring Boot Application 🏴‍☠️

Shiver me timbers! A swashbuckling Hello World web application built with Java 17 and Spring Boot 2.7.17, ready to sail the seven seas of code!

## Prerequisites - Gather Yer Booty

- Java 17 (JDK 17) - The finest treasure from the Java isles!
- Maven 3.x - Yer trusty shipbuilding tool

## Build and Run - Hoist the Sails!

### Using Maven - The Captain's Way

```bash
# Prepare the vessel for battle
mvn clean package

# Set sail with Spring Boot
mvn spring-boot:run
```

### Using Java - The Pirate's Way

```bash
# Build yer ship first, ye scallywag!
mvn clean package

# Fire up the cannons and launch the JAR
java -jar target/hello-world-app-1.0.0.jar
```

## Available Endpoints - Treasure Maps

Once yer vessel is sailin', navigate to these ports:

### Basic Ports o' Call
- `http://localhost:8080/` - The main deck with a hearty welcome
- `http://localhost:8080/hello` - The hello port (default: "Ahoy, World!")
- `http://localhost:8080/hello?name=YourName` - A personalized greeting from the captain
- `http://localhost:8080/info` - Ship's manifest in JSON format

### Database Ports - The Treasure Vault
- `GET http://localhost:8080/api/persons` - Plunder all persons from the treasure vault
- `GET http://localhost:8080/api/persons/search?name=John` - Search fer a specific scallywag by name
- `GET http://localhost:8080/api/persons/search-partial?name=john` - Search fer partial matches, ye clever sea dog
- `POST http://localhost:8080/api/persons` - Add a new crew member to the roster

### H2 Database Console - The Captain's Log
- `http://localhost:8080/h2-console` - The ship's logbook interface
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa` (the Ship's Admiral)
  - Password: (leave empty - no secrets among pirates!)

## Testing - Fire the Cannons!

```bash
# Test the basic ports
curl http://localhost:8080/
curl http://localhost:8080/hello
curl http://localhost:8080/hello?name=John
curl http://localhost:8080/info

# Raid the database ports
curl http://localhost:8080/api/persons
curl "http://localhost:8080/api/persons/search?name=John"
curl "http://localhost:8080/api/persons/search-partial?name=john"

# Recruit a new crew member
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com"}'
```

## Sample Crew - The Pirate's Posse

The ship sets sail with these fine buccaneers already aboard:
- John Doe (john.doe@example.com) - The First Mate
- Jane Smith (jane.smith@example.com) - The Navigator
- Bob Johnson (bob.johnson@example.com) - The Quartermaster
- Alice Williams (alice.williams@example.com) - The Bosun
- John Smith (john.smith@example.com) - The Rigger

## Project Structure - The Ship's Blueprint

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
│   │   │               └── HelloWorldController.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── helloworld
└── README.md
```

## Technologies Used - The Arsenal

- Java 17 - The modern cutlass of the Java seas!
- Spring Boot 2.7.17.RELEASE - The finest ship in the fleet!
- Spring Web - The sails that catch the wind
- Maven - The shipwright's hammer

## AWS Bedrock - The Mystical Treasure Map
### AWS Docs used:
- https://docs.aws.amazon.com/bedrock/latest/userguide/agents-kb-add.html
- https://docs.aws.amazon.com/bedrock/latest/userguide/knowledge-base.html
- https://docs.aws.amazon.com/bedrock/latest/userguide/knowledge-base-build.html

Gemini Prompts: how to create aws bedrock knowledge base containing html files
s3 bucket created: java-spring-upgrade

---

*"Yo ho ho and a bottle of Spring Boot! May yer code compile without errors and yer tests pass with flying colors!"* 🏴‍☠️⚓
