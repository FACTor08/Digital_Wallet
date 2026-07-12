# Digital Wallet API

A robust, simulated banking backend application that enables users to securely manage funds, perform peer-to-peer transfers, and track their digital accounts. This system leverages stateless authentication and is fully containerized for reliable environment deployment.

## 🚀 Live Demo & API Documentation
* **Swagger UI Documentation:** [Explore the API Endpoints](https://digital-wallet-rqbp.onrender.com/swagger-ui/index.html)

---

## 🛠️ Tech Stack & Key Concepts
* **Framework:** Spring Boot (Java)
* **Security:** Spring Security & JWT (JSON Web Tokens) for stateless authentication
* **Database:** PostgreSQL
* **Data Access:** Spring Data JPA
* **API Documentation:** Swagger UI / OpenAPI 3
* **Containerization:** Docker & Docker Compose

---

## 🔑 Core Features

* **User Onboarding:** Complete signup process where users register, set a secure transaction passcode, and automatically receive a unique, system-generated `accountNumber`.
* **Profile Management:** Secure endpoint configuration allowing users to upload and update their profile pictures.
* **Stateless Authentication:** Endpoint protection utilizing JWT. Secured routes require a valid bearer token in the authorization header.
* **Core Banking Transactions:**
  * **Deposits:** Simulate adding funds directly to a user wallet.
  * **Fund Transfers:** Secure peer-to-peer sending and receiving of balances between accounts.
* **Account Details:** Real-time retrieval of comprehensive account statements, transaction histories, and user profile summaries.

---

## ⚙️ Local Setup Instructions

### Prerequisites
* Java 17 or higher
* Docker and Docker Compose
* Maven

### Running Locally with Docker
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/FACTor08/Digital_Wallet.git](https://github.com/FACTor08/Digital_Wallet.git)
   cd Digital_Wallet

2. **Spin up the application and PostgreSQL container:**
   ```bash
   docker-compose up --build
3. **Access the local documentation:**
   Once the server is running, open your browser and navigate to:
   http://localhost:8080/swagger-ui/index.html
   
## 🔒 API Endpoints Preview
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| **POST** | `http://localhost:8080/user-signup` | Registers a new user | No |
| **POST** | `http://localhost:8080/login` | Authenticates credentials and returns a valid JWT bearer token | No |
| **POST** | `http://localhost:8080/deposit` | Deposits simulation funds into the authenticated wallet balance | Yes (JWT) |
| **POST** | `http://localhost:8080/transfer` | Sends funds safely from the user to a target account number | Yes (JWT) |
| **GET** | `http://localhost:8080/account-details` | Retrieves full account data, balance, and transaction information | Yes (JWT) |
| **POST** | `http://localhost:8080/profile-picture-upload` | Handles multi-part file uploads for user profile photos | Yes (JWT) |
| **POST** | `http://localhost:8080/new-wallet` | Creates new wallet & auto-generates a unique account number | Yes (JWT) |
   
