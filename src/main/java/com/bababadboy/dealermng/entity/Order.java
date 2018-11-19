package com.bababadboy.dealermng.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Order实体类
 * @author Ash
 */
@Entity
@Table(name = "Order")
public class Order implements Serializable {

    @Id // 主键
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 自增长策略
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
