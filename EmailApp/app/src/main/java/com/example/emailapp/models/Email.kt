package com.example.myapp.models

data class Email(
    val sender: String,       // Tên người gửi
    val subject: String,      // Tiêu đề email
    val snippet: String,      // Nội dung tóm tắt
    val time: String,         // Thời gian gửi
    val avatar: String        // Chữ cái đại diện cho người gửi
)
