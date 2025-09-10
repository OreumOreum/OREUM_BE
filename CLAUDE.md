# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

OREUM (오름) is a Spring Boot REST API backend for a travel planning application. The project provides user authentication, place/course management, folder organization, planning features, and data synchronization with external tourism APIs.

## Build and Development Commands

### Build and Testing
```bash
./gradlew build                    # Build the project
./gradlew test                     # Run all tests
./gradlew test --tests ClassName   # Run specific test class
./gradlew bootRun                  # Run the application locally
```

### Development Utilities
```bash
./gradlew clean                    # Clean build artifacts
./gradlew bootJar                  # Create executable JAR
```

### Database
- H2 in-memory database for development (accessible at `/h2-console`)
- MySQL for production (configure via environment variables)

### API Documentation
- Swagger UI available at `/swagger-ui.html` when running locally
- Protected with basic auth (credentials in application.properties)

## Architecture

### Domain Structure
The application follows Domain-Driven Design with the following main domains:

- **auth**: OAuth2 authentication (Kakao, Google, Apple), JWT token management
- **member**: User management with category preferences and folder relationships
- **place**: Tourism places with categorization and review system
- **spot**: Visit logs and location tracking
- **planner**: Trip planning and itinerary management
- **folder**: Organization system for saved places/courses
- **scheduler**: External API synchronization and batch processing

### Package Organization
```
com.zzarit.oreum/
├── auth/           # Authentication & authorization
├── member/         # User management
├── place/          # Tourism places & courses
├── spot/           # Visit tracking
├── planner/        # Trip planning
├── folder/         # Content organization
├── scheduler/      # Data synchronization
├── global/         # Shared utilities and configuration
└── logging/        # Request/response logging
```

### Key Architectural Patterns

**Repository Pattern**: Each domain has its own repository interfaces extending JpaRepository
**Service Layer**: Business logic is encapsulated in service classes
**DTO Pattern**: Request/Response DTOs separate API contracts from domain models
**Entity Relationships**: JPA entities with proper cascade settings and lazy loading

### Security & Authentication
- JWT-based authentication with access/refresh tokens
- OAuth2 integration with Kakao, Google, and Apple
- Cookie-based session management
- Custom authentication interceptors and argument resolvers

### Data Synchronization
The `SynchronizeService` handles:
- Fetching tourism data from external OpenAPI
- Batch processing of places and courses
- Category mapping and relationship building
- Scheduled synchronization tasks (enabled via `@EnableScheduling`)

### Configuration Management
- Profile-based configuration (local/production)
- Environment-specific properties via application.yml
- Sensitive data managed through environment variables
- Custom security configurations in SecurityConfig

### Database Schema
- Uses JPA/Hibernate with MySQL dialect
- Entities extend BaseTimeEntity for audit timestamps
- QueryDSL integration for complex queries
- Relationship mappings: Member → Folder → Places, Planner relationships

### Logging & Monitoring
- Structured logging with track IDs for request tracing
- Log file rotation (100MB files, 14 days retention)
- Health check endpoint at `/health-check`
- Request/response logging via custom interceptors

## Testing Strategy
- JUnit 5 with Spring Boot Test
- Test fixtures in `util` package for common test data
- Separate test configurations for authentication
- Integration tests for controllers and services