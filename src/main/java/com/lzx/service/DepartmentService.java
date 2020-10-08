package com.lzx.service;

import com.lzx.bean.Department;
import com.lzx.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ezio
 * @date 2020/10/6 9:44
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 获得所有部门信息
     *
     * @return
     */
    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
