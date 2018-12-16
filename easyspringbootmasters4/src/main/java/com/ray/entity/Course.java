package com.ray.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Course
 *
 * @author ray
 * 
 *
 */
@Component
public class Course implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String courseNo;

    private String courseName;

    private Integer courseHours;

    /**
     * 课程状态
     */
    private String courseStatus;

    private Double coursePoint;

    /**
     * 课程要求
     */
    private String[] courseReqs;

    private String reqs;
    /**
     * 课程备注
     */
    private String courseMemo;

    /**
     * 封面图片转换为byte数组
     */
    private byte[] courseTextbookPic;

    /**
     * 课程类型
     */
    private CourseType courseType;
    
    private User user;

    public String getCourseNo() {
        return courseNo;
    }
    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Integer getCourseHours() {
        return courseHours;
    }
    public void setCourseHours(Integer courseHours) {
        this.courseHours = courseHours;
    }

    public String[] getCourseReqs() {
        return courseReqs;
    }
    /**
     * 对课程要求进行处理 a|b|c|d
     * @param courseReqs
     */
    public void setCourseReqs(String[] courseReqs) {
        this.courseReqs = courseReqs;

        StringBuffer sb = new StringBuffer();
        for(String req:courseReqs){
            sb.append(req).append("|");
        }
        sb.deleteCharAt(sb.length()-1);//删除多余的“|”

        this.reqs = sb.toString();
    }

    public CourseType getCourseType() {
        return courseType;
    }
    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
    public String getCourseStatus() {
        return courseStatus;
    }
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    public Double getCoursePoint() {
        return coursePoint;
    }
    public void setCoursePoint(Double coursePoint) {
        this.coursePoint = coursePoint;
    }

    public String getCourseMemo() {
        return courseMemo;
    }
    public void setCourseMemo(String courseMemo) {
        this.courseMemo = courseMemo;
    }

    public byte[] getCourseTextbookPic() {
        return courseTextbookPic;
    }
    public void setCourseTextbookPic(byte[] courseTextbookPic) {
        this.courseTextbookPic = courseTextbookPic;
    }
    public String getReqs() {
        return reqs;
    }
    /**
     * 用“|”做分隔符
     * @param reqs
     */
    public void setReqs(String reqs) {
        this.reqs = reqs;
        this.courseReqs = this.reqs.split("\\|");
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
