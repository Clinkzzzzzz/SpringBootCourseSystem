package com.ray.service;

import java.util.List;

import com.ray.entity.User;

/**
 * UserService
 *
 * @author xiaoze
 * @date 2018/6/3
 *
 */
public interface UserService {

/**
 * 查
 * @param userNo
 * @return
 */
    User get(String userNo);


    /**
     * 
     *增
     * @param  user
     * @return User
     *
     */
    void addUser(User user);
    
    /**
     * 删
     * @param userNo
     * @return
     */
    boolean removeUserByNo(String userNo);
    
    /**
     * 改
     * @param user
     */
    void updateUser(User user);
    
   
    List<User> loadUserByGameRank(Integer rank);
    
    byte[] getUserPic(String userNo);
}

