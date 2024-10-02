package tableorder.tableorder.domain.Board

import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    fun searchUser(): List<Board> {
        return boardRepository.findAllBoards()

    }
}