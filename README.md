
# Studio Management API

A simple Studio Management API built with Spring Boot. This project allows users to manage class bookings. It includes endpoints to create classes, book classes, and view all classes and bookings. 

## Features
- Create classes with a start date, end date, and capacity.
- Book a class on a specific date.
- View all classes and bookings.

## Technologies Used
- Java 17
- Spring Boot 3.3.5
- Maven 3.6 or higher

## Project Structure
- **Controllers**: Handles HTTP requests and maps them to the appropriate service layer.
- **Services**: Contains business logic, including booking validation and availability checks.
- **Repositories**: An in-memory data store for classes and bookings.
- **Models**: Contains the data structures `StudioClass` and `BookingRequest`.
- **Exceptions**: Custom exception handling for improved error feedback.


### Running the Application
1. **Clone the Repository**: 
   git clone https://github.com/GUMMADIDALA-DILEEPCHANDU/studio-management.git
   cd studio-management
2. **Build the Application**:
   mvn clean install
3. **Run the Application**:
   mvn spring-boot:run

   The application will start on `http://localhost:8080`.

### API Endpoints

- **All the endpoint are there on `Golfox.postman_collection`.**

- **Create Class**  
  `POST /api/classes`  
  Request body: 
  ```json
  {
    "className": "Pilates",
    "startDate": "2024-12-01",
    "endDate": "2024-12-20",
    "capacity": 20
  }
  ```

- **Book Class**  
  `POST /api/bookings`  
  Request body: 
  ```json
  {
    "name": "Dileep",
    "className": "Pilates",
    "date": "2024-12-02"
  }
  ```

- **Get All Classes**  
  `GET /api/classes`

- **Get All Bookings**  
  `GET /api/bookings`


### Error Handling
If a booking is attempted outside of the scheduled dates of a class, an error response with a 400 status code and an appropriate message will be returned.
If a person tries to book the same class more than once on the same day, they will receive a 400 error with a message stating that the booking already exists for that date.
A user can book multiple classes on the same day, but only one booking per class per day is allowed.

- **All the endpoint are there on `Golfox.postman_collection`.**


+-------------------------+       +-------------------------+       +------------------------+
|                         |       |                         |       |                        |
|     User (Client)       +------>+     ClassController     +------>+    ClassService        |
|                         |       |                         |       |                        |
+-------------------------+       +-------------------------+       +------------------------+
                                                                                |
                                                                                |
                                                                                V
                                                                        +------------------------+
                                                                        |                        |
                                                                        |   InMemoryRepository   |
                                                                        |                        |
                                                                        +------------------------+
                                                                                |
                                                                                |
                                                                                V
                                                                        +------------------------+
                                                                        |                        |
                                                                        |      StudioClass       |
                                                                        |                        |
                                                                        +------------------------+
                                                                                |
                                                                                |
                                                                                V
                                                                        +------------------------+
                                                                        |                        |
                                                                        |   BookingRequest       |
                                                                        |                        |
                                                                        +------------------------+

