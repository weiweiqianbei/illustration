package com.illustration.service.impl;

import com.illustration.dao.MenuDao;
import com.illustration.dao.UserDao;
import com.illustration.entity.Menu;
import com.illustration.entity.Role;
import com.illustration.entity.User;
import com.illustration.entity.vo.LoginUserVO;
import com.illustration.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.illustration.result.StateMsg.StatusMsg_432;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int accountType = -1;
        if (username.matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+$")){
            accountType = 0;
        } else if (username.matches("^[a-zA-Z0-9_]{3,16}$")) {
            accountType = 1;
        }
        if (accountType == -1) {
            throw new CustomException(StatusMsg_432);
        }
        User user = userDao.selectUserByAccount(username, accountType);
        if (user == null) {
            throw new CustomException(StatusMsg_432);
        }
        List<Long> roleIds = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleIds.add(role.getId());
        }
        List<Menu> menus = menuDao.getMenusByRoleId(roleIds);
        for (Menu menu : menus) {
            user.getPerms().add(menu.getPerms());
        }
        LoginUserVO userVO = new LoginUserVO();
        userVO.setUser(user);
        userVO.setId(user.getId());
        return userVO;
    }
}
