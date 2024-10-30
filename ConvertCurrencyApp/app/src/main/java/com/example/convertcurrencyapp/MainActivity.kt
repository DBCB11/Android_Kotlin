package com.example.convertcurrencyapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var sourceAmount: EditText
    private lateinit var targetAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var targetCurrency: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sourceAmount = findViewById(R.id.sourceAmount)
        targetAmount = findViewById(R.id.targetAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        targetCurrency = findViewById(R.id.targetCurrency)

        // Thiết lập các đơn vị tiền tệ mẫu
        val currencies = listOf("USD", "EUR", "VND", "JPY")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrency.adapter = adapter
        targetCurrency.adapter = adapter

        // Thêm listener thay đổi vào EditText
        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Listener thay đổi của Spinner
        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        targetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun convertCurrency() {
        val amountText = sourceAmount.text.toString()
        if (amountText.isNotEmpty()) {
            val amount = amountText.toDoubleOrNull() ?: return

            val sourceCurrencyCode = sourceCurrency.selectedItem.toString()
            val targetCurrencyCode = targetCurrency.selectedItem.toString()

            // Chuyển đổi số tiền dựa trên tỷ giá (ví dụ giả định)
            val exchangeRate = getExchangeRate(sourceCurrencyCode, targetCurrencyCode)
            val convertedAmount = amount * exchangeRate

            targetAmount.setText(convertedAmount.toString())
        } else {
            targetAmount.setText("")
        }
    }

    // Hàm lấy tỷ giá (ví dụ giả định)
    private fun getExchangeRate(source: String, target: String): Double {
        return when (source to target) {
            "USD" to "VND" -> 24000.0
            "VND" to "USD" -> 1 / 24000.0
            "EUR" to "USD" -> 1.1
            "USD" to "EUR" -> 1 / 1.1
            else -> 1.0 // tỷ giá mặc định là 1 cho các đơn vị khác
        }
    }
}