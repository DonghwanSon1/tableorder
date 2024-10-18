package tableorder.tableorder.global.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionCode(
        val status: HttpStatus,
        val message: String
) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "해당 카테고리는 존재하지 않습니다."),
}