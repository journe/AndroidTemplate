package tech.jour.template.common.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountBean(
	@PrimaryKey(autoGenerate = true)
	var id: Int,
	var nickname: String? = null,
	var phone: String? = null,
) {
	constructor(name: String, pass: String) : this(0, name, pass)
}