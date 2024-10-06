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
    fun saveCategory(categoryRq: List<CategoryRq>) {
        val entityList = ArrayList<Category>()
        val categoryMap = HashMap<Long, CategoryRq>()

        categoryRq.forEach { rq ->
            // 만약 카테고리 sn이 있다면,
            if (rq.sn != null) {
                // 수정이므로 한번에 조회 할 수 있도록 snList를 따로 빼고, Map으로 변경될 내용들을 저장한다.
                categoryMap[rq.sn] = rq
            }
            else {
                // sn 없다면, 신규 저장이므로 생성 후 저장한다.
                entityList.add(Category.createCategory(rq))
            }
        }

        // Map이 비워있지 않다면, 수정할 값이 있다고 판단한다.
        if (!categoryMap.isNullOrEmpty()) {
            // 한번에 조회 할 수 있도록 List로 담고, 조회 후 엔티티들을 가져온다.
            val snList: List<Long> = categoryMap.keys.toList()
            val categoryEntities: List<Category> = categoryRepository.findBySnIn(snList)
            // 가져온 엔티티들을 돌려 미리 Map으로 rq를 담아놓은걸을 매칭하여 해당하는 sn을 통해 update 한다.
            categoryEntities.forEach { category ->
                if (categoryMap.containsKey(category.sn)) {
                    entityList.add(category.updateCategory(categoryMap[category.sn]!!))
                }
            }
        }

        // 생성/수정 된 엔티티들을 저장/수정 한다.
        categoryRepository.saveAll(entityList)
    }

    @Transactional
    fun deleteCategory(snList: List<Long>) {
        categoryRepository.deleteBySnList(snList)
    }
}