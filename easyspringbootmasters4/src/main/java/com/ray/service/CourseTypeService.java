package com.ray.service;

import com.ray.entity.*;

import java.util.List;

/**
 * CourseTypeService
 *
 * @author ray
 * 
 *
 */
public interface CourseTypeService {

    /**
     * 鏂板涓�鏉¤绋嬬被鍨嬭褰�?
     *
     * @param courseType
     *
     */
    void addCourseType(CourseType courseType);

    /**
     * 鍒犻櫎涓�鏉¤绋嬬被鍨嬭褰�?
     *
     * @param typeId
     *
     */
    void removeCourseType(Integer typeId);

    /**
     * 鏇存柊涓�鏉¤绋嬬被鍨嬭褰�?
     *
     * @param courseType
     *
     */
    void updateCourseType(CourseType courseType);

    /**
     * 鑾峰彇涓�鏉¤绋嬬被鍨嬭褰�?
     *
     * @param typeId
     * @return CourseType
     *
     */
    CourseType getCourseTypeById(Integer typeId);

    /**
     *  鑾峰彇鎵�鏈夎绋嬬被鍨嬭褰�
     *
     * @return List
     *
     */
    List<CourseType> loadAll();

}
