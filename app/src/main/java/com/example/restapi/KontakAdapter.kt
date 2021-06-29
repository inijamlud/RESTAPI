package com.example.restapi
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KontakAdapter(private val listku: ArrayList<KontakData>): RecyclerView.Adapter<KontakAdapter.KontakViewHolder>(){
    inner class KontakViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var arrayku = KontakData()
        var tvJudul: TextView = viewku.findViewById(R.id.tv_judul)
        var tvGenre: TextView = viewku.findViewById(R.id.tv_genre)
        var tvDire: TextView = viewku.findViewById(R.id.tv_director)
        var tvDur: TextView = viewku.findViewById(R.id.tv_durasi)
        var btnDelte: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return KontakViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvJudul.text = dataku.judul
        holder.tvDire.text = dataku.director
        holder.tvGenre.text = dataku.genre
        holder.tvDur.text = dataku.durasi.toString()
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("judul", dataku.judul.toString())
            bundle.putString("director", dataku.director.toString())
            bundle.putString("genre", dataku.genre.toString())
            bundle.putString("durasi", dataku.durasi.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }

        holder.btnDelte.setOnClickListener {
            holder.apiIterface!!.deleteKontak(dataku.id!!).enqueue(object : Callback<KontakData>{
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    if (response.isSuccessful){
                        listku.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}