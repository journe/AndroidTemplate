package tech.jour.template.common.model

data class AccountVipBean(
    val user: User,
    val vip: List<Vip>,
    val vip_record: List<String>
) {
    data class User(
        val member_vip: Int? = null,
        val vip_expired_at: String? = null,
    )

    data class Vip(
        val content: String? = null,
        val describe: String? = null,
        val duration: Int? = null,
        val is_main: Int? = null,
        val name: String? = null,
        val original_price: String? = null,
        val package_id: Int? = null,
        val vip: Int? = null,
        val price: String? = null,
        val tag_name: String? = null,
    )
}

