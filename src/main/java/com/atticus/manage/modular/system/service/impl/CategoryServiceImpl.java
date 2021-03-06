package com.atticus.manage.modular.system.service.impl;

import com.atticus.manage.core.common.node.MenuNode;
import com.atticus.manage.core.common.node.ZTreeNode;
import com.atticus.manage.core.util.Pager;
import com.atticus.manage.modular.system.dao.CategoryMapper;
import com.atticus.manage.modular.system.dao.SiteMapper;
import com.atticus.manage.modular.system.model.Category;
import com.atticus.manage.modular.system.model.Site;
import com.atticus.manage.modular.system.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author atticus
 * @Date 2019/7/21 15:17
 * 分类相关业务接口实现类
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SiteMapper siteMapper;

    public static Pager<Category> pager = null;


    public List<Category> getCatogry(Map<String, Object> map) {
        return categoryMapper.getCatogry(map);
    }

    public List<MenuNode> getCatogryNode(Map<String, Object> map) {
        return categoryMapper.getCatogryNode(map);
    }

    public List<ZTreeNode> tree() {
        return categoryMapper.tree();
    }


    public List<Category> getCatogrySite(Map<String, Object> map) {
        List<Category> categoryList = categoryMapper.getList(null);
        List<Site> siteList = siteMapper.getList(null);
        for (Category category:categoryList) {
            List<Site> sites = new ArrayList<>();
            for (Site site:siteList){
                if (site.getCategoryId() == category.getId()){
                    sites.add(site);
                }

            }
            category.setSites(sites);
        }
        return categoryList;
    }


}
