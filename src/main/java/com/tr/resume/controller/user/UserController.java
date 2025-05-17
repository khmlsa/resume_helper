package com.tr.resume.controller.user;

import com.tr.resume.entity.Result;
import com.tr.resume.entity.dto.UserLoginDTO;
import com.tr.resume.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "登录", description = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);
        return Result.success();
    }

    @Operation(summary = "注册", description = "注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        userService.register(userLoginDTO);
        return Result.success();
    }
}
