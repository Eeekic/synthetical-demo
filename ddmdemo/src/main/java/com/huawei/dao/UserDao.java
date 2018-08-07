package com.huawei.dao;

import com.huawei.projo.User;

import java.util.List;

public interface UserDao {
    /**
     * create by: sunpeng
     * description:
     * create time: 19:15 2018/7/26
     *
     * @return 
     */
    public User querySimpleUserInfoByName(String userName);

    /**
     * create by: sunpeng
     * description:
     * create time: 19:28 2018/7/26
     *
     * @return 
     */
    public int addUser(User user);

    /**
     * create by: sunpeng
     * description:
     * create time: 0:29 2018/7/27
     *
     * @return
     */
    public int updateUserBalance(int price,long userId);

    /**
     * create by: sunpeng
     * description:
     * create time: 11:44 2018/7/28
     *
     * @return
     */
    public int queryUserBalance(long userId);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public User queryUserDetailInfoById(long userId);
    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public long queryUserId(String userName);

    /**
     * create by: sunpeng
     * description:
     * create time: 19:28 2018/7/26
     *
     * @return
     */
    public int addUser(String userName,String userPwd,String userType);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public List<User> queryUser(String userType);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int cleanUser(String userType);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int queryUserCount(String userType);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int queryUserMaxId(String userType);

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int queryUserMinId(String userType);

}
