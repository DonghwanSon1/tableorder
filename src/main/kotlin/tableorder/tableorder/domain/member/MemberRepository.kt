package tableorder.tableorder.domain.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface MemberRepository: JpaRepository<Member, Long>, MemberCustomRepository {

    fun findByIdAndRole(id: String, role: String): Member?
}