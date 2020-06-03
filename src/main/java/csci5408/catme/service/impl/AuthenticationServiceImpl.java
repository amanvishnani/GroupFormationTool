package csci5408.catme.service.impl;

import csci5408.catme.authentication.ISessionStore;
import csci5408.catme.dao.UserDao;
import csci5408.catme.domain.User;
import csci5408.catme.dto.UserSummary;
import csci5408.catme.service.AuthenticationService;
import csci5408.catme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static csci5408.catme.authentication.AuthConfig.AUTH_COOKIE_NAME;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    final
    AuthenticationManager authenticationManager;

    final
    ISessionStore ISessionStore;

    final
    UserService userService;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    final
    UserDao userDao;

    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            ISessionStore ISessionStore, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.ISessionStore = ISessionStore;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDao = userDao;
    }


    @Override
    public boolean login(String email, String password, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        try {
            if(this.isAuthenticated()) {
                String cookieString = ISessionStore.setSession((UserSummary)auth.getPrincipal());
                response.addCookie(new Cookie(AUTH_COOKIE_NAME, cookieString));
            }
        } catch (IllegalStateException e) {
            System.out.println("Code probably called from CommandLineRunner");
        }

        return sc.getAuthentication().isAuthenticated();
    }

    @Override
    public UserSummary signUp(UserSummary user, String password) {
        UserSummary existingUser = userService.getUserByEmailId(user.getEmailId());
        if(existingUser != null) {
            // User Exists
            throw new RuntimeException("User Already Exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return userService.createUser(user, encodedPassword);
    }

    @Override
    public boolean isAuthenticated() {
        SecurityContext sc = SecurityContextHolder.getContext();
        return sc.getAuthentication().isAuthenticated();
    }

    @Override
    public void changePassword(UserSummary user, String password) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User u = userDao.findByEmail(user.getEmailId());
        u.setPassword(encodedPassword);
        userDao.update(u);
    }
    
    public boolean isAdmin(String email, String password)
    {
    	if(email.equals("krupa1711@gmail.com") && password.equals("admin")) {
    	return true;
    	}
    	else {
    		return false;
    	}
    }
}
