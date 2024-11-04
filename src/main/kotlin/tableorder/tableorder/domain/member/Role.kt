package tableorder.tableorder.domain.member

enum class Role(val desc: String) {
    ADMIN("슈퍼 관리자"),
    SHOP("가게 관리자"),
    USER("일반 사용자")
}