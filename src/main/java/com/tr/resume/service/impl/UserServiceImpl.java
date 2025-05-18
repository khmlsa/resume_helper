package com.tr.resume.service.impl;

import com.tr.resume.entity.dto.UserLoginDTO;
import com.tr.resume.entity.po.User;
import com.tr.resume.entity.vo.UserVO;
import com.tr.resume.exception.BuzException;
import com.tr.resume.mapper.UserMapper;
import com.tr.resume.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tr.resume.utils.JwtConfig;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tr
 * @since 2025-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 登录
     *
     * @param userLoginDTO
     */
    @Override
    public UserVO login(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        User user = this.lambdaQuery().eq(User::getPhone, phone).one();
        if (Objects.isNull(user)) {
            throw new BuzException("用户没有注册");
        }
        String encodedPassword = HexUtils.toHexString(userLoginDTO.getPassword().getBytes());
        if (!Objects.equals(encodedPassword, user.getPassword())) {
            throw new BuzException("密码错误");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        if (Objects.nonNull(user.getSex())) {
            userVO.setSex(user.getSex() ? "男" : "女");
        }
        // 生成token
        String token = jwtConfig.generateToken(user.getPhone());
        userVO.setToken(token);
        return userVO;
    }

    /**
     * 注册
     *
     * @param userLoginDTO
     */
    @Override
    public void register(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        String password = userLoginDTO.getPassword();
        User user = this.lambdaQuery().eq(User::getPhone, phone).one();
        if (Objects.nonNull(user)) {
            throw new BuzException("该手机号已经注册");
        }
        String encodedPassword = HexUtils.toHexString(password.getBytes());
        User currentUser = new User();
        currentUser.setPhone(phone);
        currentUser.setPassword(encodedPassword);
        this.save(currentUser);
    }
}
