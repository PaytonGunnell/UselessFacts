package edu.dixietech.pgunnell.uselessfacts.fragments.factdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.dixietech.pgunnell.uselessfacts.R
import edu.dixietech.pgunnell.uselessfacts.databinding.FragmentFactDetailBinding

class FactDetailFragment : Fragment() {

    private lateinit var binding: FragmentFactDetailBinding
    private lateinit var viewModel: FactDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactDetailBinding.inflate(inflater, container, false)


        return binding.root
    }
}