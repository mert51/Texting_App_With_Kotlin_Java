# 📱 Messaging Application

This project is a **TCP-based messaging application** developed using
**Android Studio (Kotlin)** and **IntelliJ IDEA (Java)**.\
The application consists of an **Android client** and a **Java-based TCP
server**.\
The purpose is to allow users to send messages via an Android phone and
receive responses from the server.

------------------------------------------------------------------------

## 📂 Project Structure

    root/
    │
    ├── app/                   # Android client application (written in Kotlin)
    │   ├── src/main/java/...  
    │   │   └── com.example.mesajuygulamasi.MainActivity.kt
    │   ├── res/layout/activity_main.xml
    │   └── AndroidManifest.xml
    │
    ├── tcpjavanew.java        # Java-based TCP server (can be run with IntelliJ IDEA / cmd)
    │
    ├── build.gradle           # Gradle build configuration files
    ├── settings.gradle
    ├── gradlew / gradlew.bat  # Gradle wrapper scripts
    └── README.md              # This document

------------------------------------------------------------------------

## ⚙️ Requirements

### For Client (Android side):

-   Android Studio (latest version recommended)\
-   Minimum Android SDK: 21 (Lollipop)\
-   Android device with Internet access (or Android Emulator)

### For Server (Java side):

-   IntelliJ IDEA (or any Java IDE)\
-   Java JDK 8+\
-   An open **8081 port** (default setting)

------------------------------------------------------------------------

## 🚀 Setup and Run

### 1. Server Setup (On Computer)

1.  Open `tcpjavanew.java` file with **IntelliJ IDEA** or run it in
    terminal:

    ``` bash
    javac tcpjavanew.java
    java org.example.TOPSimulationServer
    ```

2.  You should see the following output in the console:

        TCP Server started on port 8081...

👉 This means the server is successfully running and waiting for client
connections.

------------------------------------------------------------------------

### 2. Running the Android Client

1.  Open the project in **Android Studio**.\

2.  Find the following line in `MainActivity.kt`:

    ``` kotlin
    private val serverIp = "000.000.0.000"
    ```

    🔹 Replace it with the **IP address of the computer running the
    server**.

    -   On your computer, run `ipconfig` (Windows) or `ifconfig`
        (Linux/Mac) to find your IP address.\

    -   Example:

        ``` kotlin
        private val serverIp = "192.168.1.7"
        ```

3.  Connect your phone to your computer via **USB** (or be on the same
    Wi-Fi network).\

4.  From Android Studio, install the app on your device with the **Run
    ▶** button.

------------------------------------------------------------------------

### 3. Testing Messaging

-   Open the Android application.\
-   You should see the message `"Connected to server!"`.\
-   Type a message and press the **Send** button:
    -   Your message will be sent to the server.\
    -   The server replies back with `"Server reply: ..."`.\
-   The app will display both your messages and the server's responses.

------------------------------------------------------------------------

## 📜 Example Usage Steps

1.  Start the server on the computer:

        TCP Server started on port 8081...

2.  Open the app on the Android device → `"Connected to server!"`
    message appears.\

3.  User types a message:

        Hello

4.  App displays:

        You: Hello

5.  Server replies:

        Server reply: Hello

6.  App displays:

        Server: Server reply: Hello

------------------------------------------------------------------------

## 🔐 Notes and Security

-   Internet permission is defined in the Android `Manifest` file:

    ``` xml
    <uses-permission android:name="android.permission.INTERNET" />
    ```

-   By default, `serverPort = 8081` is used. Can be changed if needed.\

-   During development/testing, it is important that the client and
    server are on the **same network** (Wi-Fi hotspot or same router).

------------------------------------------------------------------------

## 🛠️ Technologies Used

-   **Kotlin (Android)** → Client UI and TCP connection\
-   **Java (Server)** → TCP ServerSocket + multi-client support\
-   **Coroutines (Kotlin)** → Asynchronous communication\
-   **Gradle** → Project management and dependency system

------------------------------------------------------------------------

## 👨‍💻 Contribution

1.  Fork this repository.\

2.  Create a new branch:

    ``` bash
    git checkout -b feature-xyz
    ```

3.  Commit your changes:

    ``` bash
    git commit -m "Added new feature"
    ```

4.  Push to the branch:

    ``` bash
    git push origin feature-xyz
    ```

5.  Open a Pull Request. 🚀

------------------------------------------------------------------------

## 📌 Summary Setup Checklist

✅ `serverIp` → must be set to your computer's IP address.\
✅ Start the server (`tcpjavanew.java`).\
✅ Android device must be on the same network or connected via USB.\
✅ Reinstall and run the application.\
✅ Start messaging 🎉
