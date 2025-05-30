# 🎉 Online Auction System

> **Final Assignment** – A robust, enterprise-grade Online Auction System built with Jakarta EE, packaged as an EAR, leveraging ActiveMQ for async messaging and tested using JMeter and VisualVM.

---

## 🏗️ Project Overview

This system enables users to list items for auction, place bids in real time, and receive notifications when auctions conclude. Key features:

* **Modular Architecture:** Core entities, EJB business logic, web APIs, and EAR packaging.
* **Asynchronous Messaging:** Notify winners and sellers via JMS queue (ActiveMQ).
* **Performance Testing:** Validate scalability with Apache JMeter and profile JVM with VisualVM.

---

## 📦 Architecture & Modules

The repository follows an **Enterprise Archive (EAR)** structure:

```
Online-Auction-System/
├── core/         # JAR: JPA entities (User, Item, Auction, Bid), DTOs, DAOs
├── ejb/          # EJB JAR: Stateless beans (AuctionServiceBean, BidServiceBean), JMS producers
├── web/          # WAR: JAX-RS REST endpoints under /api (UserResource, AuctionResource, BidResource)
├── ear/          # EAR: Aggregates core, ejb, web; contains application.xml & JMS resource descriptors
├── pom.xml       # Parent POM defining modules & dependency management
├── .gitignore
└── README.md     # This document
```

### Core Module (`core`)

* **Entities:** `User`, `Item`, `Auction`, `Bid` with JPA annotations.
* **DAOs:** Generic CRUD operations via `EntityManager`.
* **DTOs:** Data transfer objects for REST payloads.

### EJB Module (`ejb`)

* **`AuctionServiceBean`** (`@Stateless`): Create auctions, close auctions.
* **`BidServiceBean`** (`@Stateless`): Place bids with concurrency control.
* **JMS Producer:** Sends messages to notify users when auctions close.

  ```java
  @Resource(lookup = "jms/AuctionConnectionFactory")
  private ConnectionFactory cf;
  @Resource(lookup = "jms/AuctionQueue")
  private Queue queue;

  public void notifyAuctionEnd(Auction auction) {
      try (JMSContext context = cf.createContext()) {
          context.createProducer()
                 .send(queue, auction.getId().toString());
      }
  }
  ```

### Web Module (`web`)

* **JAX-RS Resources:**

    * `UserResource` for registration and login.
    * `AuctionResource` to list/create auctions.
    * `BidResource` to place bids (`POST /api/auctions/{id}/bids`).
* **Security:** Basic authentication (EJB interceptor or Jakarta Security).

### EAR Module (`ear`)

* **`application.xml`:** Defines modules and context roots.
* **JMS Resources:** Included in `META-INF` for server configuration:

  ```xml
  <resource-ref>
    <res-ref-name>jms/AuctionQueue</res-ref-name>
    <res-type>javax.jms.Queue</res-type>
  </resource-ref>
  ```

---

## 🚦 ActiveMQ Integration

**Apache ActiveMQ** is our JMS broker for reliable, asynchronous messaging.

1. **Setup Broker:**

   ```bash
   brew install activemq            # MacOS, or download ZIP for Windows/Linux
   activemq start                   # Starts broker at tcp://localhost:61616
   ```
2. **Define Resources in `broker.xml`:**

   ```xml
   <queue physicalName="AuctionQueue" />
   ```
3. **Producer Example:** See `ejb/src/main/java/.../AuctionServiceBean.java` above.
4. **Consumer Example:** A separate EJB MDB listening on `jms/AuctionQueue`:

   ```java
   @MessageDriven(activationConfig = {
     @ActivationConfigProperty(propertyName="destination", propertyValue="AuctionQueue"),
     @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")
   })
   public class AuctionNotificationMDB implements MessageListener {
     public void onMessage(Message msg) {
       String auctionId = msg.getBody(String.class);
       // Lookup winning bid and email user...
     }
   }
   ```

---

## 🧪 Testing & Profiling

### 📊 Apache JMeter

Load-test REST endpoints to simulate high traffic:

1. **Design Test Plan:** Thread Group → HTTP Requests → Listeners.
2. **Sample Command (Non-GUI):**

   ```bash
   jmeter -n -t AuctionTest.jmx \
         -l AuctionResults.jtl -e -o AuctionReport
   ```
3. **Key Metrics:** Throughput, Response Time, Error Rate.
4. **Importance:** Validates scalability, identifies bottlenecks.

### 🖥️ VisualVM

Profile JVM performance in real time:

1. **Launch VisualVM:** bundled with JDK or standalone.
2. **Attach** to running App Server process.
3. **Monitor:** CPU, Heap, Threads, and perform **Heap Dumps**.
4. **Profiler:** Drill down into hot methods in EJBs and DAOs.
5. **Importance:** Detect memory leaks, thread contention, and heavy CPU usage.

---

## 🚀 Deployment & Quick Start

1. **Build All Modules:**

   ```bash
   mvn clean install
   ```
2. **Deploy EAR:**

   ```bash
   asadmin deploy ear/OnlineAuctionSystem.ear
   ```
3. **Start ActiveMQ** (see above).
4. **Access API:** `http://localhost:8080/auction-web/api`

---

## 📄 License & Contact

MIT © 2025 Tharindu714
Questions? Email: [contact@example.com](mailto:contact@example.com)

---

> Ready to bid? 🏷️ Happy coding and good luck on your final assignment!
