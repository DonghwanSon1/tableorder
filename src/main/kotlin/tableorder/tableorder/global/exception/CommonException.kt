package tableorder.tableorder.global.exception

// TODO exception message가 응답을 주지 않는거 확인하기
class CommonException(val exceptionCode: CommonExceptionCode) : RuntimeException(exceptionCode.message) {

}