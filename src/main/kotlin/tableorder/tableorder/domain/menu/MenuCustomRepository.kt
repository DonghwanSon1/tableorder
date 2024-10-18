package tableorder.tableorder.domain.menu

import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs

interface MenuCustomRepository {

    fun findByCategorySn(categorySn: Long): List<MenuDetailRs>?
}