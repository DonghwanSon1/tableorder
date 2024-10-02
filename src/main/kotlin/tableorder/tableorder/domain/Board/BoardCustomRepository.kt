package tableorder.tableorder.domain.Board

interface BoardCustomRepository {

    fun findAllBoards(): List<Board>
}