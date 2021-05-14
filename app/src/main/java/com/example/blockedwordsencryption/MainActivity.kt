package com.example.blockedwordsencryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.blockedwordsencryption.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var input: EditText
    lateinit var output: TextView


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        input = findViewById(R.id.input)
       output = findViewById(R.id.output)
        input.addTextChangedListener(textWatcher)
        binding.word.addTextChangedListener(textWatcher)
        binding.reset.setOnClickListener { reset() }
    }

     private  val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            val inputList = input.text.split(" ").lazyLog().toMutableList()


            inputList.forEachIndexed { index, words ->
                words.takeIf {  binding.word.text.contains(it)}?.let {
                    inputList[index] = it.chunked(1).joinToString ("-")
                }
            }


                binding.output.text=inputList.joinToString(" ").lazyLog()




        }
            }

private fun reset()
{
    output.text=""
    input.setText("")
    binding.word.setText("")
}

    fun <T> T.lazyLog(tag: String = "LAZY_LOG"): T {
        Log.i(tag, toString())
        return this
    }
}

