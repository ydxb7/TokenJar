package ai.tomorrow.tokenjar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDatabaseDao {

    @Query("select * from history_table ORDER BY timeStamp DESC")
    fun getAllHistory(): LiveData<List<DatabaseHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg histories: DatabaseHistory)
}



