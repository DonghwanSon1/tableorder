package tableorder.tableorder.domain.menu

import jakarta.persistence.*
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRq
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

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "price")
    val price: Long? = null,

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
        fun createMenu(menuDetailRq: MenuDetailRq, categorySn: Long): Menu {
            return Menu(
                categorySn = categorySn,
                name = menuDetailRq.name,
                description = menuDetailRq.description,
                price = menuDetailRq.price,
                order = menuDetailRq.order ?: 0,
                imageUrl = menuDetailRq.imageUrl,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    fun updateMenu(menuDetailRq: MenuDetailRq): Menu {
        return Menu(
            sn = this.sn,
            categorySn = this.categorySn,
            name = menuDetailRq.name ?: this.name,
            description = menuDetailRq.description ?: this.description,
            price = menuDetailRq.price ?: this.price,
            order = menuDetailRq.order ?: this.order,
            imageUrl = menuDetailRq.imageUrl ?: this.imageUrl,
            createdAt = this.createdAt ?: LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}