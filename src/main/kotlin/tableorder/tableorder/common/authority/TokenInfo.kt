package tableorder.tableorder.common.authority

import tableorder.tableorder.domain.member.Role


data class TokenInfo(
    val grantType: String,
    val accessToken: String,
    val role: String?
)
