package com.nolva.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nolva.common.utils.PageUtils;
import com.nolva.common.utils.Query;
import com.nolva.product.dao.CategoryDao;
import com.nolva.product.entity.CategoryEntity;
import com.nolva.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

//    因为实现类继承了ServiceImpl，其中加入了泛型Dao层和Entity层的实现
//    因此可以用baseMapper替代Dao层对象
//    private final CategoryDao categoryDao;
//
//    @Autowired
//    public CategoryServiceImpl(CategoryDao categoryDao) {
//        this.categoryDao = categoryDao;
//    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 以树形结构查出所有菜单
     * @return List<CategoryEntity>
     */
    @Override
    public List<CategoryEntity> listWithTree() {
//        1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
//        2.组装成父子的树形结构
//        2.1. 查询所有的一级分类
        assert Comparator.comparingInt(CategoryEntity::getSort) !=null;
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == 0
        ).peek((menu)-> menu.setChildren(getChildren(menu, entities))).
                sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
//        TODO:检查当前删除的菜单是否被别的地方引用
//        逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root 当前菜单(父分类)
     * @param all 所有的菜单集合
     * @return root菜单的所有子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {

        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).peek(categoryEntity -> {
//            1.找到子菜单
            categoryEntity.setChildren(getChildren(categoryEntity, all));
//            2.菜单的排序
        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());

        return children;
    }

}