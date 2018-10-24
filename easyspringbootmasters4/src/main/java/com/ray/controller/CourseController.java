package com.ray.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.entity.Course;
import com.ray.service.CourseService;
import com.ray.service.CourseTypeService;
import com.ray.utils.CourseQueryHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseController
 *
 * @author ray
 * 
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTypeService courseTypeService ;

    @ModelAttribute
    public void getCourse(@RequestParam(value="courseNo",required=false) String courseNo,
                          Map<String, Object> map,Course course){

        course=courseService.loadCourseByNo(courseNo);
        if(courseNo != null&&course!= null){
            map.put("course", course);
        }
    }

    @GetMapping("/toInput")
    public String toInput(Map<String, Object> map,Course course) {


        map.put("courseTypeList", courseTypeService.loadAll());

        course.setCourseStatus("O");
        course.setCourseReqs(new String[]{"a","b"});

        map.put("course", course);

        return "course/input_course";
    }

    @PostMapping(value="/create")
    @ResponseBody
    public Map<String, Object> create(@RequestParam("coursetextbookpic") MultipartFile file, Course course, Map<String, Object> map) throws Exception{
    	Map<String, Object> Errormap =new HashMap<>();
        //读取文件数据，转换成数组
        if(file!=null){
        	System.out.println(file.getSize());
        	if (file.getSize()>21000000) {
				Errormap.put("fail", "图片超过大小限制");
				return Errormap;
			}
            course.setCourseTextbookPic(file.getBytes());
        }

        try{
            courseService.addCourse(course);
        }catch(Exception e){
            map.put("exceptionMessage", e.getMessage());

            map.put("courseTypeList", courseTypeService.loadAll());
            Errormap.put("fail", "添加课程失败，失败原因："+e.getMessage());
            return Errormap;
        }
        Errormap.put("success", "添加成功!");
        return Errormap;
    }

    @RequestMapping("/list")
    public String list(@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       Map<String, Object> map, CourseQueryHelper helper) {

        int pageNo = 1;

        //�? pageNo 的校�?
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        PageHelper.startPage(pageNo, 5);
        List<Course> courselist = courseService.loadScopedCourses(helper);
        PageInfo<Course> page=new PageInfo<Course>(courselist);

        map.put("courseTypeList", courseTypeService.loadAll());
        map.put("page", page);
        map.put("helper", helper);

        return "course/list_course";

    }


    @DeleteMapping(value="/remove/{courseNo}")
    public String remove(@PathVariable("courseNo") String courseNo) {

        courseService.removeCourseByNo(courseNo);

        return "redirect:/course/list";

    }

    @GetMapping(value="/preUpdate/{courseNo}")
    public String preUpdate(@PathVariable("courseNo") String courseNo, Map<String, Object> map) {

        map.put("course" ,courseService.loadCourseByNo(courseNo));

        map.put("courseTypeList", courseTypeService.loadAll());

        return "course/update_course";

    }

    @PostMapping(value="/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam("coursetextbookpic") MultipartFile file, Course course, Map<String, Object> map) throws Exception{
    	Map<String, Object> Errormap =new HashMap<>();
        //读取多段提交的文件数据，转成字节数组
        if(file.getBytes().length>0){
        	if(file.getSize()>21000000) {
        		Errormap.put("fail", "图片大小超过限制");
        		return Errormap;
        	}
            course.setCourseTextbookPic(file.getBytes());
        }

        try{
            courseService.updateCourse(course);
        }catch(Exception e){
            map.put("exceptionMessage", e.getMessage());

            map.put("courseTypeList", courseTypeService.loadAll());
            Errormap.put("fail", "修改课程失败，失败原因："+e.getMessage());
            return Errormap;
        }
        Errormap.put("success", "修改成功");
        return Errormap;

    }

    @GetMapping("/getPic/{courseNo}")
    public String getPic(@PathVariable("courseNo") String courseNo, HttpServletRequest request, HttpServletResponse response) throws Exception{

        byte[] textBookPic = courseService.getTextbookPic(courseNo);

        if(textBookPic==null){
            String path = request.getSession().getServletContext().getRealPath("/pics/default.jpg");
            FileInputStream fis = new FileInputStream(new File(path));

            textBookPic = new byte[fis.available()];
            fis.read(textBookPic);
            fis.close();
        }

        //让浏览器知道，我要发送是图片
        response.setContentType("image/jpeg");
        ServletOutputStream sos=response.getOutputStream();
        sos.write(textBookPic);
        sos.flush();
        sos.close();

        return null;

    }


}
