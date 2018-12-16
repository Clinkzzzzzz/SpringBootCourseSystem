/**
 * 
 */
package com.ray.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author ray
 *
 */
@Component
@Table(name="tbl_comment_type")
public class CommentType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer typeId;
	
	private String typeName;
	
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
        return "commentType [typeId=" + typeId + ", typeName=" + typeName + "]";
    }
	
}
