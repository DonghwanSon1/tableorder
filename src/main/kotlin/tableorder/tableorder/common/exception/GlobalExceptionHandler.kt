package tableorder.tableorder.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CommonException::class)
    fun handleCommonException(ex: CommonException): ResponseEntity<ErrorResponse> {
        val status = ex.exceptionCode.status
        val response = ErrorResponse(status.value(), ex.exceptionCode.message)

        return ResponseEntity(response, status)
    }
}