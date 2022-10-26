package tech.jour.template.common.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.jour.template.common.model.db.AccountBean

@Dao
interface AccountDao {
    @Query("SELECT * FROM AccountBean WHERE id = :id LIMIT 1")
    fun getUserById(id: Int): AccountBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: AccountBean)
}