package tableorder.tableorder.domain.category

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import tableorder.tableorder.domain.category.rqrs.CategoryRs

@Repository
class CategoryCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : CategoryCustomRepository {

    private val category: QCategory = QCategory.category

    override fun findAllCategory(): List<CategoryRs> {
        return queryFactory
            .select(
                Projections.fields(
                    CategoryRs::class.java,
                    category.sn,
                    category.name,
                    category.createdAt,
                    category.updatedAt
                )
            )
            .from(category)
            .orderBy(category.order.asc())
            .fetch()
    }
}