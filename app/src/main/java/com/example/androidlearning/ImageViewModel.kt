package com.example.androidlearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {

    var imageList  = mutableListOf<ImageModel>()

    fun updateImages(image: ImageModel){
        imageList.add(image)
    }
}