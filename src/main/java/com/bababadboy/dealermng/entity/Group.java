package com.bababadboy.dealermng.entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangxiaobin
 */
@Entity
@Table(name = "company")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 集团名称
    @Column(nullable = false)
    private String groupName;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    /*** getter and setter ***/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
