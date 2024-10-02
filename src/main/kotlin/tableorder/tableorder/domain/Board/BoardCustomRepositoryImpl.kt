package tableorder.tableorder.domain.Board

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class BoardCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : BoardCustomRepository {

    private val qBoard: QBoard = QBoard.board

    override fun findAllBoards(): List<Board> {
        return queryFactory
            .selectFrom(qBoard)
            .fetch()
    }
}