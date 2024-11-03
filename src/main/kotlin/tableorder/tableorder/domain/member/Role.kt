package tableorder.tableorder.domain.member

enum class Role(val desc: String) {
    SUPER_ADMIN("슈퍼 관리자"),
    SHOP_ADMIN("가게 관리자"),
    USER("일반 사용자")

}