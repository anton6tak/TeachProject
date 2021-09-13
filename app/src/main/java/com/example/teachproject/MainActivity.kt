package com.example.teachproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachproject.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var listItems: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        val viewModel: MyViewModel by viewModels()

        val btnMusic = viewBinding.buttonMusic
        btnMusic.setOnClickListener(this)

        val btnShare = viewBinding.buttonShare
        btnShare.setOnClickListener(this)

        val btnChange = viewBinding.buttonChange
        btnChange.setOnClickListener(this)

        val btnAdd = viewBinding.buttonAdd
        btnAdd.setOnClickListener(this)

        val btnNext = viewBinding.buttonNextActivity
        btnNext.setOnClickListener(this)

        recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getUsers().observe(this, Observer<List<String>> { users ->
            recyclerView.adapter = MyAdapter(users)
        })
        listItems = mutableListOf("A", "B", "C", "D", "E", "F", "J", "H")
        recyclerView.adapter = MyAdapter(listItems)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            viewBinding.buttonMusic.id -> {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_MUSIC)
                startActivity(intent)
            }
            viewBinding.buttonShare.id -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com")
                intent.putExtra(Intent.EXTRA_TEXT, "How are you?")
                startActivity(intent)
            }
            viewBinding.buttonChange.id -> {
                for (view in listOf(viewBinding.buttonMusic, viewBinding.buttonShare)) {
                    val borderColor: Int = Color.argb(
                        255,
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256)
                    )
                    view.setBackgroundColor(borderColor)

                    val titleColor: Int = Color.argb(
                        255,
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256)
                    )
                    view.setTextColor(titleColor)

                    val params = view.layoutParams as MarginLayoutParams
                    params.setMargins(
                        Random.nextInt(100),
                        Random.nextInt(100),
                        Random.nextInt(100),
                        Random.nextInt(100)
                    )
                    view.requestLayout()
                }
            }
            viewBinding.buttonAdd.id -> {
                if (viewBinding.editText.text.toString().trim().isNotEmpty()) {
                    listItems.add(viewBinding.editText.text.toString())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            viewBinding.buttonNextActivity.id -> {
                startActivity(Intent(this, MvvmActivity::class.java))
            }

        }
    }
}