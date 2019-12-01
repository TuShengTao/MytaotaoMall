package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/31
 *
 * @author Tu ShengTao
 * Description:实现网站首页 左上侧栏商品分类 鼠标放上面发送ajax跨域请求 显示各级分类
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public CatResult getItemCatList() {
        CatResult catResult=new CatResult();
        //parentId设置为0 根节点
        catResult.setData(getCatList(0));
        return catResult;
    }
    /**
     * @author: tushengtao
     * @date: 2019/10/31
     * @Description:  查询分类列表 传入 父节点id
     * @param:
     * @return:
     */
    private List<?> getCatList(long parentId){
        //创建查询条件
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list=itemCatMapper.selectByExample(example);
        //返回值 list
        List resultList=new ArrayList<>();
        //向list 中添加节点

        // 暂时解决首页 商品分类超出界面问题 加判断 限制为 14个
        int count=0;


        for (TbItemCat tbItemCat: list){
            //判断是否为父节点
            if (tbItemCat.getIsParent()){
                CatNode catNode=new CatNode();
                //判断是否有父亲节点
                if (parentId==0){
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                }else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");

                //  ！！！ 关键点： 递归调用函数 查找此catNode对象 是否有
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                // 只需在商品分类第一层级 判断 如果大于14 break
                count++;
                if (parentId==0&&count >= 14){
                    break;
                }
                //如果是 叶子节点 直接添加 字符串
            }else {
                resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
        return resultList;
    }

}
