package life.ycz.community.service;

import life.ycz.community.mapper.UserMapper;
import life.ycz.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addOrUpdate(User user) {
        User  dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser!=null){
            user.setGmtModified(System.currentTimeMillis());
            userMapper.update(user);
        }else{
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
