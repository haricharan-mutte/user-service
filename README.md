# 🧠 First, Understand: What is the Security Context?
---
SecurityContext is a thread-local store in Spring Security that holds the details about the currently authenticated user.

When you set the Authentication object inside SecurityContext, you're telling Spring Security:

"Hey, this user is authenticated, and here's their identity and authorities (roles)."

🔐 Why Set It?
Because Spring uses SecurityContext to:

Check if the user is authenticated.

Evaluate annotations like PreAuthorize("hasRole('ADMIN')").

Perform access decisions during request processing.

So your manual logic of:

Extracting JWT,

Validating it,

Getting roles,

Creating an Authentication object (e.g. UsernamePasswordAuthenticationToken or JwtAuthenticationToken),

And setting it in SecurityContextHolder.getContext().setAuthentication(auth)
is correct and valid, and it's equivalent to what spring-boot-starter-oauth2-resource-server automates.

---
# 🌟 Centralized Application Logs Monitoring with ELK stack with Sleuth+Zipkin
ELK stands for:

Component	Purpose
Elasticsearch	A fast, scalable search engine that stores logs as structured documents
Logstash	A data processing pipeline that ingests, transforms, and sends data
Kibana	A visualization dashboard tool to explore and analyze data in Elasticsearch
Logback / Log4j	A Logging in Spring Boot apps
Spring Cloud Sleuth A Distributed tracing & correlation ID injection 
Zipkin A Visual trace viewer for distributed systems

✅ Together, ELK helps you collect, store, search, and visualize logs across all your microservices.

---
# 🎯 Why Use ELK in Microservices?
Centralized Logging: Aggregate logs from many services into one place.

Full-text search on logs (Elasticsearch).

Real-time monitoring and alerting.

Visual dashboards for log metrics (via Kibana).

Troubleshooting & Root Cause Analysis becomes easier.

---
# 🧱 ELK Architecture Overview with Spring Boot Microservices
🔁 Data Flow: Spring Boot Microservices → Log Shipper (Logstash/Filebeat) → Elasticsearch → Kibana

# 🧩 1. Step-by-Step Setup: Sleuth + Zipkin

<!-- Spring Cloud Sleuth for trace IDs -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

<!-- Zipkin for collecting traces -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>

# 🔹 2. Configure Application YAML (Each Service)

   spring:
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      probability: 1.0  # 100% tracing (not recommended for prod)

# 🔹 3. Run Zipkin Server
You can run it using Docker: docker run -d -p 9411:9411 openzipkin/zipkin

# 4. How It Works
Spring Sleuth assigns a trace ID and span ID to every request.

These IDs are passed to downstream services automatically.

All services log messages with the same trace ID.

Zipkin collects and visualizes these traces.

# 🔹 5. Sample Log Output (With Sleuth)

[TRACE_ID, SPAN_ID, exportable]  --- Message
[9f1b03a7e8fcb6fa,9f1b03a7e8fcb6fa,true]  --- Request received at gateway
[9f1b03a7e8fcb6fa,3ac1f5bd99f86b12,true]  --- Forwarded to auth-service

# 📌 Optional: Send logs to ELK stack
If needed, we can:

Send logs to Logstash (or Fluentd)

Store in Elasticsearch

Visualize in Kibana

This requires setting up a centralized logging system separately.





