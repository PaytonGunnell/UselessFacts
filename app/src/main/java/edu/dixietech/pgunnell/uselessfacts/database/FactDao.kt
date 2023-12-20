package edu.dixietech.pgunnell.uselessfacts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import edu.dixietech.pgunnell.uselessfacts.model.Fact

@Dao
interface FactDao {

//    @Upsert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fact: Fact)

    @Query("SELECT * FROM fact_table WHERE id = :factId LIMIT 1")
    suspend fun getFactWithId(factId: String): Fact

    @Query("SELECT * FROM fact_table ORDER BY text DESC")
    suspend fun getFacts(): List<Fact>

    @Delete
    suspend fun delete(fact: Fact)
}