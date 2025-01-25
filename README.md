# Chatter Video Conference Backend

This is the backend for the video conferencing application, designed to support seamless audio and video communication among multiple users. It is built using **Spring Boot** and provides the necessary API endpoints to manage the video streams and user interactions during the conference.

## Features

- **Audio and Video Streams**: Allows seamless audio and video functionality for multiple users.
- **No Login/Signup Required**: Users can join meetings by simply using a meeting ID.
- **Participant Management**: Displays participant list during the session.
- **Real-Time Updates**: Updates the meeting state in real-time for all participants.

## Requirements

Before you can run the backend, make sure you have the following installed:

- Java 17 or above
- Gradle (for building the project)
- Websocket

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Tosin-dotcom/chatter-backend.git
   ```
2. Navigate to the project directory:
    ```
    cd chatter-backend
    ```

3. Install the dependencies and build the project:
    ```
   gradlew build
   ```
   
## Running the Application
1. Start the backend server:
   ```
   ./gradlew bootRun
   ```
   
2. The application will be running at:
   ```
   http://localhost:8080
   ```
   
## Technologies Used
   - Spring Boot: Backend framework
   - WebSockets: For real-time communication

