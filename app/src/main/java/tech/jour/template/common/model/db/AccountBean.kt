package tech.jour.template.common.model.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class AccountBean(
    @PrimaryKey
    val id: Int = 0,
    val nickname: String? = null,
    var phone: String? = null,
) {
    @Ignore
    constructor() : this(0)
}