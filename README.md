# Item Processing Service

This is a Spring Boot application that provides an API to process a list of items from an in-memory H2 database.

## Features

- Seeds an in-memory H2 database with sample `Item` data on startup.
- Provides a `GET /process` endpoint to retrieve processed item data.
- Calculates a `score` for each item based on its category.
- Returns the top N items by score, the total item count, and the average score.

## Requirements

- Java 17 or later
- Maven 3.6 or later

## How to Run

1.  **Clone the repository or save the project files.**

2.  **Build and Run the application using Maven:**

    Open your terminal, navigate to the project's root directory (where `pom.xml` is located), and run the following command:

    ```bash
    # For Linux/macOS
    ./mvnw spring-boot:run

    # For Windows (using PowerShell)
    .\mvnw.cmd spring-boot:run
    
    # If you have Maven installed globally
    mvn spring-boot:run
    ```

3.  **The application will start on `http://localhost:8080`.**

    You will see log output indicating that the application has started and the database has been seeded.

## How to Use

### API Endpoint: `GET /process`

-   **URL:** `http://localhost:8080/process`
-   **Method:** `GET`
-   **Query Parameters:**
    -   `top_n` (optional, integer): The number of top-scoring items to return. Defaults to `3`.

#### Example `curl` Commands:

-   **Get the top 2 items:**

    ```bash
    curl "http://localhost:8080/process?top_n=2"
    ```

    **Expected Response:**
    ```json
    {
      "topItems": [
        {
          "id": 4,
          "name": "Dragon's Breath",
          "category": "alpha",
          "value": 250.0,
          "score": 250.0
        },
        {
          "id": 2,
          "name": "Shield of Ages",
          "category": "beta",
          "value": 120.0,
          "score": 144.0
        }
      ],
      "count": 6,
      "averageScore": 119.0
    }
    ```

-   **Get the top 3 items (default):**

    ```bash
    curl "http://localhost:8080/process"
    ```

    **Expected Response:**
    ```json
    {
      "topItems": [
        {
          "id": 4,
          "name": "Dragon's Breath",
          "category": "alpha",
          "value": 250.0,
          "score": 250.0
        },
        {
          "id": 2,
          "name": "Shield of Ages",
          "category": "beta",
          "value": 120.0,
          "score": 144.0
        },
        {
          "id": 1,
          "name": "Sword of Valor",
          "category": "alpha",
          "value": 100.0,
          "score": 100.0
        }
      ],
      "count": 6,
      "averageScore": 119.0
    }
    ```

### H2 Database Console

You can access the in-memory H2 database console to view the `ITEM` table.

-   **URL:** `http://localhost:8080/h2-console`
-   **JDBC URL:** `jdbc:h2:mem:itemdb`
-   **Username:** `sa`
-   **Password:** `password`

Click "Connect" to access the database. You can then run SQL queries like `SELECT * FROM ITEM;`.