package edu.dixietech.pgunnell.uselessfacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import edu.dixietech.pgunnell.uselessfacts.utils.URLConverter

@Database(entities = [Fact::class], version = 1, exportSchema = false)
@TypeConverters(URLConverter::class)
abstract class FactDatabase: RoomDatabase() {

    abstract val dao: FactDao

    companion object {
        @Volatile
        private var INSTANCE: FactDatabase? = null

        fun getInstance(context: Context): FactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FactDatabase::class.java,
                        "fact_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}