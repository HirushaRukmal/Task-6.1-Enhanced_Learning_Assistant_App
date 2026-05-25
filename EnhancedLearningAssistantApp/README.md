# Enhanced Learning Assistant App

Android app for Task 6.1D with a real backend API integration pattern.

## Modules
- `EnhancedLearningAssistantApp/` Android client
- `backend/` Express server that calls OpenAI securely with environment variables

## Android setup
1. Open `EnhancedLearningAssistantApp` in Android Studio.
2. Start the backend server first.
3. If using Android Emulator, the app points to `http://10.0.2.2:3000`.
4. Run the app.

## Backend setup
See `backend/README.md`.

## LLM features included
- Generate hint
- Explain why answer is correct/incorrect
- Produce lesson summary
- Create 3 flashcards

The app shows prompt + response, loading state, and failure state.
