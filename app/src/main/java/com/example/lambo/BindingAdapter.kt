package com.example.lambo

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lambo.home.MovieAdapter
import com.example.lambo.model.Movie

val BASEURL_IMAGE = "https://image.tmdb.org/t/p/w185/"


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(BASEURL_IMAGE + it)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_image))
            .into(imgView)
    }
}

@BindingAdapter("textTitle")
fun bindText(textView: TextView, text: String?) {
    text?.let {
        textView.text = it
    }
}