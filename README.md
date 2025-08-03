__Todo App__
A simple Android Todo application built using Jetpack Compose, following the MVVM architecture. The app integrates with the DummyJSON API for login and todo creation endpoints, utilizes Dagger Hilt for dependency injection, and RoomDB for local caching.
Features

Login Screen: Authenticate users using the DummyJSON login endpoint.
Home Screen: Displays a list of saved todos, with options to:
Edit a todo by clicking on it.
Delete a todo by clicking the bin icon.
Mark a todo as complete by clicking the "Uncomplete" text on the todo card.


Todo Creation Screen: Add new todos using the DummyJSON todo creation endpoint.

Tech Stack

Jetpack Compose: For building the UI.
MVVM Architecture: For clean separation of concerns.
Dagger Hilt: For dependency injection.
RoomDB: For local caching of todos.
DummyJSON API: For login and todo creation functionalities.

Setup

Clone the repository:git clone https://github.com/muhammadrabilu/To-do.git


Open the project in Android Studio.
Ensure you have an active internet connection for API calls to DummyJSON.
Build and run the app on an emulator or physical device.

Usage

Login: Enter credentials to authenticate via the DummyJSON API.
View Todos: On the home screen, see a list of cached todos.
Manage Todos:
Click a todo to edit its details.
Click the bin icon to delete a todo.
Click "Uncomplete" to mark a todo as complete.


Add Todo: Navigate to the todo creation screen to add a new todo.

Dependencies

Jetpack Compose
Dagger Hilt
Room Database
Retrofit (for API calls)
DummyJSON API

Notes

Ensure a stable internet connection for API interactions.
Todos are cached locally using RoomDB for offline access.
The app follows MVVM to ensure scalability and maintainability.
