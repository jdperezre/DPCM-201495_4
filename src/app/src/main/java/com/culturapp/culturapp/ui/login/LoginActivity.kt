package com.culturapp.culturapp.ui.login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.Window
import com.culturapp.culturapp.R
import com.culturapp.culturapp.api.ApiClient
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.titlebar_events.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var progressProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.login)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()

        //TODO: Mostrar cuando se implemente la funcionalidad
        btnGoogle.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }

    private fun initControls() {
        editTextUser.SetHint(R.string.hint_usuario)
        editTextUser.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME)

        editTextPassword.SetHint(R.string.hint_contrasena)
        editTextPassword.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }

    private fun setupEvents() {
        btnLogin.setOnClickListener{
            login()
        }

        btnGoogle.setOnClickListener {
            //TODO: goToGoogle
        }

        textForgetPassword.setOnClickListener{
            startActivity(Intent(this, ForgetEmailActivity::class.java))
            overridePendingTransition(R.anim.leftin, R.anim.leftout)
        }

        textRegister.setOnClickListener{
            startActivity(Intent(this, RegisterLoginInfoActivity::class.java))
            overridePendingTransition(R.anim.leftin, R.anim.leftout)
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun login(){

        var data = JSONObject("""{"email":"Juanperz211@gmail.com","contrasena":"Juan123**"}""")

        val call : Call<String>? = ApiClient.getClient.login(data)
        call?.enqueue(object : Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val loginResponse = response.body()

                if (loginResponse != null) {
                    println("Éxitoso!!!")
                } else {
                    println("Error al autenticarse")
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                println("Falló>>> ")
            }


        })
    }


    








}






