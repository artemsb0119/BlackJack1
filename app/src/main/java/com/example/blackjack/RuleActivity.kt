package com.example.blackjack

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class RuleActivity : AppCompatActivity() {

    private lateinit var rule: Rule
    private lateinit var imageViewFon2: ImageView
    private lateinit var activity: Activity
    private lateinit var textViewTitle: TextView
    private lateinit var textViewText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule)
        imageViewFon2 = findViewById(R.id.imageViewFon2)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewText = findViewById(R.id.textViewText)

        activity = this
        Glide.with(this)
            .load("http://135.181.248.237/26/fon2.png")
            .into(imageViewFon2)
        loadData()
    }
    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/26/rule.json").readText()
                val gson = Gson()
                rule = gson.fromJson(data, Rule::class.java)

                activity.runOnUiThread {
                    textViewTitle.text = rule.name
                    textViewText.text = rule.text
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}