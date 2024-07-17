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
    public Response<List<Map<String, Object>>> getUser(@RequestParam int user_id) {
        return userService.getUser(user_id);
    }

    @PutMapping("/update_user")
    public Response<Map<String, Object>> updateUser(@RequestBody UserInfo userInfo) {
        return userService.updateUser(userInfo);
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody Map<String, String> userInfo) {
        Integer userId = Integer.parseInt(userInfo.get("user_id"));
        String password = userInfo.get("password");
        return userService.login(userId, password);
    }
    @DeleteMapping("/delete_user")
    public Response<String> deleteUser(@RequestParam int user_id){
        return userService.deleteUser(user_id);
    }
    @PostMapping("/add_address")
    public Response<Map<String, Object>> addAddress(@RequestBody AddressInfo addressInfo) {
        return userService.addAddress(addressInfo);
    }
    @PutMapping("/update_address")
    public Response<Map<String, Object>> updateAddress(@RequestBody AddressInfo addressInfo) {
        return userService.updateAddress(addressInfo);
    }
    @GetMapping("/get_address")
    public Response<List<Map<String, Object>>> getAddress(@RequestParam Integer user_id){
        return userService.getAddress(user_id);
    }
    @DeleteMapping("/delete_address")
    public Response<String> deleteAddress(@RequestParam Integer address_id) {
            return userService.deleteAddress(address_id);
    }
    @PostMapping("/user/add_cart")
    public Response<Map<String, Object>> addCart(@RequestBody CartInfo cartInfo) {
        return userService.addCart(cartInfo);
    }
    @GetMapping("/user/get_cart")
    public Response<List<Map<String, Object>>> getCart(@RequestParam int user_id) {
        return userService.getCart(user_id);
    }
    @DeleteMapping("/user/delete_cart")
    public Response<String> deleteAddress(@RequestParam int goods_id) {
        return userService.deleteCart(goods_id);
    }
    @PostMapping("/add_follow")
    public Response<Map<String, Object>> addFollow(@RequestBody FollowInfo followInfo) {
        return userService.addFollow(followInfo);
    }
    @DeleteMapping("/delete_follow")
    public Response<String> deleteFollow(@RequestParam int store_id,int user_id) {
        return userService.deleteFollow(store_id,user_id);
    }
    @GetMapping("/get_follow")
    Response<Map<String, List<?>>>getFollow(@RequestParam int user_id){
        return userService.getFollow(user_id);
    }
    @GetMapping("/get_if_follow")
    Response<String>getIfFollow(@RequestParam int user_id,int store_id){
        return userService.getIfFollow(user_id,store_id);
    }
    @GetMapping("/user/get_cart")
    public Response<List<Map<String, Object>>> getCart(@RequestParam int user_id) {
        return userService.getCart(user_id);
    }
}
