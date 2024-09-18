package cc.dao;

import cc.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}