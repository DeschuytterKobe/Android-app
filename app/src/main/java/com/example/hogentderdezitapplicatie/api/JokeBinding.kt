package com.example.hogentderdezitapplicatie.api

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("jokeText")
fun TextView.setJokeText(item: String?) {
    item?.let {
        text = item
    }
}