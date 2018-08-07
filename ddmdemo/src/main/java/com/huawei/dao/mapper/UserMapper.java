package com.huawei.dao.mapper;

import com.huawei.projo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * create by: sunpeng
     * description:
     * create time: 19:50 2018/7/26
     *
     * @reurn
     */
    public int addUser(User user);

    /**
     * create by: sunpeng
     * description:
     * create time: 19:28 2018/7/26
     *
     * @return
     */
    public int addTestUser(@Param("userName")String userName,@Param("userPwd")String userPwd,@Param("userType")String userType);
    /**
     * create by: sunpeng
     * description:
     * create time: 19:50 2018/7/26
     *
     * @return 
     */
    public User querySimpleUserInfoByName(String userName);
    /**
     * create by: sunpeng
     * description:
     * create time: 0:30 2018/7/27
     *
     * @return
     */
    public int updateUserBalance(@Param("price")int price,@Param("userId")long userId);

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

    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public List<User> queryTestUser(String userType);

    public long queryUserId(String userName);
    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int cleanTestUser(String userType);
    /**
     * create by: sunpeng
     * description:
     * create time: 14:45 2018/7/28
     *
     * @return
     */
    public int queryTestUserCount(String userType);

}
