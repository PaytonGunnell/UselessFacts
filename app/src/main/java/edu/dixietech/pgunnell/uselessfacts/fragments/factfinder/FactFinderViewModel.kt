package edu.dixietech.pgunnell.uselessfacts.fragments.factfinder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.database.FactDatabase
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import kotlinx.coroutines.launch
import edu.dixietech.pgunnell.uselessfacts.networking.UselessFactsService
import edu.dixietech.pgunnell.uselessfacts.repository.FactRepository

class FactFinderViewModel(application : Application) : AndroidViewModel(application) {

    private val repository = FactRepository(FactDatabase.getInstance(application))

    private var _fact = MutableLiveData<Fact>()
    val fact: LiveData<Fact>
        get() = _fact

    fun getRandomFact() {
        viewModelScope.launch {
            val randomFact = repository.getRandomFact()
            _fact.postValue(randomFact)
        }
    }

    fun favoriteFact() {
        fact.value?.let {
            viewModelScope.launch {
            if (!it.isFavorited) { repository.favoriteFact(it) }
                else { repository.unFavoriteFact(it) }
            }
            _fact.postValue(it.apply { isFavorited = !isFavorited })
        }
    }
}