import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import OpenAI from "openai";

dotenv.config();

const app = express();
app.use(cors());
app.use(express.json());

const client = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY,
});

app.get("/health", (req, res) => {
  res.json({ ok: true, message: "Backend is running" });
});

async function runPrompt(prompt) {
  const response = await client.responses.create({
    model: "gpt-5.5",
    input: prompt,
  });

  return response.output_text;
}

function handleOpenAiError(error, res, fallbackMessage) {
  console.error(error);

  if (error?.status === 429 || error?.code === "insufficient_quota") {
    return res.status(429).json({
      error: "OpenAI quota exceeded. Please check billing or API credits.",
      prompt: "",
      response: "",
      fallback: fallbackMessage
    });
  }

  return res.status(500).json({
    error: "LLM request failed.",
    prompt: "",
    response: "",
    fallback: fallbackMessage
  });
}

app.post("/llm/hint", async (req, res) => {
  try {
    const { question } = req.body;

    const prompt = `Generate a short learning hint for this question without giving the final answer directly:\n\n${question}`;
    const output = await runPrompt(prompt);

    res.json({
      prompt,
      response: output
    });
  } catch (error) {
    handleOpenAiError(
      error,
      res,
      "Hint: Focus on the key concept in the question and remove obviously incorrect options first."
    );
  }
});

app.post("/llm/explain", async (req, res) => {
  try {
    const { question, correctAnswer, selectedAnswer } = req.body;

    const prompt = `Explain why the correct answer is correct and whether the student's selected answer is correct or incorrect.

Question: ${question}
Correct Answer: ${correctAnswer}
Selected Answer: ${selectedAnswer}

Keep the explanation short and student-friendly.`;

    const output = await runPrompt(prompt);

    res.json({
      prompt,
      response: output
    });
  } catch (error) {
    handleOpenAiError(
      error,
      res,
      `Explanation: The correct answer is "${req.body.correctAnswer}". Your selected answer was "${req.body.selectedAnswer}".`
    );
  }
});

app.post("/llm/summary", async (req, res) => {
  try {
    const { topic } = req.body;

    const prompt = `Produce a short lesson summary for the topic: ${topic}. Keep it clear and easy for a student to revise quickly.`;
    const output = await runPrompt(prompt);

    res.json({
      prompt,
      response: output
    });
  } catch (error) {
    handleOpenAiError(
      error,
      res,
      `Summary: ${req.body.topic} is an important learning topic. Review the key idea, one practical use, and one common mistake.`
    );
  }
});

app.post("/llm/flashcards", async (req, res) => {
  try {
    const { topic } = req.body;

    const prompt = `Create exactly 3 flashcards for the topic: ${topic}.
Format:
1. Front: ...
   Back: ...
2. Front: ...
   Back: ...
3. Front: ...
   Back: ...`;

    const output = await runPrompt(prompt);

    res.json({
      prompt,
      response: output
    });
  } catch (error) {
    handleOpenAiError(
      error,
      res,
      `Flashcard 1: Define ${req.body.topic}\nFlashcard 2: Give one example of ${req.body.topic}\nFlashcard 3: State one common misconception about ${req.body.topic}`
    );
  }
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});