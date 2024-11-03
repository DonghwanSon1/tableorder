package tableorder.tableorder.domain.menu


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.category.CategoryRepository
import tableorder.tableorder.domain.category.CategoryService
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRq
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.global.exception.CommonException
import tableorder.tableorder.global.exception.CommonExceptionCode

@Service
@Transactional(readOnly = true)
class MenuService(
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository
) {

    @Transactional
    fun saveMenu(categorySn: Long, menuRq: MenuRq) {
        // 카테고리 유효성 체크
        this.checkCategorySn(categorySn)

        if (!menuRq.deleteSnList.isNullOrEmpty()) this.deleteMenu(menuRq.deleteSnList)

        // 담을 변수 선언
        val entityList = ArrayList<Menu>()
        val menuMap = HashMap<Long, MenuDetailRq>()

        menuRq.menuDetailList!!.forEach { menu ->
            // 이미지를 안주면 Exception 발생 (필수)
            if (menu.imageUrl.isNullOrEmpty()) throw CommonException(CommonExceptionCode.IMAGE_BAD_REQUEST)
            // 만약 카테고리 sn이 있다면,
            if (menu.sn != null) {
                // 수정이므로 한번에 조회 할 수 있도록 snList를 따로 빼고, Map으로 변경될 내용들을 저장한다.
                menuMap[menu.sn] = menu
            }
            else {
                // 메뉴 가격를 주지 않으면 Exception 발생 (필수)
                if (menu.price == null) throw CommonException(CommonExceptionCode.NO_MENU_PRICE)
                // sn 없다면, 신규 저장이므로 생성 후 저장한다.
                entityList.add(Menu.createMenu(menu, categorySn))
            }
        }

        // Map이 비워있지 않다면, 수정할 값이 있다고 판단한다.
        if (!menuMap.isNullOrEmpty()) {
            // 한번에 조회 할 수 있도록 List로 담고, 조회 후 엔티티들을 가져온다.
            val snList: List<Long> = menuMap.keys.toList()
            val menuEntities: List<Menu> = menuRepository.findBySnIn(snList)

            // 가져온 엔티티들을 돌려 미리 Map으로 rq를 담아놓은걸을 매칭하여 해당하는 sn을 통해 update 한다.
            menuEntities.forEach { menu ->
                if (menuMap.containsKey(menu.sn)) {
                    entityList.add(menu.updateMenu(menuMap[menu.sn]!!))
                }
            }
        }

        // 생성/수정 된 엔티티들을 저장/수정 한다.
        menuRepository.saveAll(entityList)
    }

    fun searchMenu(categorySn: Long): MenuRs {
        // 카테고리 유효성 검사 및 가져오기
        val category: Category? = this.checkCategorySn(categorySn)
        val menuList: List<MenuDetailRs>? = menuRepository.searchByCategorySn(categorySn)

        return MenuRs.createMenuRs(category!!, menuList)
    }

    @Transactional
    fun deleteAllMenu(categorySn: Long) {
        // 카테고리 유효성 체크
        this.checkCategorySn(categorySn)
        // 카테고리에 속한 메뉴들을 가져와 메뉴Sn을 담은 후 deleteMenu 메서드로 보낸다.
        val menuList: List<Long>? = menuRepository.searchByCategorySn(categorySn)?.map { it.sn!! }
        this.deleteMenu(menuList!!)
    }


    private fun checkCategorySn(categorySn: Long): Category? {
        // 카테고리 유효성 검사
        return categoryRepository.findBySn(categorySn) ?: throw CommonException(CommonExceptionCode.INVALID_CATEGORY)
    }

    private fun deleteMenu(menuSnList: List<Long>) {
        // 메뉴Sn들을 가져와 엔티티들을 가져온 후 삭제 한다.
        val menuEntityList: List<Menu> = menuRepository.findBySnIn(menuSnList)
        menuRepository.deleteAll(menuEntityList)
    }

}