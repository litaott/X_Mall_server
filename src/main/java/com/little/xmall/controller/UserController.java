package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.user.CartInfo;
import com.little.xmall.entity.user.FollowInfo;
import com.little.xmall.entity.user.UserInfo;
import com.little.xmall.entity.user.AddressInfo;
import com.little.xmall.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户信息
     *
     * @param
     * @return Response
     */
    @PostMapping("/register")
    public Response<Map<String, Object>> registerUser(@RequestBody UserInfo userInfo) {
        return userService.registerUser(userInfo);
    }


    @GetMapping("/get_user")
    public Response<List<Map<String, Object>>> getUser(int user_id) {
        return userService.getUser(user_id);
    }

    @PostMapping("/user/update")
    public Response<Map<String, Object>> updateUser(@RequestBody UserInfo userInfo) {
        return userService.updateUser(userInfo);
    }

    @PostMapping("/user/login")
    public Response<Map<String, Object>> login(@RequestBody Map<String, String> userInfo) {
        Integer userId = Integer.parseInt(userInfo.get("user_id"));
        String password = userInfo.get("password");
        return userService.login(userId, password);
    }
    @PostMapping("/user/add_address")
    public Response<Map<String, Object>> addAddress(@RequestBody AddressInfo addressInfo) {
        return userService.addAddress(addressInfo);
    }
    @PostMapping("/user/update_address")
    public Response<Map<String, Object>> updateAddress(@RequestBody AddressInfo addressInfo) {
        return userService.updateAddress(addressInfo);
    }
    @DeleteMapping("/user/delete_address")
    public Response<String> deleteAddress(@RequestParam Integer address_id) {
            return userService.deleteAddress(address_id);
    }
    @PostMapping("/user/add_cart")
    public Response<Map<String, Object>> addCart(@RequestBody CartInfo cartInfo) {
        return userService.addCart(cartInfo);
    }
    @DeleteMapping("/user/delete_cart")
    public Response<String> deleteAddress(@RequestParam int goods_id) {
        return userService.deleteCart(goods_id);
    }
    @PostMapping("/user/add_follow")
    public Response<Map<String, Object>> addFollow(@RequestBody FollowInfo followInfo) {
        return userService.addFollow(followInfo);
    }
    @DeleteMapping("/user/delete_follow")
    public Response<String> deleteFollow(@RequestParam int store_id) {
        return userService.deleteFollow(store_id);
    }
}
