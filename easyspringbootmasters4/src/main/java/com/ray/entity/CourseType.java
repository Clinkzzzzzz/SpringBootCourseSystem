package com.ray.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * CourseType
 *
 * @author ray
 * 
 *
 */
@Component
@Table(name="tbl_course_type")
public class CourseType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    private Integer typeId;

    private String  typeName;


    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @Override
    public String toString() {
        return "CourseType [typeId=" + typeId + ", typeName=" + typeName + "]";
    }

}
