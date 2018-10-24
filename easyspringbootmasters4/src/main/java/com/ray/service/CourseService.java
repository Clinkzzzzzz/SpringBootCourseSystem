package com.ray.service;

import java.util.List;

import com.ray.entity.Course;
import com.ray.utils.CourseQueryHelper;


/**
 * CourseService
 *
 * @author ray
 *
 *
 */
public interface CourseService {

    /**
     * 新增课程记录
     *
     * @param course
     *
     */
    void addCourse(Course course);

    /**
     * 删除一条课程记录
     *
     * @param courseNo
     * @return boolean
     *
     */
    boolean removeCourseByNo(String courseNo);

    /**
     * 更新课程信息
     *
     * @param course
     *
     */
    void updateCourse(Course course);

    /**
     * 根据id获取课程信息
     *
     * @param courseNo
     * @return Course
     *
     */
    Course loadCourseByNo(String courseNo);

    /**
     * 获得某查询条件下的所有的课程记录
     *
     * @param helper
     * @return List
     *
     */
    List<Course> loadScopedCourses(CourseQueryHelper helper);

    /**
     * 根据课程编号，获得图片
     *
     * @param courseNo
     * @return byte
     *
     */
    byte[] getTextbookPic(String courseNo);

}
