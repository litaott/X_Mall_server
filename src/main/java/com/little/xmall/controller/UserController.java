package com.little.xmall.controller;

import com.little.xmall.constant.Response;
import com.little.xmall.entity.security.UserPassword;
import com.little.xmall.entity.user.*;
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
    @PutMapping("/change_user_password")
    public Response<Map<String, Object>> changePassword(UserPassword password){
        return userService.changePassword(password);
}
    @PutMapping("/update_user")
    public Response<Map<String, Object>> updateUser(@RequestBody UserInfo userInfo) {
        return userService.updateUser(userInfo);
    }

    @PostMapping("/login")
    public Response<String> login(@RequestParam Integer user_id,String password) {
        return userService.login(user_id, password);
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

    @PostMapping("/add_cart")
    public Response<Map<String, Object>> addCart(@RequestBody CartInfo cartInfo) {
        return userService.addCart(cartInfo);
    }

    @GetMapping("/get_cart")
    public Response<List<Map<String, Object>>> getCart(@RequestParam int user_id) {
        return userService.getCart(user_id);
    }

    @DeleteMapping("/delete_cart")
    public Response<String> deleteAddress(@RequestParam int user_id,int goods_id) {
        return userService.deleteCart(user_id,goods_id);
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


   @PutMapping("/change_cart_goods")
    Response<String>changeCartGoods(@RequestParam int user_id,int goods_id,int quantity){
        return userService.changeCartGoods(user_id,goods_id,quantity);
   }
   @PostMapping("/addRecord")
   Response<Map<String,Object>>addRecord(@RequestBody HistoryInfo historyInfo){
        return userService.addRecord(historyInfo);
   }
   @GetMapping("/getRecord")
   Response<List<Map<String,Object>>>getRecord(){
        return userService.getRecord();
   }
   @PutMapping("/deleteRecord")
       Response<String>deleteRecord(@RequestParam Integer goods_id){
           return userService.deleteRecord(goods_id);
       }
   }

