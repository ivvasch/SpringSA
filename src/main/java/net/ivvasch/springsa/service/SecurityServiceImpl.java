package net.ivvasch.springsa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;




    @Override
    public String findLoggedInUsername() {
        Object userdetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userdetails instanceof UserDetails) {
            return ((UserDetails) userdetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        // создаем объект UserDetails, путем загрузки пользователя по его имени
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // создаем экземпляр класса UsernamePasswordAuthenticationToken и передаем ему детали пользователя userdetails,
        // пароль и разрешения пользователя,
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                password, userDetails.getAuthorities());
        // авторизуем нашего пользователя
        authenticationManager.authenticate(authenticationToken);
        // проверяем, если авторизация пользователя прошла то
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            logger.debug(String.format("succsessfully autolog in",  username));
        }

    }
}
