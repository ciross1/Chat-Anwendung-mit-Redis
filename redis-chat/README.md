# 💬 Chat-Anwendung mit Redis & Spring Boot

Eine Echtzeit-Chat-Anwendung die **Redis Pub/Sub Messaging** mit **Spring Boot** demonstriert.

---

## 📋 Beschreibung

Dieses Projekt zeigt wie man mit Redis Pub/Sub eine entkoppelte Kommunikation zwischen Services aufbaut. Nachrichten werden über einen Redis-Kanal gesendet und in Echtzeit empfangen – ohne dass Sender und Empfänger sich direkt kennen.

---

## 🛠️ Technologien

| Technologie | Version | Zweck |
|---|---|---|
| Java | 17 | Programmiersprache |
| Spring Boot | 3.3.x | Framework |
| Redis | latest | Pub/Sub Messaging |
| Docker Compose | - | Redis automatisch starten |
| Maven | - | Dependency Management |

---

## 📁 Projektstruktur

```
redis-chat/
├── src/
│   └── main/
│       └── java/com/ciro/redis_chat/
│           ├── ChatMessage.java        # Nachricht-Objekt
│           ├── ChatPublisher.java      # Nachrichten senden
│           ├── ChatReceiver.java       # Nachrichten empfangen
│           ├── ChatConfig.java         # Redis konfigurieren
│           └── ChatController.java     # REST Endpoints
├── compose.yml                         # Docker Compose für Redis
├── pom.xml
└── README.md
```

---

## 🚀 Wie starten?

### Voraussetzungen
- Java 17 oder höher installiert
- Docker Desktop installiert und gestartet
- Maven installiert

### Schritte

**1. Repository klonen**
```bash
git clone https://github.com/ciross1/Chat-Anwendung-mit-Redis.git
cd Chat-Anwendung-mit-Redis
```

**2. Anwendung starten**
```bash
mvn spring-boot:run
```

> Redis startet automatisch über Docker Compose – kein manueller Start nötig! ✅

---

## 📡 Wie funktioniert es?

```
Benutzer A sendet Nachricht
        │
        ▼
POST /chat/send
        │
        ▼
ChatPublisher → Redis "chat-kanal" → ChatReceiver
                                          │
                                          ▼
                                   Nachricht gespeichert
                                          │
                                          ▼
                              GET /chat/messages ← Benutzer B
```

---

## 🔗 REST Endpoints

| Method | Endpoint | Beschreibung |
|---|---|---|
| `POST` | `/chat/send?user=Anna&message=Hallo` | Nachricht senden |
| `GET` | `/chat/messages` | Alle Nachrichten abrufen |

---

## 💡 Was ich gelernt habe

- **Redis Pub/Sub** – Nachrichten zwischen Services weiterleiten
- **Spring Boot Beans** – `@Component`, `@Configuration`, `@Bean`
- **Docker Compose** – Services automatisch starten und stoppen
- **Entkoppelte Kommunikation** – Sender und Empfänger kennen sich nicht direkt
- **REST APIs** – Endpoints mit Spring Boot erstellen

---

## 👤 Autor

**Ciro Santamarina**  
GitHub: [@ciross1](https://github.com/ciross1)
