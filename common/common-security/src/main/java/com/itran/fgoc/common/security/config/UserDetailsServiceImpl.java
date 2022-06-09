package com.itran.fgoc.common.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cocoyang
 * @date 2020/6/22
 * 授权服务配置
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 请求头里登录的信息
     */
    private String passwordParameter = "password";
    private String clientId = "client_id";

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 自定义登录
     *
     * token中不保存用户权限角色相关信息，我们把用户角色、权限放到redis中存储
     * @param username 当前登录的用户
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return new User("admin", passwordEncoder.encode("123456"), AuthorityUtils.NO_AUTHORITIES);
//        log.info("用户:{} 登录",username);
//        User user = userDao.findByUserName(username);
//        if(user == null){
//            throw new UsernameNotFoundException(ResultCode.DATA_IS_NULL.getMessage());
//        }
//        String pwd = request.getParameter(passwordParameter);
//        boolean matches = passwordEncoder.matches(pwd, user.getPassword());
//        if (!matches) {
//            throw new UsernameNotFoundException(ResultCode.DATA_IS_NULL.getMessage());
//        }
//
//        List<ProductDTO> productDTOS =  userDao.findUserProduct(user.getId());
//        if(CollUtil.isEmpty(productDTOS)){
//            throw new ApiException(ResultCode.CODE_10901006);
//        }
//        ProductDTO currentProduct = productDTOS.get(0);
//
//        // 保存用户信息到 redis
//        FgocUserDetailDTO fgocUserDetailDTO = FgocUserDetailDTO.builder()
//                .userId(user.getId())
//                .username(user.getUsername())
//                .name(user.getName())
//                .mobile(user.getMobile())
//                .roles(userDao.findRolesByUseId(user.getId(), currentProduct.getProductCode()))
//                .posts(userDao.findPostByUseId(user.getId()))
//                .companyId(currentProduct.getCompanyId())
//                .companyName(currentProduct.getCompanyName())
//                .deptId(user.getDeptId())
//                .deptName(user.getDeptName())
//                .productCode(currentProduct.getProductCode())
//                .productDTOS(productDTOS)
//                .authority(userDao.findAuthorityByUseId(user.getId(), currentProduct.getProductCode()))
//                .build();
//        redisUtils.put(RedisVar.LOGIN_USER, user.getUsername(), fgocUserDetailDTO);
//
//        //返回登录对象交给spring 处理
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
