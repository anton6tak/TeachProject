package com.example.teachproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachproject.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val myAdapter = MyAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        val recyclerView: RecyclerView = viewBinding.recyclerView

        val viewModel: MyViewModel by viewModels()
        
        val btnMusic = viewBinding.buttonMusic
        btnMusic.setOnClickListener { btnMusicOnClick() }

        val btnShare = viewBinding.buttonShare
        btnShare.setOnClickListener { btnShareOnClick() }

        val btnChange = viewBinding.buttonChange
        btnChange.setOnClickListener { btnChangeOnClick(btnChange) }

        val btnAdd = viewBinding.buttonAdd
        btnAdd.setOnClickListener { btnAddOnClick() }

        val btnNext = viewBinding.buttonNextActivity
        btnNext.setOnClickListener { btnNextOnClick() }

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = myAdapter
    }

    private fun btnMusicOnClick() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_APP_MUSIC)
        startActivity(intent)
    }

    private fun btnShareOnClick() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com")
        intent.putExtra(Intent.EXTRA_TEXT, "How are you?")
        startActivity(intent)
    }

    private fun btnChangeOnClick(btn: Button) {
        for (view in listOf(viewBinding.buttonMusic, viewBinding.buttonShare)) {
            val borderColor: Int = Color.argb(
                255,
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
            btn.setBackgroundColor(borderColor)

            val titleColor: Int = Color.argb(
                255,
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
            btn.setTextColor(titleColor)

            val params = btn.layoutParams as MarginLayoutParams
            params.setMargins(
                Random.nextInt(100),
                Random.nextInt(100),
                Random.nextInt(100),
                Random.nextInt(100)
            )
            btn.requestLayout()
        }
    }

    private fun btnAddOnClick() {
        if (viewBinding.editText.text.toString().trim().isNotEmpty()) {
            myAdapter.setItems(viewBinding.editText.text.toString().trim())
            //listItems.add(viewBinding.editText.text.toString())
            viewBinding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun btnNextOnClick() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }
}