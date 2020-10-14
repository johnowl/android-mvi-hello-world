package com.johnowl.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.johnowl.cleanarchitecture.R
import com.johnowl.cleanarchitecture.model.BlogPost
import com.johnowl.cleanarchitecture.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent((MainStateEvent.GetBlogPostEvents))
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<BlogPost>> -> {
                    displayProgressBar(false)
                    appendBlogPostTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        text.text = message ?: "Unknown error."
    }

    private fun displayProgressBar(isDislayed: Boolean) {
        progress_bar.visibility = if (isDislayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogPostTitles(blogPosts: List<BlogPost>) {
        val sb = StringBuilder()
        for (post in blogPosts) {
            sb.appendLine(post.title)
        }
        text.text = sb.toString()
    }
}