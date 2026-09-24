# ⚓ Spring Boot Hello World - A Pirate's Tale 🏴‍☠️

Ahoy, ye scallywags! Welcome to the most fearsome Spring Boot application to ever sail the seven seas! This be a simple yet mighty Hello World application, upgraded to the latest treasures of Spring Boot 2.7.17 and Java 17.

## 🗺️ Prerequisites

Before ye set sail on this adventure, make sure ye have these tools in yer arsenal:

- **Java 17** or higher (the modern pirate's weapon of choice!)
- **Maven 3.6+** (to build yer ship)
- **Git** (to navigate the code seas)

## ⛵ Getting Started

### 1. Hoist the Sails (Clone the Repository)
```bash
git clone git@github.com:lnealer/test_spring_upgrade_repo.git
cd test_spring_upgrade_repo
```

### 2. Fire Up the Engines (Build the Project)
```bash
mvn clean install
```

### 3. Set Sail (Run the Application)
```bash
mvn spring-boot:run
```

The application will be ready to plunder at `http://localhost:8080`

## 🗺️ Treasure Map of Endpoints

### Welcome Endpoint
```
GET /
```
Returns a hearty pirate's greeting!

**Example Response:**
```
Ahoy! Welcome to Spring Boot 2.7.17 with Java 17! ⚓
```

### Information Endpoint
```
GET /info
```
Reveals the secrets of yer application's version and technologies!

**Example Response:**
```json
{
  "application": "Spring Boot Hello World",
  "springBootVersion": "2.7.17",
  "javaVersion": "17"
}
```

### The Crew (Sample Data)
```
GET /persons
```
Fetch all the scallywags in the database!

```
POST /persons
```
Add a new buccaneer to yer crew!

**Request Body:**
```json
{
  "name": "Blackbeard",
  "email": "blackbeard@pirate.com"
}
```

## 🏗️ Ship's Blueprint (Project Structure)

```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── HelloWorldController.java    (The Captain's Orders)
│   │       ├── Person.java                  (The Crew Member)
│   │       ├── PersonRepository.java        (The Ship's Manifest)
│   │       └── PersonService.java           (The First Mate)
│   └── resources/
│       └── application.properties           (The Ship's Configuration)
└── test/
    └── java/
        └── com/example/
            └── PersonServiceTest.java       (Testing the Waters)
```

## ⚙️ The Ship's Arsenal (Technologies)

- **Spring Boot 2.7.17** - The latest stable treasure!
- **Java 17** - Modern, powerful, and ready for adventure!
- **Spring Data JPA** - For managing yer data treasures
- **H2 Database** - An in-memory database, perfect for testing
- **Maven** - To build yer mighty vessel

## 🧪 Testing the Waters

Run the unit tests to make sure everything be shipshape:

```bash
mvn test
```

All tests should pass without a hitch!

## 📝 Recent Upgrades (The Great Voyage)

This application has been upgraded from the old ways to the modern era:

- ✅ Spring Boot: 2.3.12.RELEASE → **2.7.17**
- ✅ Java: 1.8 → **17**
- ✅ Jakarta EE 9+ Compliance (javax → jakarta migration)
- ✅ All tests passing and ready for battle!

## 🏴‍☠️ Contributing

Shiver me timbers! If ye wish to contribute to this pirate's treasure, follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/yer-feature`)
3. Commit yer changes (`git commit -am 'Add some pirate magic'`)
4. Push to the branch (`git push origin feature/yer-feature`)
5. Open a Pull Request

## 📜 License

This project be released under the MIT License - see the LICENSE file for details.

---

**Fair winds and following seas, ye landlubbers!** ⚓🏴‍☠️
