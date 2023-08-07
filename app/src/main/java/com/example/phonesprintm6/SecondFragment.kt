package com.example.phonesprintm6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.phonesprintm6.ViewModel.PhoneViewModel
import com.example.phonesprintm6.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: PhoneViewModel by activityViewModels()
    private var phoneId: String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        arguments?.let { bundle ->
            phoneId = bundle.getString("phoneid")
            Log.d("***LO RECIBO??***", phoneId.toString())
            //recibo bien
        }

        phoneId?.let {
            id-> viewModel.getPhoneDetailByIdFromInternet(id)
        }

        viewModel.getPhoneDetail().observe(viewLifecycleOwner, Observer {


            Glide.with(binding.imageView2).load(it.image).into(binding.imageView2)
            binding.id.text="Id Producto:    "+ it.id
            binding.name.text="Nombre de Celular:  "+ it.name
            binding.price.text="Precio:  "+ it.price.toString()
            binding.description.text="Descripción Producto:  "+ it.description
            binding.lastprice.text="Precio Final:  "+ it.lastPrice.toString()

            /** Esta funcionalidad fue requerida en la evaluación. Sin embargo, en la Api no
            existen credit = false, por tanto, son todos pago crédito**/

            if(it.credit==true){
                binding.credit.text="Pago Crédito"
            }
            else{
                binding.credit.text="Pago Efectivo"
            }

            //falta boton correo,
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}