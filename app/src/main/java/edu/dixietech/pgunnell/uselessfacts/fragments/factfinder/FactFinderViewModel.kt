package edu.dixietech.pgunnell.uselessfacts.fragments.factfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import kotlinx.coroutines.launch
import edu.dixietech.pgunnell.uselessfacts.networking.UselessFactsService

class FactFinderViewModel : ViewModel() {

    private var _fact = MutableLiveData<Fact>()
    val fact: LiveData<Fact>
        get() = _fact

    fun getRandomFact() {
        viewModelScope.launch {
            val randomFact = UselessFactsService.getRandomFact()
            _fact.postValue(randomFact)
        }
    }

    fun favoriteFact() {
        fact.value?.let {
            // TODO: Add to favorites
            _fact.postValue(it.apply { isFavorited = !isFavorited })
        }
    }

}