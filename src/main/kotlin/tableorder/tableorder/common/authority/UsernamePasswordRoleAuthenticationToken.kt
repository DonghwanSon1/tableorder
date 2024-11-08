package tableorder.tableorder.common.authority

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import tableorder.tableorder.domain.member.Role

class UsernamePasswordRoleAuthenticationToken(principal: String, credentials: String, val role: Role)
    : UsernamePasswordAuthenticationToken(principal, credentials)