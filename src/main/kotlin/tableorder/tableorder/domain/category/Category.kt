package tableorder.tableorder.domain.category

import jakarta.persistence.*
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import java.time.LocalDateTime

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "order")
    val order: Long? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
) {

    companion object {
        fun createCategory(categoryRq: CategoryRq): Category {
            return Category(
                name = categoryRq.name,
                order = categoryRq.order,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    fun updateCategory(categoryRq: CategoryRq): Category {
        return Category(
            sn = this.sn,
            name = categoryRq.name ?: this.name,
            order = categoryRq.order ?: this.order,
            createdAt = this.createdAt ?: LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}