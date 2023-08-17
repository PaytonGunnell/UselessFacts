package edu.dixietech.pgunnell.uselessfacts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.dixietech.pgunnell.uselessfacts.model.Fact

@Dao
interface FactDao {

    @Insert
    suspend fun insert(fact: Fact)

    @Query("SELECT * FROM fact_table ORDER BY text DESC")
    suspend fun getFacts(): List<Fact>

    @Delete
    suspend fun delete(fact: Fact)
}