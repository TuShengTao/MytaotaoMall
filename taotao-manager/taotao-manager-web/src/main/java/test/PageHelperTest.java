package test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created on 2019/10/15
 *
 * @author Tu ShengTao
 * Description:测试分页类
 */
public class PageHelperTest {
    /**
     * Created on: 2019/10/15
     * Author: Tu Sheng Tao
     * Description: 测试商品分页的方法
    */
    @Test
    public void testPageHelper(){
        //创建一个Spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //从容器获得mapper代理对象
        TbItemMapper mapper=applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> list = mapper.selectByExample(example);
        //取商品列表
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品："+total);
    }

}
