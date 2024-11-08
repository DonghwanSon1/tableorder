package tableorder.tableorder.common.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionCode(
        val status: HttpStatus,
        val message: String
) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    NO_CATEGORY_NAME(HttpStatus.BAD_REQUEST, "카테고리 이름은 필수입니다."),
    INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "해당 카테고리는 존재하지 않습니다."),
    IMAGE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "이미지를 선택해주세요."),
    NO_IMAGE_EXISTS(HttpStatus.BAD_REQUEST, "해당 이미지가 존재하지 않습니다."),
    NO_MENU_PRICE(HttpStatus.BAD_REQUEST, "메뉴의 가격은 필수 입니다."),
    DUPLICATE_ID(HttpStatus.BAD_REQUEST, "중복된 아이디가 있습니다."),
    NO_ROLE(HttpStatus.BAD_REQUEST, "해당 역할은 존재하지 않습니다."),
    USER_NAME_NOT_FOUND(HttpStatus.FORBIDDEN, "해당 유저가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.FORBIDDEN, "비밀번호를 확인해주세요."),

}