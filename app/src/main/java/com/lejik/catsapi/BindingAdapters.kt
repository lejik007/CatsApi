package com.lejik.catsapi

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.lejik.catsapi.network.CatsPhoto
import com.lejik.catsapi.ui.dashboard.CatsApiStatus
import androidx.databinding.BindingAdapter
import coil.load
import com.lejik.catsphotos.overview.PhotoGridAdapter
import java.util.*

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CatsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        CatsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CatsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CatsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
