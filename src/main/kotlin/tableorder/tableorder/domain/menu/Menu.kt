package tableorder.tableorder.domain.menu

import jakarta.persistence.*
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import java.time.LocalDateTime

@Entity
@Table(name = "menu")
data class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @Column(name = "category_sn")
    val categorySn: Long? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "order")
    val order: Long? = null,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
) {

    companion object {
        fun createMenu(menuRq: MenuRq, categorySn: Long): Menu {
            return Menu(
                categorySn = categorySn,
                name = menuRq.name,
                order = menuRq.order,
                imageUrl = menuRq.imageUrl,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    fun updateMenu(menuRq: MenuRq): Menu {
        return Menu(
            sn = this.sn,
            categorySn = this.categorySn,
            name = menuRq.name ?: this.name,
            order = menuRq.order ?: this.order,
            imageUrl = menuRq.imageUrl ?: this.imageUrl,
            createdAt = this.createdAt ?: LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}