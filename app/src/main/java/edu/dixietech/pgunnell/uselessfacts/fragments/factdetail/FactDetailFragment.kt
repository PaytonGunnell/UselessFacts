package edu.dixietech.pgunnell.uselessfacts.fragments.factdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.R
import edu.dixietech.pgunnell.uselessfacts.databinding.FragmentFactDetailBinding
import kotlinx.coroutines.launch

class FactDetailFragment : Fragment() {

    private lateinit var binding: FragmentFactDetailBinding
    private lateinit var viewModel: FactDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactDetailBinding.inflate(inflater, container, false)

        setupViewModel()
        setupObservers()

        viewModel.viewModelScope.launch {
            viewModel.getFact()
        }

        return binding.root
    }

    private fun setupViewModel() {
        val args = FactDetailFragmentArgs.fromBundle(requireArguments())
        val factory = FactDetailViewModel.Factory(requireActivity().application, args.factId)
        viewModel = ViewModelProvider(this, factory)[FactDetailViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.fact.observe(viewLifecycleOwner) {
            it?.let {
                binding.fact = it
                binding.factSource.movementMethod = LinkMovementMethod.getInstance()
                val linkedText = "<a href='${it.sourceUrl}'>${it.source}</a> says:"
                val htmlText = Html.fromHtml(linkedText, Html.FROM_HTML_MODE_COMPACT)
                binding.factSource.text = htmlText
            }
        }
    }
}