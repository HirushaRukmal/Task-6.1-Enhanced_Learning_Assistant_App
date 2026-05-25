# LLM-Enhanced Learning Assistant App

## Overview
The LLM-Enhanced Learning Assistant App is an Android application designed to support the way students learn and teachers guide progress. The app provides a simple learning flow that includes login, account setup, interest selection, generated learning tasks, question answering, and AI-supported learning assistance.

This application was developed for **Task 6.1D** and uses dummy data to demonstrate the required functionality. It also includes multiple LLM-powered learning utilities integrated into the user interface.

---

## Features

### 1. Login Screen
- Username field
- Password field
- Login button
- Navigation to account setup

### 2. Account Setup Screen
- Username
- Email
- Confirm Email
- Password
- Confirm Password
- Phone Number
- Create Account button

### 3. Interest Selection Screen
- Users can select learning interests such as:
  - Algorithms
  - Data Structures
  - Web Development
  - Testing
  - Mobile Development
  - Databases
- Selected interests are stored locally for later use in personalization

### 4. Home Screen
- Personalized greeting using the stored username
- Generated task card based on dummy learning data
- Navigation to task screen

### 5. Task Screen
- Displays a generated learning task
- Multiple-choice questions
- Submit button
- LLM-powered learning support

### 6. Results Screen
- Displays the user’s final score
- Shows percentage
- Highlights whether each selected answer is correct or wrong
- Displays correct answer and selected answer
- Improved answer review section with color-coded result text

---

## LLM-Powered Learning Utilities
The app includes more than two LLM-powered learning utilities, such as:

- **Generate Hint for a Question**
- **Explain Why an Answer is Correct or Incorrect**
- **Produce a Short Summary of a Lesson**
- **Create 3 Flashcards from a Topic**

These utilities are designed for learning support rather than general chat.

### Prompt and Response Display
The app clearly displays:
- the **prompt**
- the **response**
- **loading states**
- **failure/error states**

This improves transparency and usability.

---

## Technologies Used
- **Kotlin**
- **Android Studio**
- **Fragments**
- **Navigation Component**
- **RecyclerView**
- **View Binding**
- **ViewModel**
- **LiveData**
- **SharedPreferences**
- **Dummy Data Repository**

---
