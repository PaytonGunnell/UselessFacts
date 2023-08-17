package edu.dixietech.pgunnell.uselessfacts.fragments.favoritefacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.database.FactDatabase
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import edu.dixietech.pgunnell.uselessfacts.repository.FactRepository
import kotlinx.coroutines.launch

class FavoriteFactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FactRepository(FactDatabase.getInstance(application))

    private var _facts = MutableLiveData<List<Fact>>()
    val facts: LiveData<List<Fact>>
        get() = _facts

    fun getFacts() {
        viewModelScope.launch {
            _facts.postValue(repository.getFavoritedFacts())
        }
    }
}