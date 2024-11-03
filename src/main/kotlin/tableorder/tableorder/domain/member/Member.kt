package tableorder.tableorder.domain.member

import jakarta.persistence.*
import tableorder.tableorder.domain.member.rqrs.MemberRq
import java.time.LocalDate

@Entity
@Table(name = "admin-member")
data class Member(

    // TODO 정규식 판별 넣기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @Column(name = "id")
    val id: String? = null,

    @Column(name = "password")
    val password: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate? = null,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role? = null,

    @Column(name = "email")
    val email: String? = null,
) {

    companion object {
        fun createMember(memberRq: MemberRq): Member {
            return Member(
                id = memberRq.id,
                password = memberRq.password,
                name = memberRq.name,
                birthDate = memberRq.birthDate,
//                role = memberRq.role,
                email = memberRq.email
            )
        }
    }
}