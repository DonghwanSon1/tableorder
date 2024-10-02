package tableorder.tableorder.domain.Board

import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping("/search")
    fun searchUser(session: HttpSession): List<Board> {
        println(session.id)
        return boardService.searchUser()
    }
}