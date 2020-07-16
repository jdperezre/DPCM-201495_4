package com.culturapp.culturapp.ui.events

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.culturapp.culturapp.R
import com.culturapp.culturapp.api.ApiClient
import com.culturapp.culturapp.models.Event
import kotlinx.android.synthetic.main.new_event.*
import kotlinx.android.synthetic.main.titlebar_events.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*


class NewEventActivity : AppCompatActivity() {
    lateinit var progressProgressDialog: ProgressDialog
    private lateinit var selectedImage: Bitmap
    private lateinit var encodedImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.new_event)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }


        initControls()
        setupEvents()

        //Image
        initImageView()
    }

    private fun setupEvents() {
        btnBack.setOnClickListener{
            onBackPressed()
        }
        button.setOnClickListener {

            progressProgressDialog = ProgressDialog(this, 0)
            progressProgressDialog.run {
                setTitle(getString(R.string.loading))
                setContentView(R.layout.progress)
                setCancelable(false)
                show()
            }

            saveData()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout)
    }

    private fun initControls() {

        editTextTitle.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextTitle.SetHint(R.string.hint_titulo)

        editTextDescription.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        editTextDescription.SetHint(R.string.hint_descripcion)

        editTextDate.SetInputType(InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE)
        editTextDate.SetHint(R.string.hint_fecha)
        editTextDate.SetIcon(R.drawable.ic_calendar_input)

        editTextHour.SetInputType(InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_TIME)
        editTextHour.SetHint(R.string.hint_hora)
        editTextHour.SetIcon(R.drawable.ic_clock_input)

        editTextLocation.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextLocation.SetHint(R.string.hint_lugar)

        editTextContact.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextContact.SetHint(R.string.hint_contacto)

        editTextTel.SetInputType(InputType.TYPE_CLASS_PHONE)
        editTextTel.SetHint(R.string.hint_telefono_contacto)
    }


    private fun saveData() {
        val event: Event = Event(0,
            editTextTitle.getText(),
            editTextContact.getText(),
            editTextDescription.getText(),
            editTextTel.getText(),
            editTextLocation.getText(),
            editTextDate.getText(),
            editTextDate.getText(),
            editTextHour.getText(),
            "encodedImage",
            2,
            1
        )
        val call: Call<String> = ApiClient.getClient.createEvent(event)!!
        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    progressProgressDialog.hide()
                    Toast.makeText(this@NewEventActivity, getString(R.string.event_saved), Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }
                else{
                    progressProgressDialog.hide()
                    Log.d("Error Service", response.errorBody()?.string())
                    Toast.makeText(this@NewEventActivity, getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                progressProgressDialog.hide()

                if(!call.isCanceled && call.isExecuted){
                    Toast.makeText(this@NewEventActivity, getString(R.string.event_saved), Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }else{
                    Toast.makeText(this@NewEventActivity, getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show()
                }


            }

        })
    }


    private fun initImageView() {
        imgEvent.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)

                } else {
                    pickImageFromGallery()
                }
            }
            else
            {
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else
                {
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){

            val imageUri: Uri? = data!!.data
            val imageStream: InputStream? = contentResolver.openInputStream(imageUri!!)
            selectedImage = BitmapFactory.decodeStream(imageStream)
            encodedImage = encodeImage(selectedImage)!!

            imgEvent.setImageBitmap(selectedImage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun encodeImage(path: String): String? {
        val imagefile = File(path)
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(imagefile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val bm = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        //Base64.de
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


}