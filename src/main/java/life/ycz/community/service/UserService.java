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
            userMapper.update(user);
        }else{
            userMapper.insert(user);
        }
    }
}
