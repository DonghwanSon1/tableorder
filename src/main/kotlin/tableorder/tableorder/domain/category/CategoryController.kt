package tableorder.tableorder.domain.category

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "카테고리 관련 API")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    @Operation(summary = "카테고리 전체 조회", description = "전체 카테고리를 전체 조회합니다.")
    fun searchCategory(): List<CategoryRs> {
        return categoryService.search()
    }

}