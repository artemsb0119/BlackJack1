package com.example.blackjack

import com.google.gson.annotations.SerializedName

data class Rule(
    @SerializedName("name") val name: String,
    @SerializedName("text") val text: String
)
