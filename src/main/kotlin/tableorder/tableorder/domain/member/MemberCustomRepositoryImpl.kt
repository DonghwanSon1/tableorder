package tableorder.tableorder.domain.member

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs

@Repository
class MemberCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : MemberCustomRepository {

    private val member: QMember = QMember.member


}