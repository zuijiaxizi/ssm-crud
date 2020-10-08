package com.lzx.service;

import com.lzx.bean.Employee;
import com.lzx.bean.EmployeeExample;
import com.lzx.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ezio
 * @date 2020/10/3 19:42
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     *
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     *
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检验当前用户名是否可用
     *
     * @param empName
     * @return true：代表当前用户名可用  false：代表当前用户名不可用
     */
    public boolean checkUser(String empName) {
        //相当于select count(*) from tbl_emp where emp_name=empName
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);

        return count == 0;
    }

    /**
     * 查询单个员工
     *
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新员工信息
     *
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据id删除员工
     *
     * @param id
     */
    public void delEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除，传入id集合
     *
     * @param idList
     */
    public void delBatch(List<Integer> idList) {
        //相当于 delete from xxx where emp_id in(1,2,3...)
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(idList);

        employeeMapper.deleteByExample(example);
    }
}
