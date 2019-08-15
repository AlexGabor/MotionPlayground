package com.alexgabor.motionplayground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexgabor.motionplayground.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity).apply {
            mainMenu.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainMenuAdapter().apply {
                    setClickListener { onItemSelected(it.activity) }
                    submitList(menuItems)
                }
            }
        }
    }

    private fun onItemSelected(activity: Class<out AppCompatActivity>) {
        startActivity(Intent(this, activity))
    }
}

val menuItems = listOf(
    MainMenuAdapter.Item(
        title = "Scrollable Header above RecyclerView with MotionLayout",
        description = "How to scroll views at the ends of RecyclerView to make them seem like they are part of the list",
        activity = ScrollableHeaderAboveRecyclerViewActivity::class.java
    )
)