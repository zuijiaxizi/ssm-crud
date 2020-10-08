package com.lzx.controller;

import com.lzx.bean.Department;
import com.lzx.service.DepartmentService;
import com.lzx.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ezio
 * @date 2020/10/6 9:42
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 返回所有的部门信息
     *
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts", list);
    }
}
