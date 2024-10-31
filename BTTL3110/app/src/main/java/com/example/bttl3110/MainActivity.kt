package com.example.bttl3110

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var number:EditText;
    private lateinit var listNumber:ListView;
    private lateinit var noti: TextView;
    private lateinit var radioGroup:RadioGroup;
    private lateinit var show:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        radioGroup = findViewById(R.id.radioGroup);
        number = findViewById(R.id.number);
        listNumber = findViewById(R.id.listView);
        noti = findViewById(R.id.noti);
        show = findViewById(R.id.btnShow);
        show.setOnClickListener{
            val selectedId = radioGroup.checkedRadioButtonId
            val value = number.text.toString().toDoubleOrNull()
            Log.d("CONSOLE: ", value.toString())
            if(selectedId != -1 && value != null){
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val selectedText = selectedRadioButton.text.toString()
                if(selectedText.equals("even")){
                    var result = mutableListOf("");
                    for(i in 0..value.toInt()){
                        if(i %2 == 0){
                            result.add(i.toString());
                        }
                    }
                    val adapter = ArrayAdapter(
                        this,
                        R.layout.list_item, // Layout cho từng mục
                        result // Dữ liệu
                    )
                    listNumber.adapter = adapter
                }else if(selectedText.equals("odd")){
                    var result = mutableListOf("");
                    for(i in 0..value.toInt()) {
                        if (i % 2 != 0) {
                            result.add(i.toString());
                        }
                    }
                    val adapter = ArrayAdapter(
                        this,
                        R.layout.list_item, // Layout cho từng mục
                        result // Dữ liệu
                    )
                    listNumber.adapter = adapter
                }else if(selectedText.equals("square")){
                    var result = mutableListOf("");
                    for(i in 0..value.toInt()) {
                        val sqrt = Math.sqrt(i.toDouble()).toInt();
                        if(sqrt*sqrt == i){
                            result.add(i.toString())
                        }
                    }
                    val adapter = ArrayAdapter(
                        this,
                        R.layout.list_item, // Layout cho từng mục
                        result // Dữ liệu
                    )
                    listNumber.adapter = adapter
                }
            }else{
                noti.setText("Chưa thể cho ra kết quả")
            }
        }
    }
}