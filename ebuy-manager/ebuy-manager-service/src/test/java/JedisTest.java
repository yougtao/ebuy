import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest
{
    // 单机
    //@Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.22.129", 6379);
        String v = jedis.get("jedisPoolTest");
        System.out.println("jedisTest: " + v);
        jedis.close();
    }

    // 连接池
    //@Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.22.129", 6379);
        Jedis jedis = jedisPool.getResource();
        String v = jedis.get("jedisPoolTest");
        System.out.println("jedisPoolTest: " + v);
        jedis.close();
    }

    // 集群版
   // @Test
    public void restJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.22.129", 9001));
        nodes.add(new HostAndPort("192.168.22.129", 9002));
        nodes.add(new HostAndPort("192.168.22.129", 9003));
        nodes.add(new HostAndPort("192.168.22.129", 9004));
        nodes.add(new HostAndPort("192.168.22.129", 9005));
        nodes.add(new HostAndPort("192.168.22.129", 9006));
        JedisCluster cluster = new JedisCluster(nodes);

        String v = cluster.get("clusterTest");
        System.out.println("clusterTest: " + v);
        v = cluster.get("clusterTest");
        System.out.println("clusterTest: " + v);
    }

}
