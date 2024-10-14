package com.example.calculator03102024

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.LinkedList
import java.util.Queue

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var textResult: TextView
    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    val queue: Queue<Int> = LinkedList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textResult = findViewById(R.id.textView)
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.sum).setOnClickListener(this)
        findViewById<Button>(R.id.sub).setOnClickListener(this)
        findViewById<Button>(R.id.divide).setOnClickListener(this)
        findViewById<Button>(R.id.mul).setOnClickListener(this)
        findViewById<Button>(R.id.result).setOnClickListener(this)
        findViewById<Button>(R.id.BS).setOnClickListener(this)
        findViewById<Button>(R.id.C).setOnClickListener(this)
        findViewById<Button>(R.id.CE).setOnClickListener(this)
        findViewById<Button>(R.id.btnDot).setOnClickListener(this)
        findViewById<Button>(R.id.btnSum_Sub).setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        val id = p0?.id
        if(id == R.id.btn0){
            addDigit(0)
        }else if(id == R.id.btn1){
            addDigit(1)
        }else if(id == R.id.btn2) {
            addDigit(2)
        }else if(id == R.id.btn3) {
            addDigit(3)
        }else if(id == R.id.btn4) {
            addDigit(4)
        }else if(id == R.id.btn5) {
            addDigit(5)
        }else if(id == R.id.btn6) {
            addDigit(6)
        }else if(id == R.id.btn7) {
            addDigit(7)
        }else if(id == R.id.btn8) {
            addDigit(8)
        }else if(id == R.id.btn9) {
            addDigit(9)
        }else if(id == R.id.sum) {
            op = 1
            if (state == 1) {
                queue.add(op)
                state = 2
            }else if(state == 2){
                if(op2 != 0){
                    queue.poll()?.let { calculatorResult(it) }
                    op2 = 0
                    queue.add(op)
                }
            }
        }else if(id == R.id.sub) {
            op = 2
            if (state == 1) {
                queue.add(op)
                state = 2
            }else if(state == 2){
                if(op2 != 0){
                    queue.poll()?.let { calculatorResult(it) }
                    op2 = 0
                    queue.add(op)
                }
            }
        }else if(id == R.id.divide) {
            op = 3
            if (state == 1) {
                queue.add(op)
                state = 2
            }else if(state == 2){
                if(op2 != 0){
                    queue.poll()?.let { calculatorResult(it) }
                    op2 = 0
                    queue.add(op)
                }
            }
        }else if(id == R.id.C) {
            if (id == R.id.C) {
                op1 = 0
                op2 = 0
                state = 1
                textResult.text = "0"
            }
        }else if(id == R.id.CE) {
            if (state == 1) {
                op1 = 0
                textResult.text = "0"
            } else {
                op2 = 0
                textResult.text = "0"
            }
        }else if(id == R.id.BS) {
            if (state == 1) {
                op1 = op1 / 10
                textResult.text = "$op1"
            } else {
                op2 = op2 / 10
                textResult.text = "$op2"
            }
        }else if(id == R.id.btnDot) {
            //Update in the future
        }else if(id == R.id.mul) {
            op = 4
            if (state == 1) {
                queue.add(op)
                state = 2
            }else if(state == 2){
                if(op2 != 0){
                    queue.poll()?.let { calculatorResult(it) }
                    op2 = 0
                    queue.add(op)
                }
            }
        }else if(id == R.id.result) {
            var result = 0
            if (op == 1) {
                result = (op1 + op2)
                textResult.text = "$result"
            }else if(op == 2){
                result = (op1 - op2)
                textResult.text = "$result"
            }else if(op == 3){
                result = op1/op2
                textResult.text = "$result"
            }else if(op == 4){
                result = op1 * op2
                textResult.text = "$result"
            }
            state = 1
            op1 = 0
            op2 = 0
            op = 0
        }else if(id == R.id.btnSum_Sub){
            if (state == 1) {
                op1 = -op1
                textResult.text = "$op1"
            } else {
                op2 = -op2
                textResult.text = "$op2"
            }
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textResult.text = "$op2"
        }
    }
    fun calculatorResult(c:Int){
        if(c == 1){
            op1 = op1 + op2
            textResult.text =  "$op1"
        }else if(c == 2){
            op1 = op1 - op2
            textResult.text =  "$op1"
        }else if(c == 3){
            op1 = op1 / op2
            textResult.text =  "$op1"
        }else if(c == 4){
            op1 = op1 * op2
            textResult.text =  "$op1"
        }
    }

}