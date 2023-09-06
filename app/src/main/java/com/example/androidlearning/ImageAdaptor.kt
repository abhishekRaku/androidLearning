package com.example.androidlearning

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearning.databinding.ActivityMain2Binding
import com.example.androidlearning.databinding.ImageListBinding

class ImageAdaptor(private val imageList: List<ImageModel>): RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(ImageListBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.i("Abhi", "setting image" )
        holder.setImage(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun updateImageList(imageList: MutableList<ImageModel>){
        imageList.addAll(imageList)
    }
}

class ImageViewHolder(private val binding: ImageListBinding): RecyclerView.ViewHolder(binding.root){

    fun setImage(image: ImageModel){
        Log.i("Abhi", "set " + image.name )
        binding.tvImage.text = image.name
        binding.ivImage.setImageURI(image.uri)
    }
}