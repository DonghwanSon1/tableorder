package tableorder.tableorder.domain.category


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun search(): List<CategoryRs> {
        return categoryRepository.findAllCategory()
    }

    @Transactional
    fun saveCategory(categoryRq: CategoryRq) {

        // 만약 카테고리 sn이 있다면,
        if (categoryRq.sn != null) {
            // 수정이므로 조회 후 변경하여 저장한다.
            val category: Category = categoryRepository.findBySn(categoryRq.sn)
            categoryRepository.save(category.updateCategory(categoryRq))
        } else {
            // 없다면, 신규 저장이므로 생성 후 저장한다.
            categoryRepository.save(Category.createCategory(categoryRq))
        }
    }

    @Transactional
    fun deleteCategory(snList: List<Long>) {
        categoryRepository.deleteBySnList(snList)
    }
}