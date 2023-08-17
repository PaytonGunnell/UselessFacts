package edu.dixietech.pgunnell.uselessfacts.repository

import edu.dixietech.pgunnell.uselessfacts.database.FactDatabase
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import edu.dixietech.pgunnell.uselessfacts.networking.UselessFactsService

class FactRepository(
    private val database: FactDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getRandomFact(): Fact {
        return withContext(dispatcher) {
            return@withContext UselessFactsService.getRandomFact()
        }
    }

    suspend fun getFavoritedFacts(): List<Fact> {
        return withContext(dispatcher) {
            return@withContext database.dao.getFacts()
        }
    }

    suspend fun favoriteFact(fact: Fact) {
        withContext(dispatcher) {
            fact.isFavorited = true
            database.dao.insert(fact)
        }
    }

    suspend fun unFavoriteFact(fact: Fact) {
        withContext(dispatcher) {
            fact.isFavorited = false
            database.dao.delete(fact)
        }
    }
}