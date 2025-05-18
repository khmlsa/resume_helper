package com.tr.resume.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tr
 * @since 2025-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name= "User对象", description="")
public class User{

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String password;

    private String mail;

    private Boolean sex;

    private Integer age;

}
