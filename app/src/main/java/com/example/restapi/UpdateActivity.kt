package com.example.restapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etJudul: EditText
    lateinit var etGenre: EditText
    lateinit var etDire: EditText
    lateinit var etDur: EditText
    lateinit var valJudul: String
    lateinit var valDir: String
    lateinit var valId: String
    lateinit var valDur: String
    lateinit var valGenre: String
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }

    fun declaration() {
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etJudul  = findViewById(R.id.et_add_judul)
        etGenre = findViewById(R.id.et_add_genre)
        etDur = findViewById(R.id.et_add_dur)
        etDire = findViewById(R.id.et_add_dire)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun getMyData() {
        val myValue = intent.extras
        if (myValue != null) {
            valJudul = myValue.getString("judul").toString()
            valDir = myValue.getString("director").toString()
            valGenre = myValue.getString("genre").toString()
            valDur = myValue.getString("durasi").toString()
            valId = myValue.getString("id").toString()
        }
    }

    fun myfunction() {
        etJudul.setText(valJudul)
        etDire.setText(valDir)
        etGenre.setText(valGenre)
        etDur.setText(valDur)
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            apiService.updateKontak(
                valId.toInt(),
                etJudul.text.toString(),
                etDire.text.toString(),
                etGenre.text.toString(),
                etDur.text.toString().toInt(),
            ).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }
            }
                )
            }
        btnCancel.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
        }
    }