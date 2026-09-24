# 🏴‍☠️ Ahoy! The Legendary Spring Boot Treasure Map

Welcome aboard, ye scurvy dogs! This be a Spring Boot application that'll make yer heart sing like a sea shanty!

## 🗺️ Gather Yer Supplies (Prerequisites)

Before ye set sail on this grand adventure, make sure ye have:

- **Java 17** - The finest Java the seven seas have to offer! ⚓
- **Maven 3.6+** - Our trusty shipbuilder's tool 🔨
- **Spring Boot 2.7.17** - The legendary framework that powers our vessel! ⛵

## 🚀 Set Sail! (Build & Run)

### Build the Treasure Chest:
```bash
mvn clean package
```

### Launch the Vessel:
```bash
mvn spring-boot:run
```

Yer application will be ready at: `http://localhost:8080` 🌊

## 🗺️ Treasure Locations (API Endpoints)

### Ports O' Call:

**GET /api/persons** - Retrieve all the fearless buccaneers in our crew
```bash
curl http://localhost:8080/api/persons
```

**GET /api/persons/{id}** - Find a specific scallywag by their ID
```bash
curl http://localhost:8080/api/persons/1
```

**POST /api/persons** - Add a new buccaneer to our crew
```bash
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d '{"name":"Blackbeard","email":"blackbeard@pirate.com"}'
```

**PUT /api/persons/{id}** - Update a buccaneer's information
```bash
curl -X PUT http://localhost:8080/api/persons/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Captain Flint","email":"flint@pirate.com"}'
```

**DELETE /api/persons/{id}** - Send a buccaneer to Davy Jones' Locker
```bash
curl -X DELETE http://localhost:8080/api/persons/1
```

## 👥 The Fearless Buccaneers (Sample Data)

The application comes pre-loaded with these legendary pirates:

1. **Captain Jack Sparrow** - jack@pirate.com
2. **Blackbeard** - blackbeard@pirate.com
3. **Anne Bonny** - anne@pirate.com

## ⚔️ The Arsenal (Technologies)

- **Spring Boot 2.7.17** - The mighty framework! 🚀
- **Java 17** - Modern Java with all the bells and whistles! 🔔
- **Spring Data JPA** - For plunderin' the database! 💾
- **H2 Database** - Our in-memory treasure vault! 🏺
- **Maven** - Build automation, savvy? 🔨

## 🧪 Run the Tests

To ensure our ship be seaworthy:

```bash
mvn test
```

## 📝 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── controller/
│   │       │   └── HelloWorldController.java
│   │       ├── entity/
│   │       │   └── Person.java
│   │       ├── repository/
│   │       │   └── PersonRepository.java
│   │       ├── service/
│   │       │   └── PersonService.java
│   │       └── Application.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/
            └── PersonServiceTest.java
```

## 🏴‍☠️ Pirate's Code of Conduct

- Always test yer code before committin'! 🧪
- Keep yer commits clean and meaningful! 📝
- Document yer changes like a proper sailor! 📚
- Respect the codebase, or walk the plank! ⚓

---

**Yo ho ho and a bottle of rum!** 🍺 May yer code compile without errors and yer tests pass with flying colors! 🏴‍☠️

*Fair winds and following seas, ye magnificent code pirates!* ⛵✨
