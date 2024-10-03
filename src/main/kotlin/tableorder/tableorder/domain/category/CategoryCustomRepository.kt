package tableorder.tableorder.domain.category

import tableorder.tableorder.domain.category.rqrs.CategoryRs

interface CategoryCustomRepository {

    fun findAllCategory(): List<CategoryRs>
}