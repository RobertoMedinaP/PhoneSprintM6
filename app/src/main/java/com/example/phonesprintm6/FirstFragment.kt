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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonesprintm6.ViewModel.PhoneViewModel
import com.example.phonesprintm6.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: PhoneViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val adapter= PhoneAdapter()
        binding.rv1.adapter=adapter
        binding.rv1.layoutManager=LinearLayoutManager(context)
        binding.rv1.addItemDecoration(
            DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL)
        )

        viewModel.getPhoneList().observe(viewLifecycleOwner, Observer {

            it?.let {
                adapter.updateData(it)
            }
        })

        //me falta funcion para seleccionar

        adapter.elementoSeleccionado().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("******ELEGIR ID******", it.id.toString())
                //ID elegido sin problemas :)
                //podria intentar mandar con el viewmodel
            }
            val bundle= Bundle().apply {
                putString("phoneid", it.id.toString())
            }
            //se me habia olvidado enviar el bundle
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)


        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}