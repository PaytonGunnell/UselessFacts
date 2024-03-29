package edu.dixietech.pgunnell.uselessfacts.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.dixietech.pgunnell.uselessfacts.R
import edu.dixietech.pgunnell.uselessfacts.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.discoverButton.setOnClickListener {
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToFactFinderFragment())
        }

        binding.savedButton.setOnClickListener {
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFactsFragment())
        }

        return binding.root
    }
}