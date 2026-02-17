package com.example.threadlab101

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiHelper {

    // REPLACE THIS WITH YOUR ACTUAL KEY
    private val apiKey = "AIzaSyBwgIvWwIc3S1J-VbEL63K_zC7zaniI-dA"

    // Use "gemini-1.5-flash" for speed, or "gemini-pro" for smarter answers
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey
    )

    // Function to get a simple text response
    suspend fun getOutfitSuggestion(style: String, occasion: String): String? {
        return withContext(Dispatchers.IO) {
            val prompt = "I am building a fashion app. Suggest a cool outfit combination " +
                    "(Top and Bottom colors only) for a $style style suitable for a $occasion. " +
                    "Keep it very short, under 15 words."

            try {
                val response: GenerateContentResponse = generativeModel.generateContent(prompt)
                response.text
            } catch (e: Exception) {
                e.printStackTrace()
                "Error generating outfit. Try again."
            }
        }
    }
}
