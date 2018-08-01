package com.huawei.dao;

import com.huawei.projo.User;

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
}
