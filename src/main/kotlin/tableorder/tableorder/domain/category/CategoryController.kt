package tableorder.tableorder.domain.category

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "카테고리 관련 API")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    @Operation(summary = "카테고리 저장", description = "카테고리를 저장합니다.")
    fun saveCategory(@RequestBody categoryRq: CategoryRq) {
        return categoryService.saveCategory(categoryRq)
    }

    @GetMapping
    @Operation(summary = "카테고리 조회", description = "전체 카테고리를 조회합니다.")
    fun searchCategory(): List<CategoryRs> {
        return categoryService.search()
    }

    @PatchMapping
    @Operation(summary = "카테고리 수정", description = "카테고리를 수정합니다.")
    fun updateCategory(@RequestBody categoryRq: CategoryRq) {
        return categoryService.saveCategory(categoryRq)
    }

    @PostMapping("/delete")
    @Operation(summary = "카테고리 다중 삭제", description = "다중 카테고리를 완전 삭제합니다.")
    fun deleteCategory(@RequestBody snList: List<Long>) {
        return categoryService.deleteCategory(snList)
    }
}