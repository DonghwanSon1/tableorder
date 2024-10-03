package tableorder.tableorder.domain.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CategoryRepository: JpaRepository<Category, String>, CategoryCustomRepository {

    fun findBySn(sn: Long): Category

    @Modifying
    @Query("  DELETE FROM Category c WHERE c.sn IN :snList")
    fun deleteBySnList(snList: List<Long>)

}