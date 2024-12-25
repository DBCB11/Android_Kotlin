package com.example.managefiles

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class DirectoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_directory)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val path = intent.getStringExtra("path") ?: return
        val directory = File(path)

        if (directory.isDirectory) {
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            val files = directory.listFiles()?.toList() ?: emptyList()
            val adapter = FileListAdapter(files, this::onFileClicked)
            recyclerView.adapter = adapter
        }
    }
    private fun onFileClicked(file: File) {
        if (file.isDirectory) {
            val intent = Intent(this, DirectoryActivity::class.java)
            intent.putExtra("path", file.absolutePath)
            startActivity(intent)
        } else if (file.isFile && file.extension == "txt") {
            val intent = Intent(this, FileViewerActivity::class.java)
            intent.putExtra("path", file.absolutePath)
            startActivity(intent)
        }
    }
}