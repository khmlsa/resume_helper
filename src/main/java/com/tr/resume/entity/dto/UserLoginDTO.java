package com.tr.resume.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "用户登录请求实体")
public class UserLoginDTO {
    @Schema(description = "手机号", example = "15435485255")
    @NotBlank(message = "手机号错误")
    private String phone;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "密码错误")
    private String password;
}
