# 🎲 Tic-Tac-Toe Android App 

**🚦 Getting Started**

A modern, robust Tic-Tac-Toe game built for Android. This project serves as a demonstration of **Clean Architecture**, **Test-Driven Development (TDD)**, and professional **Android best practices**.

**Prerequisites :**
Android Studio Hedgehog or newer.

JDK 17+.

**Installation**
Clone the repository:

Bash
git clone ([https://github.com/2026-DEV2-026/TicTacToeApplication](https://github.com/2026-DEV2-026/TicTacToeApplication))
Open the project in Android Studio.

Click File > Sync Project with Gradle Files.
Wait for the sync to complete. Note: A successful sync is required for Hilt to generate the necessary Dagger classes.

📱 Running the App

**Select a Device**
 - Connect a physical Android device via USB (ensure USB Debugging is on).
Or, open the Device Manager in Android Studio and launch an Emulator.

**Build and Run**
- Click the green Run icon (▶️) in the top toolbar.

**📜 Rules**
- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
- One player has three in a row, horizontally, vertically or diagonally
- All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

# 🧠 The Logic
I solved Tic-Tac-Toe using a Functional and State-Driven approach. Instead of changing the board directly, we treat the game as a series of "snapshots" triggered by user actions.

1. Immutable Board: Every move creates a brand-new board rather than modifying the old one, preventing data bugs.

2. Pattern Matching: The winner is found by checking the board against 8 hardcoded winning coordinate sets (rows, columns, diagonals).

3. Action Pipeline: User clicks are sent as "Actions" into a pipeline. A single function takes the Old State + New Action and spits out a New State.

4. Reactive UI: The screen simply watches the state. If the state says isGameOver, the UI automatically shows the reset button.

  <img width="280" height="600" alt="Winner_1" src="https://github.com/user-attachments/assets/351579ce-7562-4448-9f8c-956a4e79c1fd" />
  <img width="280" height="600" alt="NewGame" src="https://github.com/user-attachments/assets/9f6ab4b6-2377-4f8d-9b11-7002bc70097f" />
  <img width="280" height="600" alt="Draw" src="https://github.com/user-attachments/assets/ba9b213c-b954-4401-8e0f-764c5054bad9" />
  <img width="280" height="600" alt="Winner_2" src="https://github.com/user-attachments/assets/172767ec-5651-4b19-87fb-5b1e403c433f" />

# 🚀 Key Features
**TDD Approach**: Core game logic built with a "test-first" mentality using JUnit 4 and Jupiter.

**Modern UI**: Built entirely with Jetpack Compose for a declarative and responsive interface.

**Clean Architecture**: Strict separation of concerns across Domain, Data, and UI layers.

**Dependency Injection**: Powered by Hilt for scalable and maintainable code.

**Unit Testing**: Robust testing for ViewModels and Domain logic using MockK and Turbine.

# 🛠️ Tech Stack & Dependencies
This project leverages the latest Android libraries and tools defined in a centralized version catalog (libs.versions.toml):

Language: Kotlin 2.0.21.

UI Framework: Jetpack Compose (BOM 2024.09.00).

Dependency Injection: Hilt 2.51.1.

Build Tool: Android Gradle Plugin (AGP) 8.13.2.

Unit Testing: JUnit 4 & JUnit Jupiter (5.10.2).

Mocking: MockK.

Flow Testing: Turbine.

# 🏗️ Architecture
The project follows a Clean Architecture pattern to ensure the code is modular, scalable, and easy to test:

**1. Domain Layer**
Contains the core business logic and entities.

GameEvaluator: Logic for determining winners or draw conditions.

Board: Immutable representation of the 3x3 grid.

**2. UI Layer (Presentation)**
Handles the user interface and state observation.

TicTacToeViewModel: Manages UI state using Kotlin Flows and handles user intent.

TicTacToeScreen: Declarative UI components built with Compose.

TicTacToeState: Data class representing the current game state.

**3. DI Layer**
Hilt modules provide dependencies across the application, ensuring a decoupled design.

# 📁 Directory Hierarchy
app/src/main/java/com/example/tictactoekata/
```text
├── 📂 domain/                 # Pure Kotlin - Core Logic & Entities
│   ├── Board.kt               # 3x3 Grid management & move validation
│   ├── Cell.kt                # Atomic unit representing a board space
│   ├── GameEvaluator.kt       # Logic for winner and draw detection
│   └── Player.kt              # Enum for X and O players
│  
├── 📂 ui/                     # Presentation Layer (Jetpack Compose)
│   ├── 📂 theme/              # Material3 color, type, and shape definitions
│   ├── 📂 viewmodel/          # State management & UDF handling
│   │   └── GameViewModel.kt   # Processes GameActions into new States
│   ├── MainActivity.kt        # Entry point of the application
│   ├── TicTacToeState.kt      # Immutable state holder for the UI
│   └── TicTacToeScreen.kt     # Main UI Composables and Previews
│
└── TicTacToeApplication.kt    # Hilt Application class for DI setup

app/src/test/                  # Local Unit Tests (Logic testing)
└── 📂 domain/                 # Tests for Board, Evaluator, and Logic

```
