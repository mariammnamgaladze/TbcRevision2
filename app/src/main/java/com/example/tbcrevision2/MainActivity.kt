package com.example.tbcrevision2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var nameText: EditText
    private lateinit var saveBtn: Button
    private lateinit var outputBtn: Button
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.nameText)
        saveBtn = findViewById(R.id.saveBtn)
        outputBtn = findViewById(R.id.outputBtn)
        textView = findViewById(R.id.TextView)

        findViewById<Button>(R.id.saveBtn).setOnClickListener(this)
        findViewById<Button>(R.id.outputBtn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
             R.id.saveBtn -> addAnagram()
            R.id.outputBtn -> outputAnagram()
        }
    }

    private val container = mutableListOf<String>()

    private fun checkInputs(): Boolean {
        if (nameText.text.isNullOrEmpty() )
            return false

        return true
    }

    private fun areAnagrams(one: String, two: String): Boolean {

        val map = HashMap<Char, Int>()

        for (c in one.toCharArray())
            if (map.containsKey(c))
                map[c] = map[c]!! + 1
            else
                map[c] = 1

        for (c in two.toCharArray())
            if (!map.containsKey(c))
                return false
            else {
                map[c] = map[c]!! - 1

                if (map[c] == 0)
                    map.remove(c)
            }
        return map.isEmpty()
    }



    private fun addAnagram (){
        if (checkInputs()){
            container.add(nameText.text.toString())
        }
    }


    private fun outputAnagram(){
        val anagramList= mutableListOf<String>()
          container.forEachIndexed { parentIndex, s ->
              anagramList.add(s)
              container.forEachIndexed{ index,i ->
                  if(index>parentIndex){
                     if (areAnagrams(s,i)){
                         anagramList.add(i)
                     }
                  }
              }
          }
        textView.text = anagramList.toString()
        }


    }




