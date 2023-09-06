package com.example.androidlearning

import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.androidlearning.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdaptor
    private lateinit var imageViewModel: ImageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        binding.imageRecycleView.layoutManager = LinearLayoutManager(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        if(ContextCompat.checkSelfPermission(this,"android.permission.READ_EXTERNAL_STORAGE") !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf("android.permission.READ_EXTERNAL_STORAGE"),0)
        }


        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        val miliSecYesterday = android.icu.util.Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR,-28)
        }.timeInMillis

        // taking 1 month images

        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val selectionArg = arrayOf(
            miliSecYesterday.toString()
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        binding.getImagesBtn.setOnClickListener {
            contentResolver.query(
                uri,
                projection,
                selection,
                selectionArg,
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                Log.i("Abhi", cursor.count.toString())
                while(cursor.moveToNext()){
                    val imageId = cursor.getLong(idColumn)
                    val imageName = cursor.getString(nameColumn)
                    val imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,imageId)
                    Log.i("Abhi", imageId.toString())
                    Log.i("Abhi", imageName.toString())
                    imageViewModel.updateImages(ImageModel(imageId,imageName,imageUri))
                    Log.i("Abhi",imageViewModel.imageList.toString())
                }
            }

            imageAdapter = ImageAdaptor(imageViewModel.imageList) // did not used live data that why passing list after all operations
            binding.imageRecycleView.adapter = imageAdapter

        }



        binding.btnContact.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

    }
}