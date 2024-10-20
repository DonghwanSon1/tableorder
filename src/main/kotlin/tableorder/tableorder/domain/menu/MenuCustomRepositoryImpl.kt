package tableorder.tableorder.domain.menu

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs

@Repository
class MenuCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : MenuCustomRepository {

    private val menu: QMenu = QMenu.menu


    override fun searchByCategorySn(categorySn: Long): List<MenuDetailRs>? {
        return queryFactory
                .select(
                        Projections.fields(
                                MenuDetailRs::class.java,
                                menu.sn,
                                menu.name,
                                menu.imageUrl,
                                menu.createdAt,
                                menu.updatedAt
                        )
                )
                .from(menu)
                .where(menu.categorySn.eq(categorySn))
                .orderBy(menu.order.asc())
                .fetch()
    }
}