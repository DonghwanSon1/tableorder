package tableorder.tableorder.domain.Board

import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, String>, BoardCustomRepository {

}