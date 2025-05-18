package com.tr.resume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tr.resume.entity.dto.UserLoginDTO;
import com.tr.resume.entity.po.User;
import com.tr.resume.entity.vo.UserVO;
import jakarta.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tr
 * @since 2025-05-17
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param userLoginDTO
     */
    UserVO login(@Valid UserLoginDTO userLoginDTO);

    /**
     * 注册
     * @param userLoginDTO
     */
    void register(@Valid UserLoginDTO userLoginDTO);
}
