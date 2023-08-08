package com.example.phonesprintm6.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.databinding.ItemListBinding

// Implementacion clase
class PhoneAdapter : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    private var phoneList = listOf<PhoneEntity>()
    private val selectedPhone = MutableLiveData<PhoneEntity>()

    // Declaracion de Clase interna ViewHolder y funciones
    inner class PhoneViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root),

        View.OnClickListener {
        fun bind(phone: PhoneEntity) {
            Glide.with(binding.imageView).load(phone.image).into(binding.imageView)
            binding.tv1.text = "ID Producto " + phone.id.toString()
            binding.tv2.text = phone.name
            binding.tv3.text = "$" + phone.price.toString() + "  CLP"
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedPhone.value = phoneList[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val selectedPhone = phoneList[position]
        holder.bind(selectedPhone)
    }

    //funcion para seleccionar
    fun elementoSeleccionado(): LiveData<PhoneEntity> = selectedPhone

    //funcion para actualizar
    fun updateData(list: List<PhoneEntity>) {
        phoneList = list
        notifyDataSetChanged()
    }
}