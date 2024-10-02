package tableorder.tableorder.domain.Board

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: String? = null,

    @Column(name = "username")
    val name: String? = null,

    @Column(name = "email")
    val email: String? = null,

    @Column(name = "password")
    val password: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,


    )