package tableorder.tableorder.domain.Board

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class BoardCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : BoardCustomRepository {

    private val board: QBoard = QBoard.board

    override fun findAllBoards(): List<Board> {
        return queryFactory
            .select(
                Projections.fields(
                    Board::class.java,
                    board.id,
                    board.name,
                    board.email,
                    board.password,
                    board.createdAt,
                    board.updatedAt
                )
            )
            .from(board)
            .fetch()
    }
}