package com.ray.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.entity.Course;
import com.ray.mapper.CommentMapper;
import com.ray.mapper.CourseMapper;
import com.ray.service.CourseService;
import com.ray.utils.CourseQueryHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseServiceImpl
 *
 * @author ray
 *
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addCourse(Course course) {

        courseMapper.addCourse(course);

    }

    @Override
    public boolean removeCourseByNo(String courseNo) {
    	if(commentMapper.loadCommentByCourseNo(courseNo)!=null) {
    		commentMapper.removeCommentByCourseNo(courseNo);
    	}
        courseMapper.removeCourseByNo(courseNo) ;
        return true;

    }

    @Override
    public void updateCourse(Course course) {

        String[] courseReq = course.getCourseReqs();
        if (courseReq != null && courseReq.length > 0) {

            courseMapper.updateCourse(course);

        } else {
            course.setReqs("");
            courseMapper.updateCourse(course);
        }

    }

    @Override
    public Course loadCourseByNo(String courseNo) {

        Course course=new Course();
        course=null;
        if(courseNo!=null) {
            course = courseMapper.loadCourseByNo(courseNo);
        }
        return course;

    }

    @Override
    public List<Course> loadScopedCourses(CourseQueryHelper helper) {

        Map<String,Object> map = new HashMap<>(16);
        map=getQueryHelper(helper);

        List<Course> list = courseMapper.loadScopedCourses(map);

        return list;

    }

    @Override
    public byte[] getTextbookPic(String courseNo) {

        byte[] textBookPic = null;

        Course course = courseMapper.loadCourseByNo(courseNo);

        textBookPic = course.getCourseTextbookPic();

        return textBookPic;

    }

    private Map<String,Object> getQueryHelper(CourseQueryHelper helper) {

        Map<String,Object> map = new HashMap<>(16);

        if(helper.getQryCourseName()!=null){
            map.put("qryCourseName", helper.getQryCourseName());
        }

        if(helper.getQryEndPoint()!=null){
            map.put("qryEndPoint", helper.getQryEndPoint());
        }

        if(helper.getQryStartPoint()!=null){
            map.put("qryStartPoint", helper.getQryStartPoint());
        }

        if((helper.getQryCourseType()!=null)&&(!"".equals(helper.getQryCourseType()))){
            map.put("typeId", Integer.parseInt(helper.getQryCourseType()));
        }

        return map;
    }


	@Override
	public List<Map<String, Object>> loadCountByType() {
		List<Map<String, Object>> list=courseMapper.loadCountByType();
		return list;
	}


	@Override
	public boolean removeCourseByUserNo(String userNo) {
		courseMapper.removeCourseByUserNo(userNo);
		return true;
	}

}
