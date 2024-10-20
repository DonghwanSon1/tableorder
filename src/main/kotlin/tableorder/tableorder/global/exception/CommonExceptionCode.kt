package tableorder.tableorder.global.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionCode(
        val status: HttpStatus,
        val message: String
) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "해당 카테고리는 존재하지 않습니다."),
    IMAGE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "이미지를 선택해주세요."),
    NO_IMAGE_EXISTS(HttpStatus.BAD_REQUEST, "해당 이미지가 존재하지 않습니다."),
}