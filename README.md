# VariantGraph 🔀

A Spring Boot microservice managing **product variant relationships** for the Distributed Product Catalog Platform. Models colour, size, and price variants for each product in CatalogCache.

[![Live](https://img.shields.io/badge/Live-Render-46E3B7?style=flat-square&logo=render)](https://variant-graph.onrender.com/swagger-ui.html)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)

---

## 🚀 Live Demo

**API Docs (Swagger):** https://variant-graph.onrender.com/swagger-ui.html

> Note: Free tier — first request may take 50+ seconds to wake up.

---

## 🏗️ Architecture

```
Varenya (Next.js Frontend)
       ↓
VariantGraph (this service)
  ├── GET /api/variants/{productId}  → returns all variants for a product
  └── POST /api/variants              → creates a new variant
       ↓ baseProductId
CatalogCache → product lives in a separate service (no FK, plain Long)
```

---

## ✨ Key Features

- **Variant management** — each product can have multiple variants (colour, size, price)
- **Microservices-correct design** — `baseProductId` stored as plain `Long` (no `@ManyToOne`), since Product lives in CatalogCache's separate database
- **Swagger UI** — fully documented API at `/swagger-ui.html`
- **Supabase PostgreSQL** — Session Pooler with Hikari pool size capped at 2 for free tier compatibility
- **UptimeRobot monitoring** — `/actuator/health` pinged every 5 minutes to prevent cold starts

---

## 🔑 Trade-off Decisions

| Decision | Choice | Why |
|----------|--------|-----|
| `baseProductId` storage | Plain `Long` | No cross-service FK — Product lives in CatalogCache DB; plain ID keeps services independently deployable |
| Entity exposed directly | Controller accepts `ProductVariant` | Simple CRUD service — DTO layer adds overhead without benefit at this scale |
| Hikari pool size | max=2, min-idle=1 | Supabase free tier caps at 60 total connections across all services |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/variants/{productId}` | Get all variants for a product |
| `POST` | `/api/variants` | Create a new variant |
| `GET` | `/actuator/health` | Health check |

---

## 📦 Project Structure

```
com.anurag.variant_graph
├── config/        CorsConfig
├── controller/    VariantController
├── entity/        ProductVariant
├── repository/    ProductVariantRepository
└── service/       VariantService
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Framework | Spring Boot 3.5, Java 21 |
| Database | PostgreSQL (Supabase Session Pooler) |
| ORM | Spring Data JPA + Hibernate |
| Documentation | SpringDoc OpenAPI (Swagger UI) |
| Deployment | Render (Docker) |

---

## ⚙️ Running Locally

```bash
# Clone the repo
git clone https://github.com/comodo-18/variant-graph.git
cd variant-graph

# Run (uses H2 in-memory DB locally)
./mvnw spring-boot:run
```

App runs on `http://localhost:8082`
Swagger UI: `http://localhost:8082/swagger-ui.html`

**Seed some variants:**
```bash
curl -X POST http://localhost:8082/api/variants \
  -H "Content-Type: application/json" \
  -d '{"baseProductId":1,"variantName":"Black Mesh","colour":"black","size":"standard","price":12999.00}'
```

---

## 🔧 Environment Variables (Production)

```
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://<supabase-pooler-host>:5432/postgres
DB_USER=<supabase-user>
DB_PASSWORD=<password>
```

---

## 🔗 Related Projects

- [**CatalogCache**](https://github.com/comodo-18/catalogue-cache) — Redis caching + Kafka producer for cache invalidation
- [**InventorySync**](https://github.com/comodo-18/inventory-sync) — Redisson distributed locking for oversell prevention
- [**OrderService**](https://github.com/comodo-18/order-service) — JWT auth + order management
- [**Varenya**](https://github.com/comodo-18/varenya) — Next.js 15 storefront frontend
