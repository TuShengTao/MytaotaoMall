package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.service.impl.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbitemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public TbItem getItemById(long itemId) {
        TbItem item =tbitemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = tbitemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
/**
 * @author: tushengtao
 * @date: 2019/10/24
 * @Description: 添加一个商品 单表操作 利用mybatis 逆向生成工具
 * @param:
 * @return:
 */
    @Override
    public TaotaoResult createItem(TbItem tbitem,String desc) throws Exception {
        //item 补全
        //生成商品ID 调用IDUtils 工具类
        Long itemId= IDUtils.genItemId();
        tbitem.setId(itemId);
        // 商品状态status 1-正常 2-下架 3-删除
        tbitem.setStatus((byte)1);
        tbitem.setCreated(new Date());
        tbitem.setUpdated(new Date());
        //插入到数据库 添加商品
        tbitemMapper.insert(tbitem);
        //添加商品描述
        TaotaoResult result=insertItemDesc(itemId,desc);
        if (result.getStatus() != 200){
            //如果不是200 抛异常 事务应该交给spring管理
            throw new Exception();
        }

        // 同时添加进  索引库
        Item item=new Item();
        item.setId(Long.toString(tbitem.getId()));
        // 先从数据库查
        Item itemToSolr=getItemToSolr(item);
        // 查到数据再 存入 solr
        TaotaoResult solrResult=addItem(itemToSolr);
        if (solrResult.getStatus()!=200){
            //如果不是200 抛异常 事务应该交给spring管理
            throw new Exception();
        }
        //返回自定义的json格式
        return TaotaoResult.ok();
    }
    @Override
    public int deleteByPrimaryKey(Long id) {
        int result=0;
        // 删除商品表的商品
        int flag_item=tbitemMapper.deleteByPrimaryKey(id);
        // 删除商品描述表的商品描述
        int flag_desc=itemDescMapper.deleteByPrimaryKey(id);
        if (flag_desc ==1 || flag_item ==1){
            result=1;
        }
        return result;
    }

    private TaotaoResult insertItemDesc(Long itemId,String desc){
        /**
         * @author: tushengtao
         * @date: 2019/10/24
         * @Description: 添加商品描述 为了保证添加商品与添加描述在同一事务里
         * 应该在上面 商品添加的方法里调用此方法
         * @param: [desc]
         * @return: com.taotao.common.pojo.TaotaoResult
         */
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
    @Override
    public TaotaoResult getItemDesc(long itemId) {
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return TaotaoResult.ok(itemDesc);
    }
/**
 * @author: tushengtao
 * @date: 2019/12/20
 * @Description:  添加进solr 索引库 调用 taotao-search
 * @param:
 * @return:
 */
    @Override
    public TaotaoResult addItem(Item item) {
        String addItemUrl="http://localhost:8083/search/manager/addItem";
        Map<String,String> map=new HashMap<>();
        map.put("id",item.getId());
        map.put("title",item.getTitle());
        map.put("sellPoint",item.getSellPoint());
        map.put("price",Long.toString(item.getPrice()));
        map.put("image",item.getImage());
        map.put("categoryName",item.getCategoryName());
        map.put("itemDesc",item.getItemDesc());
        
        //发送post请求
        String json = HttpUtils.post(JsonUtils.objectToJson(item), addItemUrl);
//        String json = HttpClientUtil.doPost(addItemUrl,map);
        return TaotaoResult.ok(json);
    }
    @Override
    public Item getItemToSolr(Item item) {
        Item itemResult =itemMapper.getItemToSolr(item);
        return itemResult;
    }
}
