package com.little.xmall.controller;


import com.little.xmall.constant.Response;
import com.little.xmall.entity.security.StorePassword;
import com.little.xmall.entity.store.StoreInfo;
import com.little.xmall.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * 商店控制器
 *
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/register_store")
    public Response<Map<String, Object>> registerStore(@RequestBody StoreInfo storeInfo) {
        return storeService.registerStore(storeInfo);
    }

    @GetMapping("/get_store")
    public Response<List<Map<String, Object>>> getStore(@RequestParam Integer store_id) {
        return storeService.getStore(store_id);
    }

    @PutMapping("/update")
    public Response<Map<String, Object>> updateStore(@RequestBody StoreInfo storeInfo) {
        return storeService.updateStore(storeInfo);
    }

    @PutMapping("/change_password")
    public Response<Map<String, Object>> changePassword(@RequestBody StorePassword password) {
        return storeService.changePassword(password);
    }

    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody Map<String, String> storeInfo) {
        Integer storeId = Integer.parseInt(storeInfo.get("store_id"));
        String password = storeInfo.get("password");
        return storeService.login(storeId, password);
    }

    @DeleteMapping("/delete_store")
    public Response<String> deleteStore(@RequestParam int store_id) {

        return storeService.deleteStore(store_id);
    }

    @GetMapping("/get_reputation")
    public Response<Map<String, Object>> getReputation(@RequestParam Integer store_id) {
        return storeService.getReputation(store_id);
    }

    @GetMapping("/get_fans")
    public Response<Map<String, Object>> getFans(@RequestParam Integer store_id) {
        return storeService.getFans(store_id);
    }
}
