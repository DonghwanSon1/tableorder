package tableorder.tableorder.domain.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import tableorder.tableorder.domain.category.Category

interface MenuRepository: JpaRepository<Menu, String>, MenuCustomRepository {

    fun findBySnIn(sn: List<Long>): List<Menu>

    fun findByCategorySn(categorySn: Long): List<Menu>?


}