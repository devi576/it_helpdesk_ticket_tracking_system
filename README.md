# IT Helpdesk Ticket Tracking System

A web application where users raise IT support tickets, agents and admins assign and work on them, and everyone can follow a ticket from creation to resolution. Built with Java, Spring Boot and MySQL.

## Features
- **Authentication and roles:** register and log in with Spring Security (BCrypt-hashed passwords). Roles: `ADMIN`, `AGENT`, `USER`, `MANAGER`.
- **Ticket management:** create, view, update and delete tickets, with
  - **Status:** Open, In Progress, Pending Customer, Pending Vendor, Resolved, Closed, Cancelled
  - **Priority:** Low, Medium, High, Critical
  - **Category:** Technical Issue, Billing, Account Access, Feature Request, Bug Report, General Inquiry
- **Assignment:** assign tickets to agents, reassign them with a reason, and track assignment status.
- **Search and filters:** find tickets by keyword, status, priority, user or assigned agent.
- **SLA tracking:** overdue tickets and tickets at risk of breaching their SLA.
- **Dashboard and reports:** ticket counts, recent tickets, active agents, priority and category distribution, average resolution time.
- **Agent management:** admins can add and view agents.
- **Email notifications:** Spring Mail (Gmail SMTP) and scheduled background checks.

## Tech stack
| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.5, Spring MVC, Spring Security, Spring Data JPA (Hibernate), Spring Mail |
| Database | MySQL 8 |
| Frontend | Thymeleaf, HTML, CSS, JavaScript |
| Build | Maven |

## Project structure
```
src/main/java/com/nec
├── config/        # default data loading
├── controller/    # page controllers + REST controllers
├── dto/           # request/response objects
├── model/         # Ticket, User, SLA, TicketAssignment entities
├── repository/    # Spring Data JPA repositories
├── security/      # Spring Security, mail and scheduler config
└── service/       # business logic (tickets, users, SLA, email)
src/main/resources
├── templates/     # Thymeleaf pages (auth, dashboard, tickets, agents, reports)
├── static/        # CSS and JavaScript
└── application.properties
```

## REST API (main endpoints)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/tickets` | Create a ticket |
| GET | `/api/tickets/{id}` | Get a ticket |
| PUT | `/api/tickets/{id}` | Update a ticket |
| DELETE | `/api/tickets/{id}` | Delete a ticket |
| POST | `/api/tickets/assign` | Assign a ticket to an agent |
| POST | `/api/tickets/{ticketId}/reassign/{newAgentId}` | Reassign with a reason |
| POST | `/api/tickets/{ticketId}/status` | Change status |
| POST | `/api/tickets/{ticketId}/resolve` | Resolve with notes |
| GET | `/api/tickets/status/{status}`, `/priority/{priority}` | Filter tickets |
| GET | `/api/tickets/search`, `/overdue`, `/sla-breaching`, `/metrics` | Search, SLA and metrics |
| GET/POST | `/api/agents` | List / add agents |

## Getting started

### Prerequisites
- JDK 17+
- Maven 3.8+ (or the IDE's built-in Maven)
- MySQL 8 running locally

### 1. Create the database
```sql
CREATE DATABASE helpdesk_table;
```
Tables are created automatically by Hibernate on first run.

### 2. Set environment variables
Passwords are never stored in this repository. Set these before running:

| Variable | Purpose |
|---|---|
| `DB_USERNAME` | MySQL username (default `root`) |
| `DB_PASSWORD` | MySQL password |
| `MAIL_USERNAME` | Gmail address that sends notifications |
| `MAIL_PASSWORD` | Gmail **app password** (not your normal password) |

### 3. Run
```bash
mvn spring-boot:run
```
Open http://localhost:8085

### Default login (created on first run)
| Role | Email | Password |
|---|---|---|
| Admin | `admin@helpdesk.com` | `admin123` |
| User | `user@helpdesk.com` | `user123` |

These are demo accounts only. Change them before using the app for anything real.

## Notes
- The original source files were lost, so the Java code was recovered by decompiling the compiled classes. Templates, CSS, JavaScript and configuration are the original files.
- Email notifications need a valid Gmail app password; the app still starts without one.

## Author
**Ramadevi Mahamkali**
[GitHub](https://github.com/devi576) · [LinkedIn](https://www.linkedin.com/in/ramadevi-mahamkali-625801277)
