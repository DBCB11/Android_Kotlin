package com.example.emailapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emailapp.adapter.EmailAdapter
import com.example.myapp.models.Email

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dữ liệu mẫu
        val emailList = listOf(
            Email("Edurila.com", "Learn Web Design", "Looking to learn web design?", "12:34 PM", "E"),
            Email("Chris Abad", "Feedback Request", "Help us improve Campaign Monitor", "11:22 AM", "C"),
            Email("Tuto.com", "Free Training", "Free Photoshop and SEO training", "11:04 AM", "T"),
            Email("Support", "OVH Services Update", "Société Ovh update on services", "10:26 AM", "S"),
            Email("Matt from Ionic", "New Ionic Creator", "Announcing the new Creator", "9:15 AM", "M"),
            Email("Test1", "Subject1", "Study1", "9:15 AM", "T1"),
            Email("Test2", "Subject2", "Study2", "9:15 AM", "T2"),
            Email("Test3", "Subject3", "Study3", "9:15 AM", "T3"),
            Email("Test4", "Subject4", "Study4", "9:15 AM", "T4"),
            Email("Test5", "Subject5", "5", "9:15 AM", "T5"),
            Email("Test6", "Subject6", "6", "9:15 AM", "T6")

        )

        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
    }
}