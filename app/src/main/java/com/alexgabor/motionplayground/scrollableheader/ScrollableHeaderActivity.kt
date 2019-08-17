package com.alexgabor.motionplayground.scrollableheader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexgabor.motionplayground.R
import com.alexgabor.motionplayground.databinding.ScrollableHeaderActivityBinding
import com.bumptech.glide.Glide

class ScrollableHeaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ScrollableHeaderActivityBinding>(this, R.layout.scrollable_header_activity)

        binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ScrollableHeaderAdapter().apply {
                submitList(items)
            }
        }

        Glide.with(this)
            .load("https://picsum.photos/1600/900")
            .into(binding.header)

    }
}

val items = (0..50).map { ScrollableHeaderAdapter.Item("Title $it", "Description $it") }