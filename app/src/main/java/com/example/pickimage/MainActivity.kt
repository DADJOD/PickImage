package com.example.pickimage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pickimage.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private var mInfo: TextView? = null
    private var mImage: ImageView? = null
    private var uri: Uri? = null
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mImage = findViewById(R.id.mImage)
        mInfo  = findViewById(R.id.info)

//        mInfo?.run { text = "Info" }
//        mImage?.run { setImageResource(android.R.drawable.btn_star_big_on) }

        binding.fab.setOnClickListener { view ->
            pickImage()
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 14)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ( requestCode != 14) return super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode != RESULT_OK) return
        else {
            uri = data!!.data

            mInfo!!.text = uri.toString()
            mImage!!.setImageURI(uri)
            Log.d("happySDK", uri.toString())
//            copyImage()
        }
    }

    fun copyImageWrapper() {
        val result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (result == PackageManager.PERMISSION_GRANTED) {
            copyImage()
            return
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            42
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 42) {
            val result = grantResults[0]
            if (result == PackageManager.PERMISSION_GRANTED) {
                copyImage()
            } else {
                mInfo!!.text = "permission denied"
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    @SuppressLint("Recycle")
    private fun copyImage() {
        // internal
//        val dir = filesDir    // dir - its place where picture will be save
//        val dir = cacheDir

        // external
//        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return
        }

        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        Log.d("happySDK", "free ${dir.freeSpace}")
        Log.d("happySDK", dir!!.absolutePath)
        dir.mkdirs()

        val out = File(dir, "img ${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(out)
        val inputStream = contentResolver.openInputStream(uri!!)

        val buffer = byteArrayOf(1024.toByte())
        var len : Int
        while ((inputStream!!.read(buffer).also { len = it }) != -1) {
            outputStream.write(buffer, 0, len)
        }
        inputStream!!.close()
        outputStream.close()

        Log.d("happySDK", out.absolutePath)
        Log.d("happySDK", "len ${out.length()}")
    }
}