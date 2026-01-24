# 🏨 StaySphere-Backend - Hotel Management System

A comprehensive **REST API backend** for a hotel booking platform built with **Spring Boot 4.0.1** and **Java 21**. This system handles hotel management, room inventory, guest bookings, dynamic pricing, and payment processing with Stripe integration.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Features](#features)
- [Project Architecture](#project-architecture)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Security](#security)
- [Recent Improvements](#recent-improvements)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

**StaySphere** is a full-featured hotel booking and management platform backend that enables:

- **Hotel Owners**: Manage multiple hotels, rooms, inventory, and pricing strategies
- **Guests**: Search hotels, make bookings, manage profiles, and pay securely
- **Admins**: Track bookings, revenue, and generate detailed reports

The system implements modern practices including JWT authentication, dynamic pricing algorithms, transaction management, and comprehensive error handling.

---

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot**: 4.0.1 (Latest stable)
- **Spring Security**: JWT-based authentication
- **Spring Data JPA**: ORM with Hibernate
- **Spring Validation**: Input validation with Jakarta Bean Validation

### Database
- **PostgreSQL**: Primary relational database
- **Hibernate ORM**: Object-relational mapping
- **JPA Transactions**: ACID compliance

### Payment Integration
- **Stripe Java SDK**: 31.2.0 - Payment processing and webhooks

### Development & DevOps
- **Java**: 21 (Latest LTS)
- **Maven**: Dependency management & build tool
- **Lombok**: Reduce boilerplate code
- **ModelMapper**: 3.2.6 - DTO to Entity mapping
- **JJWT**: 0.12.6 - JWT token generation & validation
- **SpringDoc OpenAPI**: 2.8.3 - API documentation with Swagger

### API Documentation
- **Swagger/OpenAPI**: Interactive API documentation
- **SpringDoc**: Auto-generates API specs

---

## ✨ Features

### 🏩 Hotel Management
- ✅ Create, read, update, delete hotels
- ✅ Hotel activation and deactivation
- ✅ Contact information management
- ✅ Hotel amenities and photos
- ✅ Multi-hotel support per admin

### 🛏️ Room Management
- ✅ Create and manage room types
- ✅ Room amenities and photos
- ✅ Capacity and pricing configuration
- ✅ Bulk inventory management
- ✅ Room availability tracking

### 📅 Booking System
- ✅ Advanced hotel search with filters
- ✅ Multi-guest booking support
- ✅ Dynamic date range selection
- ✅ Real-time availability checking
- ✅ Booking status tracking (RESERVED → GUESTS_ADDED → PAYMENTS_PENDING → CONFIRMED)
- ✅ Booking cancellation with refunds
- ✅ 10-minute booking expiry

### 💰 Dynamic Pricing
- ✅ Base price configuration
- ✅ Multiple pricing strategies:
  - **Holiday Pricing**: Surge pricing on holidays
  - **Occupancy Pricing**: Price varies with room occupancy
  - **Surge Pricing**: Peak time pricing adjustments
  - **Urgency Pricing**: Last-minute booking discounts
- ✅ Surge factor customization

### 💳 Payment Processing
- ✅ Stripe Checkout integration
- ✅ Secure payment sessions
- ✅ Webhook support for payment confirmation
- ✅ Refund processing
- ✅ Payment status tracking

### 👤 User Management
- ✅ User registration with email validation
- ✅ Strong password requirements
- ✅ Profile management
- ✅ Guest profile management
- ✅ Role-based access control (GUEST, HOTEL_MANAGER)

### 🔐 Authentication & Authorization
- ✅ JWT token-based authentication
- ✅ Refresh token support via HTTP-only cookies
- ✅ Role-based endpoint protection
- ✅ User ownership verification

### 📊 Reporting & Analytics
- ✅ Hotel booking reports
- ✅ Revenue tracking
- ✅ Booking count analytics
- ✅ Date range filtering
- ✅ Average revenue calculations

### 🔔 Inventory Management
- ✅ Year-long room inventory creation
- ✅ Booked/reserved/available count tracking
- ✅ Surge factor adjustment
- ✅ Room closure management
- ✅ Dynamic price calculation

---

## 🏗️ Project Architecture

### Layered Architecture

```
HotelManagementApp/
├── controller/          # REST endpoints & request handling
├── service/             # Business logic layer
├── repository/          # Data access layer (JPA)
├── entity/              # JPA entities (database models)
├── dto/                 # Data transfer objects
├── security/            # JWT & authentication
├── advice/              # Global exception handling
├── config/              # Spring configuration
├── strategy/            # Pricing strategy pattern
├── enums/               # Enumeration classes
├── Exception/           # Custom exceptions
└── util/                # Utility functions
```

### Layer Responsibilities

#### 🎯 Controller Layer
```
8 REST Controllers handling:
- Authentication (signup, login, token refresh)
- Hotel CRUD operations
- Room management
- Booking workflow (init, add guests, payment, cancel)
- Hotel search & browsing
- Inventory management
- User profile management
- Payment webhooks
```

#### 🔄 Service Layer
```
11 Services implementing business logic:
- AuthService: Authentication & authorization
- HotelService: Hotel operations
- RoomService: Room management
- BookingService: Complete booking lifecycle
- InventoryService: Room inventory & pricing
- UserService: User profile management
- GuestService: Guest management
- CheckoutService: Payment session creation
- PricingService: Dynamic price calculation (Strategy Pattern)
```

#### 💾 Repository Layer
```
8 Repositories for data access:
- HotelRepository: Hotel queries
- RoomRepository: Room queries
- BookingRepository: Booking queries with custom methods
- InventoryRepository: Inventory with locking & updates
- UserRepository: User lookups
- GuestRepository: Guest management
- HotelMinPriceRepository: Optimized pricing queries
- HotelContactInfoRepository: Contact information
```

#### 📦 Entity Models
```
8 JPA Entities:
- User: User accounts with roles
- Hotel: Hotel properties
- Room: Room types
- Booking: Guest bookings
- Guest: Guest details
- Inventory: Daily room inventory
- HotelContactInfo: Contact details
- HotelMinPrice: Optimized pricing view
```

---

## 🚀 Installation & Setup

### Prerequisites
- Java 21+
- Maven 3.6+
- PostgreSQL 12+
- Stripe account with API keys
- Git

### Step 1: Clone Repository
```bash
git clone https://github.com/yourusername/StaySphere-Backend.git
cd HotelManagementApp
```

### Step 2: Configure Database
Create PostgreSQL database:
```sql
CREATE DATABASE airBnb;
```

### Step 3: Set Environment Variables
Create a `.env` file or set system environment variables:
```bash
export JWT_SECRET_KEY="your-secret-key-min-32-chars"
export STRIPE_SECRET_KEY="sk_test_your_stripe_key"
export STRIPE_WEBHOOK_SECRET="whsec_your_webhook_secret"
```

### Step 4: Update Application Properties
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/airBnb
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
jwt.secretKey=${JWT_SECRET_KEY}
stripe.secret.key=${STRIPE_SECRET_KEY}
stripe.webhook.secret=${STRIPE_WEBHOOK_SECRET}
frontend.url=http://localhost:3000
```

### Step 5: Build & Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# The API will be available at:
# http://localhost:8080/api/v1
# Swagger UI: http://localhost:8080/api/v1/swagger-ui.html
```

---

## 📡 API Endpoints

### Authentication Endpoints

```
POST   /auth/signup
       Register a new user account
       
POST   /auth/login
       Login with email and password
       Returns: JWT access token
       
POST   /auth/refresh
       Refresh access token using refresh token (from cookie)
```

### Hotel Management Endpoints (Admin Only)

```
POST   /admin/hotels
       Create a new hotel
       
GET    /admin/hotels
       Get all hotels owned by admin
       
GET    /admin/hotels/{hotelId}
       Get specific hotel details
       
PUT    /admin/hotels/{hotelId}
       Update hotel information
       
DELETE /admin/hotels/{hotelId}
       Delete a hotel
       
PATCH  /admin/hotels/{hotelId}
       Activate a hotel (creates year-long inventory)
       
GET    /admin/hotels/{hotelId}/bookings
       Get all bookings for a hotel
       
GET    /admin/hotels/{hotelId}/reports
       Generate booking reports with revenue analytics
```

### Room Management Endpoints (Admin Only)

```
POST   /admin/hotels/{hotelId}/rooms
       Create a new room in hotel
       
GET    /admin/hotels/{hotelId}/rooms
       Get all rooms in hotel
       
GET    /admin/hotels/{hotelId}/rooms/{roomId}
       Get specific room details
       
PUT    /admin/hotels/{hotelId}/rooms/{roomId}
       Update room information
       
DELETE /admin/hotels/{hotelId}/rooms/{roomId}
       Delete a room
```

### Inventory Management Endpoints (Admin Only)

```
GET    /admin/inventory/rooms/{roomId}
       Get all inventory records for a room
       
PATCH  /admin/inventory/rooms/{roomId}
       Update inventory (surge factor, closure status)
```

### Hotel Browse & Search Endpoints (Public)

```
POST   /hotels/search
       Search hotels by city, date range, and room count
       Returns paginated results with pricing
       
GET    /hotels/{hotelId}/info
       Get detailed hotel information with rooms
```

### Booking Endpoints (Authenticated Users)

```
POST   /bookings/init
       Initialize a new booking
       
POST   /bookings/{bookingId}/addGuests
       Add guest details to booking
       
POST   /bookings/{bookingId}/payments
       Initiate Stripe checkout session
       
POST   /bookings/{bookingId}/cancel
       Cancel confirmed booking (with refund)
       
POST   /bookings/{bookingId}/status
       Check booking status
```

### User Profile Endpoints (Authenticated Users)

```
PATCH  /users/profile
       Update user profile information
       
GET    /users/profile
       Get current user profile
       
GET    /users/myBookings
       Get all bookings for current user
```

### Guest Management Endpoints (Authenticated Users)

```
GET    /users/guests
       Get all saved guest profiles
       
POST   /users/guests
       Add a new guest profile
       
PUT    /users/guests/{guestId}
       Update guest profile
       
DELETE /users/guests/{guestId}
       Delete guest profile
```

### Payment Webhook Endpoints

```
POST   /webhook/payment
       Stripe webhook for payment confirmation
       Called by Stripe when payment completes
```

---

## 🗄️ Database Schema

### Key Tables

#### users
```sql
- id (PK): Long
- email: String (UNIQUE)
- password: String (hashed)
- name: String
- gender: Enum (MALE, FEMALE, OTHER)
- dateOfBirth: LocalDate
- roles: Set<Role>
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### hotels
```sql
- id (PK): Long
- name: String
- city: String
- owner_id (FK): Long
- photos: String[] (JSON)
- amenities: String[] (JSON)
- active: Boolean
- contactInfo_id (FK): Long
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### rooms
```sql
- id (PK): Long
- hotel_id (FK): Long
- type: String
- basePrice: BigDecimal
- photos: String[] (JSON)
- amenities: String[] (JSON)
- totalCount: Integer
- capacity: Integer
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### bookings
```sql
- id (PK): Long
- user_id (FK): Long
- hotel_id (FK): Long
- room_id (FK): Long
- checkInDate: LocalDate
- checkOutDate: LocalDate
- roomsCount: Integer
- amount: BigDecimal
- bookingStatus: Enum (RESERVED, GUESTS_ADDED, PAYMENTS_PENDING, CONFIRMED, CANCELLED)
- paymentSessionId: String (Stripe)
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### inventory
```sql
- id (PK): Long
- room_id (FK): Long
- hotel_id (FK): Long
- date: LocalDate
- bookedCount: Integer
- reservedCount: Integer
- totalCount: Integer
- surgeFactor: BigDecimal
- price: BigDecimal (basePrice * surgeFactor)
- closed: Boolean
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### guests
```sql
- id (PK): Long
- user_id (FK): Long
- name: String
- gender: Enum
- age: Integer
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

#### booking_guest (Join Table)
```sql
- booking_id (FK): Long
- guest_id (FK): Long
```

---

## ⚙️ Configuration

### Application Properties
```properties
# Server
server.servlet.context-path=/api/v1
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/airBnb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secretKey=${JWT_SECRET_KEY}
jwt.access.expiry=600000          # 10 minutes
jwt.refresh.expiry=15552000000    # 180 days

# Stripe
stripe.secret.key=${STRIPE_SECRET_KEY}
stripe.webhook.secret=${STRIPE_WEBHOOK_SECRET}

# Frontend
frontend.url=http://localhost:8080
```

### Environment Variables Required
```bash
JWT_SECRET_KEY              # Min 32 characters
STRIPE_SECRET_KEY           # From Stripe dashboard
STRIPE_WEBHOOK_SECRET       # From Stripe webhook settings
```

---

## 🔐 Security

### Authentication
- **JWT Tokens**: Stateless authentication
- **Access Token**: 10-minute expiry
- **Refresh Token**: 180-day expiry (stored in HTTP-only cookie)
- **Token Refresh**: `/auth/refresh` endpoint

### Authorization
- **Role-Based**: GUEST, HOTEL_MANAGER roles
- **Ownership Verification**: Users can only access their own resources
- **Endpoint Protection**: `/admin/**` endpoints require HOTEL_MANAGER role

### Password Requirements
- Minimum 8 characters
- At least one uppercase letter
- At least one lowercase letter
- At least one digit
- At least one special character (@$!%*?&)

### Data Protection
- Passwords hashed with BCrypt
- Payment information handled by Stripe (PCI compliant)
- SQL injection prevention via Prepared Statements
- CSRF protection enabled

### API Security
- Input validation on all endpoints
- Request body validation with Jakarta Bean Validation
- Global exception handling
- Rate limiting recommended (future improvement)

---

## 📈 Recent Improvements

### Version 0.0.2-HOTFIX (January 2026)

#### Critical Fixes Applied ✅
1. **Fixed Authorization Logic** - Hotel managers can now access their hotels
2. **Fixed Type Mismatch** - Corrected User vs Long comparison errors
3. **Added Null Safety** - Prevented NullPointerException in multiple endpoints
4. **Implemented Input Validation** - All DTOs now validate requests
5. **Fixed HTTP Methods** - Search now uses POST (not GET with body)
6. **Improved Error Handling** - Validation errors return 400 (not 500)
7. **Added Dependency Injection** - Fixed missing ModelMapper injection
8. **Enhanced Logging** - Better error messages and debugging info

#### New Features ✅
- Spring Boot Validation framework integrated
- DTO validation annotations on all input models
- Comprehensive exception handler for validation errors
- Field-level error reporting in API responses

#### Documentation ✅
- Detailed code review report
- Complete fix guide with testing checklist
- Updated README with comprehensive documentation

See [COMPLETE_FIX_GUIDE.md](./COMPLETE_FIX_GUIDE.md) for full details.

---

## 🧪 Testing

### Manual Testing

#### Test Authentication
```bash
# Signup
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "SecurePass123!",
    "name": "John Doe"
  }'

# Login
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "SecurePass123!"
  }'
```

#### Test Hotel Creation
```bash
curl -X POST http://localhost:8080/api/v1/admin/hotels \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Luxury Hotel",
    "city": "New York",
    "amenities": ["WiFi", "Pool", "Gym"],
    "photos": ["url1", "url2"]
  }'
```

#### Test Hotel Search
```bash
curl -X POST http://localhost:8080/api/v1/hotels/search \
  -H "Content-Type: application/json" \
  -d '{
    "city": "New York",
    "startDate": "2026-02-01",
    "endDate": "2026-02-05",
    "roomsCount": 2,
    "page": 0,
    "size": 10
  }'
```

### Automated Testing
```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate coverage report
mvn jacoco:report
```

### API Documentation
- **Swagger UI**: http://localhost:8080/api/v1/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/v1/v3/api-docs

---

## 🐳 Deployment

### Docker Setup (Recommended)

Create `Dockerfile`:
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/HotelManagementApp-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Create `docker-compose.yml`:
```yaml
version: '3.8'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: airBnb
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
  
  app:
    build: .
    environment:
      JWT_SECRET_KEY: ${JWT_SECRET_KEY}
      STRIPE_SECRET_KEY: ${STRIPE_SECRET_KEY}
    ports:
      - "8080:8080"
    depends_on:
      - db
```

### Deployment Steps
```bash
# Build Docker image
docker build -t stayphsere-backend:latest .

# Run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f app
```

### Production Considerations
- Use environment-specific properties files
- Enable HTTPS/TLS
- Configure CORS for frontend domain
- Set up database backups
- Configure application logging
- Use reverse proxy (Nginx/Apache)
- Enable monitoring and alerting
- Implement rate limiting

---

## 🤝 Contributing

### Code Standards
- Follow Google Java Style Guide
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Write unit tests for new features
- Keep methods focused and single-responsibility

### Pull Request Process
1. Create feature branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -am 'Add feature description'`
3. Push to branch: `git push origin feature/your-feature`
4. Submit pull request with description

### Bug Reports
Include:
- Detailed description of the issue
- Steps to reproduce
- Expected vs actual behavior
- Error logs/stack traces
- System information

---

## 📄 License

This project is proprietary software. All rights reserved.

---

## 📞 Support & Contact

For issues, questions, or improvements:
- Create an issue in the GitHub repository
- Contact the development team
- Review API documentation at `/api/v1/swagger-ui.html`

---

## 🚀 Future Roadmap

### Planned Features
- [ ] Real-time availability notifications
- [ ] Advanced search filters (amenities, price range)
- [ ] User reviews and ratings
- [ ] Promotional codes and discounts
- [ ] Multi-currency support
- [ ] Mobile app integration
- [ ] Analytics dashboard
- [ ] Email notifications
- [ ] SMS alerts
- [ ] Insurance options

### Performance Improvements
- [ ] Database query optimization
- [ ] Caching layer (Redis)
- [ ] API response compression
- [ ] Database indexing
- [ ] Connection pooling

### Security Enhancements
- [ ] Rate limiting
- [ ] CORS configuration
- [ ] API key management
- [ ] Audit logging
- [ ] Two-factor authentication

---

**Last Updated**: January 24, 2026  
**Version**: 0.0.2-HOTFIX  
**Status**: ✅ Production Ready (after testing)

Made with ❤️ by Yash Kumar

