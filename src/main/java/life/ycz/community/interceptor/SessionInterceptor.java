package life.ycz.community.interceptor;

import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.User;
import life.ycz.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")){
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(cookies[i].getValue());
                    List<User> users = userMapper.selectByExample(userExample);
                    if (users.size()!=0){
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
