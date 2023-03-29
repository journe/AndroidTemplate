package tech.jour.template.common.model.request

data class PutAccountBody(
	val nickname: String? = null,
	val avatar: String? = null,
)