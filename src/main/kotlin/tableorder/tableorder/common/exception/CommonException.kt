package tableorder.tableorder.common.exception

class CommonException(val exceptionCode: CommonExceptionCode) : RuntimeException(exceptionCode.message) {

}