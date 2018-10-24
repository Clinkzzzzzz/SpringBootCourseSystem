package com.ray.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ray.entity.*;
import com.ray.mapper.CourseMapper;
import com.ray.mapper.CourseTypeMapper;
import com.ray.service.CourseTypeService;

import java.util.List;

/**
 * CourseTypeServiceImpl
 *
 * @author ray
 * 
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void addCourseType(CourseType courseType) {

        courseTypeMapper.insert(courseType);

    }

    @Override
    public void removeCourseType(Integer typeId) {

        if(courseMapper.loadCourseByTypeId(typeId)!=null){
            courseMapper.removeCourseByTypeId(typeId);
        }

        courseTypeMapper.deleteByPrimaryKey(typeId);

    }

    @Override
    public void updateCourseType(CourseType courseType) {

        courseTypeMapper.updateByPrimaryKey(courseType);

    }
    /**
     *
     */
    @Override
    public CourseType getCourseTypeById(Integer typeId) {

        return courseTypeMapper.selectByPrimaryKey(typeId);

    }
    /**
     *
     */
    @Override
    public List<CourseType> loadAll() {

        return courseTypeMapper.selectAll();

    }

}
