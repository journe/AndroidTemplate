package tech.jour.template.common.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.jour.template.common.model.db.AccountBean

@Dao
interface AccountDao {
	@Query("SELECT * FROM AccountBean WHERE id = :id LIMIT 1")
	fun getUserById(id: Int): AccountBean?

	@Query("SELECT * FROM AccountBean WHERE id = :id LIMIT 1")
	fun getUserByIdFlow(id: Int): Flow<AccountBean?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(obj: AccountBean): Long

	@Query("SELECT * FROM AccountBean WHERE rowid = :rowId")
	fun getUserByRowId(rowId: Long): AccountBean?

	@Query("SELECT * FROM AccountBean")
	fun getAllUsers():Flow<List<AccountBean>?>
}