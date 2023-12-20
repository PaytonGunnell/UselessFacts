package edu.dixietech.pgunnell.uselessfacts.fragments.factdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.dixietech.pgunnell.uselessfacts.database.FactDatabase
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import edu.dixietech.pgunnell.uselessfacts.repository.FactRepository
import kotlinx.coroutines.launch

class FactDetailViewModel(
    application: Application,
    private val factId: String
): AndroidViewModel(application) {

    private val repository = FactRepository(FactDatabase.getInstance(application))

    private var _fact = MutableLiveData<Fact>()
    val fact: LiveData<Fact>
        get() = _fact

    suspend fun getFact() {
        viewModelScope.launch {
            _fact.postValue(repository.getFactWithId(factId))
        }
    }

    class Factory(
        private val application: Application,
        private val factId: String
    ): ViewModelProvider.Factory {

        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FactDetailViewModel::class.java)) {
                return FactDetailViewModel(application, factId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}