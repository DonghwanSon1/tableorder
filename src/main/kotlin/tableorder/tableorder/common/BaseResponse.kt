package tableorder.tableorder.common

data class BaseResponse<T>(
    val data: T? = null,
    val message: String? = null
)
