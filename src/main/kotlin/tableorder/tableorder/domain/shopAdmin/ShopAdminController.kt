package tableorder.tableorder.domain.shopAdmin

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.category.CategoryService
import tableorder.tableorder.domain.menu.MenuService
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs

@RestController
@RequestMapping("/shop-admin")
@Tag(name = "ShopAdmin", description = "가게 관리자 전용 API")
class ShopAdminController(
    private val categoryService: CategoryService,
    private val menuService: MenuService,
) {
    @PostMapping("/category")
    @Operation(summary = "카테고리 다중 저장", description = "카테고리를 다중 저장합니다.")
    fun saveCategory(@RequestBody categoryRqList: List<CategoryRq>) {
        // 비워있거나 Null로 들어올 시 Exception 발생
        if (categoryRqList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        categoryService.saveCategory(categoryRqList)
    }

    @PatchMapping("/category")
    @Operation(summary = "카테고리 다중 수정", description = "카테고리를 다중 수정합니다.")
    fun updateCategory(@RequestBody categoryRqList: List<CategoryRq>) {
        // 비워있거나 Null로 들어올 시 Exception 발생
        if (categoryRqList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        categoryService.saveCategory(categoryRqList)
    }

    @PostMapping("/category/delete")
    @Operation(summary = "카테고리 다중 삭제", description = "다중 카테고리를 완전 삭제합니다.")
    fun deleteCategory(@RequestBody snList: List<Long>) {
        categoryService.deleteCategory(snList)
    }

    @PostMapping("/menu/{categorySn}")
    @Operation(summary = "메뉴 다중 저장", description = "카테고리에 속한 메뉴들을 다중 저장합니다.")
    fun saveMenu(@PathVariable categorySn: Long,
                 @RequestBody menuRq: MenuRq) {
        // 비워있거나 Null로 들어올 시 Exception 발생
        if (menuRq.menuDetailList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        menuService.saveMenu(categorySn, menuRq)
    }

    @PatchMapping("/menu/{categorySn}")
    @Operation(summary = "메뉴 다중 수정", description = "메뉴를 다중 수정합니다.")
    fun updateMenu(@PathVariable categorySn: Long,
                   @RequestBody menuRq: MenuRq) {
        // menuDetailList 와 삭제할 메뉴들이 비워있거나 Null로 들어올 시 Exception 발생
        if (menuRq.menuDetailList.isNullOrEmpty() && menuRq.deleteSnList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        menuService.saveMenu(categorySn, menuRq)
    }

    @DeleteMapping("/menu/{categorySn}")
    @Operation(summary = "카테고리 속한 메뉴 전체 삭제", description = "카테고리에 속한 메뉴 전체를 완전 삭제합니다.")
    fun deleteAllMenu(@PathVariable categorySn: Long,) {
        menuService.deleteAllMenu(categorySn)
    }
}