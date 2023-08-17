package edu.dixietech.pgunnell.uselessfacts.fragments.favoritefacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.dixietech.pgunnell.uselessfacts.databinding.FragmentFavoriteFactsBinding

class FavoriteFactsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteFactsBinding
    private lateinit var viewModel: FavoriteFactsViewModel
    private lateinit var adapter: FavoriteFactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteFactsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoriteFactsViewModel::class.java]

        setUpObservers()
        setupRecycler()

        viewModel.getFacts()
//        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    fun setUpObservers() {
        viewModel.facts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    fun setupRecycler() {
        adapter = FavoriteFactsAdapter(FavoriteFactsAdapter.ClickListener {
            findNavController()
                .navigate(FavoriteFactsFragmentDirections.actionFavoriteFactsFragmentToFactDetailFragment(it))
        })
        binding.favoriteFactRecycler.adapter = adapter
    }
}