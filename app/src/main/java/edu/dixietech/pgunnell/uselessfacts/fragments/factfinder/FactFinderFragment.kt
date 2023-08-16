package edu.dixietech.pgunnell.uselessfacts.fragments.factfinder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.databinding.FragmentFactFinderBinding
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import java.net.URL

class FactFinderFragment : Fragment() {

    private lateinit var binding: FragmentFactFinderBinding
    private lateinit var viewModel: FactFinderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactFinderBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[FactFinderViewModel::class.java]
        binding.viewModel = viewModel
//        setupObservers()

        viewModel.getRandomFact()
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }



//    private fun setupObservers() {
//        viewModel.fact.observe(viewLifecycleOwner, Observer {
//            binding.fact = it
//        })
//    }
}