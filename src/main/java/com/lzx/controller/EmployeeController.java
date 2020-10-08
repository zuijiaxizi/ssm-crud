package com.lzx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzx.bean.Employee;
import com.lzx.service.EmployeeService;
import com.lzx.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ezio
 * @date 2020/10/2 20:27
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 根据id删除用户
     * 整合单个删除和批量删除
     * 批量删除1-2-3
     * 单个删除1
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            //批量删除
            List<Integer> del_ids = new ArrayList<>();
            String[] idList = ids.split("-");
            //取到String数组中的值拼接为Integer数组
            for (String id : idList) {
                del_ids.add(Integer.valueOf(id));
            }
            employeeService.delBatch(del_ids);
        } else {
            //单个删除
            int id = Integer.parseInt(ids);
            employeeService.delEmp(id);
        }
        return Msg.success();
    }

    /**
     * 更新员工信息
     *
     * @return
     */
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee, HttpServletRequest request) {
        System.out.println(request.getParameter("gender"));
        System.out.println("将要更新的员工数据" + employee);
        employeeService.updateEmp(employee);
        System.out.println("调用成功");
        return Msg.success();
    }

    /**
     * 查询单个员工
     *
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    /**
     * 查询所有员工
     *
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //引入PageHelper分页插件
        //在查询之前调用，传入页码和每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟的查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就可以
        //封装了详细的分页信息，包括有我们查询出来的数据,可以传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 员工保存
     *
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败信息，在模态框中校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
//                System.out.println("错误的字段名" + fieldError.getField());
//                System.out.println("错误的字段值" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errors", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 检验用户名是否可用
     *
     * @param empName
     * @return
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName) {
        String regx = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{4,12}";
        //先检测用户名是否合法
        if (!empName.matches(regx)) {
            return Msg.fail().add("user_msg", "用户名不合法，可以是4-12位的中文，英文，数字和_的组合");
        }
        //判断用户名是否已经存在
        boolean flag = employeeService.checkUser(empName);
        if (flag) {
            return Msg.success();
        } else {
            return Msg.fail().add("user_msg", "用户名已存在");
        }
    }

    //    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //引入PageHelper分页插件
        //在查询之前调用，传入页码和每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟的查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就可以
        //封装了详细的分页信息，包括有我们查询出来的数据,可以传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", page);
        return "list";
    }
}
