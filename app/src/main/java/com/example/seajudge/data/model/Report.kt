package com.example.seajudge.data.model

data class Report(
    val id: Int,
    val username: String,
    val reporter: String,
    val image: String,
    val violation: String,
    val location: String,
    val date: String,
    val time: String
)
