package tech.jour.template.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import tech.jour.template.common.model.db.AccountBean
import tech.jour.template.base.BaseApplication
import tech.jour.template.base.utils.ioThread
import tech.jour.template.common.room.dao.AccountDao

/**
 * Created by journey on 2020/5/18.
 */
@Database(
	entities = [AccountBean::class],
	version = 1,
	exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun accountDao(): AccountDao

	companion object {
		private const val DATABASE_NAME: String = "room.db"

		// For Singleton instantiation
		@Volatile
		private var instance: AppDatabase? = null
		fun getInstance(context: Context = BaseApplication.context): AppDatabase {
			return instance ?: synchronized(this) {
				instance ?: buildDatabase(context).also { instance = it }
			}
		}

		// Create and pre-populate the database. See this article for more details:
		// https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
		private fun buildDatabase(context: Context): AppDatabase {
			return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
				.fallbackToDestructiveMigration()
				.allowMainThreadQueries()
				.addCallback(object : Callback() {
					override fun onCreate(db: SupportSQLiteDatabase) {
						super.onCreate(db)
						//此处初始化数据库并插入默认值
						ioThread {
							getInstance(context).accountDao()
								.insert(
									AccountBean(
										id = 0,
										nickname = "SampleData",
										phone = "188-1235-4568"
									)
								)
						}
					}
				}).build()
		}
	}
}
