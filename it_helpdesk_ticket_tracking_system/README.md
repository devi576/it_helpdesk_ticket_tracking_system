# IT Helpdesk Ticket Tracking System

A web-based helpdesk application where users raise IT support tickets, agents get them assigned, and managers track progress, SLAs and reports.

## Tech stack
- **Backend:** Java 17, Spring Boot 3.5, Spring Security, Spring Data JPA (Hibernate), JavaMailSender, scheduled jobs
- **Database:** MySQL
- **Frontend:** Thymeleaf templates, HTML, CSS, vanilla JavaScript
- **Build:** Maven

## Features
- Register / login with role-based access (user, agent, admin)
- Create, list and filter tickets by category, priority and status
- Ticket assignment to agents and assignment status tracking
- SLA tracking per ticket
- Email notifications for ticket events
- Dashboard and reports pages

## Project layout
- `src/main/resources/` – application config, Thymeleaf templates, static CSS/JS
- `src/main/java/com/nec/` – Java source; `src/test/java/` – Testcontainers test setup (controllers, services, models, DTOs, repositories, security config)
- `pom.xml` – Maven build file

> Note: the original source files were lost, so the Java code here was recovered by decompiling the compiled classes (CFR). Lombok-generated getters, setters, builders and `toString` appear as plain code, and original comments are gone. Templates, CSS, JS and configuration are the original files.

## Configuration
Secrets are read from environment variables, never stored in the repo:

| Variable | Purpose |
|---|---|
| `DB_USERNAME` | MySQL username (default `root`) |
| `DB_PASSWORD` | MySQL password |
| `MAIL_USERNAME` | Gmail address used to send notifications |
| `MAIL_PASSWORD` | Gmail app password |

Default DB: `jdbc:mysql://localhost:3306/helpdesk_table`, server port `8085`.

## Author
Ramadevi Mahamkali – [GitHub](https://github.com/devi576)
