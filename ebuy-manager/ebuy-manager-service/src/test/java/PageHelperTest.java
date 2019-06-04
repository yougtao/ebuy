import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongtao.ebuy.manager.mapper.ItemMapper;
import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.pojo.ItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest
{
    @Test
    public void testPageHelper() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ItemMapper itemMapper = context.getBean(ItemMapper.class);
        PageHelper.startPage(1, 10);
        ItemExample e = new ItemExample();
        List<Item> items = itemMapper.selectByExample(e);
        PageInfo pageInfo = new PageInfo(items, 10);
        for (Item item : items) {
            System.out.println(item.getId() + ": " + item.getTitle());
        }
    }


}
