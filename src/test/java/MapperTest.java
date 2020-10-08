import com.lzx.bean.Department;
import com.lzx.bean.Employee;
import com.lzx.dao.DepartmentMapper;
import com.lzx.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author ezio
 * @date 2020/10/2 18:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void test01() {
        //测试部门插入
        departmentMapper.insertSelective(new Department(null, "人事部"));
        departmentMapper.insertSelective(new Department(null, "财务部"));
    }

    @Test
    public void test02() {
        //测试员工插入
        employeeMapper.insertSelective(new Employee(2, "南墙", "M", "nanqiang@qq.com", 1));
    }

    @Test
    public void test03() {
        //测试批量插入
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, "M", uid + "@random.com", 1));

        }
    }

    @Test
    public void test04() {
        Employee employee = employeeMapper.selectByPrimaryKey(2);
        System.out.println(employee);
    }
}
