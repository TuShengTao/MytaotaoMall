package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItem getItemById(long itemId) {
        TbItem item =itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
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
    public TaotaoResult createItem(TbItem item,String desc) throws Exception {
        //item 补全
        //生成商品ID 调用IDUtils 工具类
        Long itemId= IDUtils.genItemId();
        item.setId(itemId);
        // 商品状态status 1-正常 2-下架 3-删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库 添加商品
        itemMapper.insert(item);
        //添加商品描述
        TaotaoResult result=insertItemDesc(itemId,desc);
        if (result.getStatus() != 200){
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
        int flag_item=itemMapper.deleteByPrimaryKey(id);
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
}
