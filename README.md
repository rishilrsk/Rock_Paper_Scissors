# Rock Paper Scissors - Java Game

Welcome to the Rock Paper Scissors project! This is a simple, visual, desktop game built with Java. 

This guide is designed for **complete beginners** to help you understand how the code is organized and how the game actually works behind the scenes.

## Features
- **Sleek Dark Theme:** Modern, high-contrast user interface.
- **3D Interactive Buttons:** The buttons depress and visually click when you press them.
- **Victory Animations:** Winning a round triggers a pulsing, color-shifting text animation.
- **Score Tracking:** Tracks the player's score versus the computer.
- **Reset and Quit:** Easy controls to reset the game logic or quit the desktop application entirely.

## How the Project is Structured
The code is divided into three separate parts (or "classes"). This makes it easy to understand and organize. Imagine building a house: you have the foundation, the structure, and the paint.

Our three parts are:
1. **`Main.java`** (The Launcher)
2. **`GameLogic.java`** (The Brain/Rules)
3. **`GameUI.java`** (The Look and Feel)

Let's break them down.

---

### 1. `Main.java` - The Launcher
* **Where it is:** `src/rps/Main.java`
* **What it does:** This is the starting point of the application. When you run the project, the computer looks for this file first.
* **How it works:** It simply says, "Hey Java, create the visual Window (`GameUI`) and show it on the screen." That's its only job!

### 2. `GameLogic.java` - The Brains
* **Where it is:** `src/rps/logic/GameLogic.java`
* **What it does:** This file doesn't care about colors, buttons, or windows. It only cares about the rules of Rock-Paper-Scissors. It keeps track of the score and decides who wins.
* **How it works:** 
  - **Random Moves:** When the player picks an option, this file generates a random choice for the computer using a tool called `Random`. It picks randomly between Rock, Paper, and Scissors.
  - **Who Wins?** It has a set of rules (e.g., Rock beats Scissors, Paper beats Rock, Scissors beat Paper). It looks at what you picked and what the computer picked, and returns "WIN", "LOSE", or "DRAW".
  - **Scores:** It adds `1` to whoever wins the round.

### 3. `GameUI.java` - The Look and Feel (User Interface)
* **Where it is:** `src/rps/ui/GameUI.java`
* **What it does:** This file draws the actual window you see. It creates the buttons, text, background colors, and updates the screen when you play.
* **How it works:** 
  - It uses a Java tool called **Swing**, which provides building blocks for apps (like buttons and text labels).
  - It handles **Clicking:** When you click the "Rock" button, it "listens" to that click and tells the `GameLogic.java` file, "Hey, the player picked Rock! Play a round!"
  - It takes the result ("WIN", "LOSE", or "DRAW") back from the brain, updates your score on the screen, and triggers **animations** using a Java `Timer` if you win.
  - It achieves the **3D effect** by drawing custom borders that dynamically shift when your mouse clicks!

---

## The Step-by-Step Flow of a Game Round

Here is exactly what happens in the code when you play:

1. **You Click a Button:** You click "Rock" on the screen. The button physically pushes "down" visually (handled by `GameUI`).
2. **The Logic Takes Over:** `GameUI` asks `GameLogic` to figure out the computer's move.
3. **Computer Picks:** `GameLogic` randomly selects "Paper".
4. **Determine Winner:** `GameLogic` compares your "Rock" to the computer's "Paper". Paper wraps Rock, so the computer wins! The computer score increases by 1.
5. **Update Screen:** `GameLogic` tells `GameUI` that the computer won. `GameUI` updates the text to say "You Lose!", shows the computer's choice, and updates the scoreboard text.
   - *(If you had won, it would tell the Timer to start pulsing the Victory text!)*

## Summary for Beginners
If you are explaining this project:
* Tell them the layout uses **Object-Oriented Programming (OOP)**. This means we split the code into "Objects" that handle specific jobs (The UI handles visuals, the Logic handles rules).
* By keeping the "Brain" (`GameLogic`) separate from the "Visuals" (`GameUI`), the code is much easier to read, fix, and update later on!
