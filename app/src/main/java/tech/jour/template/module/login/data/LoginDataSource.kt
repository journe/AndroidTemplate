package tech.jour.template.module.login.data

import tech.jour.template.common.model.db.AccountBean
import tech.jour.template.common.room.AppDatabase
import tech.jour.template.common.room.dao.AccountDao
import tech.jour.template.module.login.data.model.LoggedInUser
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class LoginDataSource {

	fun register(username: String, password: String): Result<LoggedInUser> {
		try {
			val accountDao: AccountDao = AppDatabase.getInstance().accountDao()
			val rowId = accountDao.insert(AccountBean(name = username, pass = password))
			val bean = accountDao.getUserByRowId(rowId)
			return if (bean != null) {
				Result.Success(LoggedInUser(bean.id.toString(), bean.nickname ?: ""))
			} else {
				Result.Error(IOException("register failed", Exception()))
			}
		} catch (e: Throwable) {
			return Result.Error(IOException("Error register", e))
		}
	}

	fun login(username: String, password: String): Result<LoggedInUser> {
		try {
			// TODO: handle loggedInUser authentication
			val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
			return Result.Success(fakeUser)
		} catch (e: Throwable) {
			return Result.Error(IOException("Error logging in", e))
		}
	}

	fun logout() {
		// TODO: revoke authentication
	}
}