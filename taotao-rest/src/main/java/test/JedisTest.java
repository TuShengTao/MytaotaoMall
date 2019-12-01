package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * Created on 2019/11/4
 *
 * @author Tu ShengTao
 * Description: 测试单机版Redis  使用本机的win版本 redis 本机ip：172.16.0.1 端口：6379
 */
public class JedisTest {
    @Test
    public void testJedisSingle(){
        //创建一个jedis 对象
        Jedis jedis=new Jedis("172.16.0.1",6379);
        //调用jedis对象的方法 方法名称与 redis命令一致
        jedis.set("key1","jedis test");
        String string=jedis.get("key1");
        System.out.println(string);
        //关闭jedis
        jedis.close();
    }

    /*
    * 单机版 使用连接池
    * */

    @Test
    public void testJedisPool(){
        //创建jedis连接池
        JedisPool pool=new JedisPool("172.16.0.1",6379);
        //从连接池 中获得jedis对象
        Jedis jedis=pool.getResource();
        String string=jedis.get("key1");
        System.out.println(string+"连接池测试！");
        //关闭
        jedis.close();
        pool.close();
    }
    /**
     * @author: tushengtao
     * @date: 2019/11/4
     * @Description:  Redis集群版测试 JedisCluster 自带连接池
     * @param:
     * @return:
     */
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.109.128",7001));
        nodes.add(new HostAndPort("192.168.109.128",7002));
        nodes.add(new HostAndPort("192.168.109.128",7003));
        nodes.add(new HostAndPort("192.168.109.128",7004));
        nodes.add(new HostAndPort("192.168.109.128",7005));
        nodes.add(new HostAndPort("192.168.109.128",7006));
        JedisCluster cluster=new JedisCluster(nodes);
        cluster.set("key1","999");
        String string=cluster.get("key1");
        System.out.println(string+"测试集群版Redis");
        cluster.close();

    }
    /**
     * @author: tushengtao
     * @date: 2019/11/4
     * @Description: spring 与 redis 单机版整合测试
     * @param:
     * @return:
     */
    @Test
    public void testSpringSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        pool.close();
    }
/**
 * @author: tushengtao
 * @date: 2019/11/4
 * @Description: spring 与 redis整合 集群版测试
 * @param:
 * @return:
 */
@Test
public void testSpringJedisCluster() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
    String string = jedisCluster.get("key1");
    System.out.println(string+" 测试！");
    jedisCluster.close();
    }
}
