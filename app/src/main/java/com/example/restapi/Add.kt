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

class Add : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etGenre: EditText
    lateinit var etJudul: EditText
    lateinit var etDire: EditText
    lateinit var etDur: EditText
    lateinit var apiService: ServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etJudul = findViewById(R.id.et_add_judul)
        etGenre = findViewById(R.id.et_add_genre)
        etDire = findViewById(R.id.et_add_dire)
        etDur = findViewById(R.id.et_add_dur)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = KontakData()
            array.judul=etJudul.text.toString()
            array.genre= etGenre.text.toString()
            array.director = etDire.text.toString()
            array.durasi = etDur.text.toString().toInt()
            apiService.postKontak(array).enqueue(object : Callback<KontakData>{
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(Intent(this@Add, MainActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}