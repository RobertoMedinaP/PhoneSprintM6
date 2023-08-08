package com.example.phonesprintm6.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonesprintm6.R
import com.example.phonesprintm6.ViewModel.PhoneViewModel
import com.example.phonesprintm6.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    // Declaracion de variables
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // Instancia de viewModel
    private val viewModel: PhoneViewModel by activityViewModels()

    // Implementacion de metodos
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Implementacion de boton para salir de aplicacion
        binding.btcerrar.setOnClickListener {
            requireActivity().finishAffinity()
        }

        // Le pasamos el adapter al RecyclerView
        val adapter = PhoneAdapter()
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(context)
        binding.rv1.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.getPhoneList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })

        adapter.elementoSeleccionado().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("******ELEGIR ID******", it.id.toString())
            }
            val bundle = Bundle().apply {
                putString("phoneid", it.id.toString())
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })
    }

    // Funcion para destruir la vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}