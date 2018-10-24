package com.ray.mapper;

import java.util.List;
import java.util.Map;

import com.ray.entity.Course;

/**
 * CourseMapper
 *
 * @author ray
 *
 */
public interface CourseMapper {

    /**
     * 添加课程
     *
     * @param course
     *
     */
    void addCourse(Course course);

    /**
     * 根据id删除课程
     *
     * @param courseNo
     * @return boolean
     *
     */
    boolean removeCourseByNo(String courseNo);

    /**
     * 根据被删除课程类型id删除若干课程信息
     *
     * @param typeId
     * 
     *
     * @return boolean
     *
     *
     */
    boolean removeCourseByTypeId(Integer typeId);

    /**
     * 更新课程信息
     *
     * @param course
     *
     */
    void updateCourse(Course course);

    /**
     * 根据课程id获取课程信息
     *
     * @param courseNo
     * @return Course
     *
     */
    Course loadCourseByNo(String courseNo);

    /**
     * 根据课程类型获取多条课程信息
     *
     * @param typeId
     * @return List
     *
     */
    List<String> loadCourseByTypeId(Integer typeId);


    /**
     * 获取对应条件课程信息
     *
     * @param map
     * @return List
     *
     */
    List<Course> loadScopedCourses(Map<?, ?> map);

}
