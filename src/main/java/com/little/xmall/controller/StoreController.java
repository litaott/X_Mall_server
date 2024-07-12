package com.little.xmall.controller;


import com.little.xmall.constant.Response;
import com.little.xmall.entity.store.StoreInfo;
import com.little.xmall.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * 商店控制器
 * @author Little
 */
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    @PostMapping("/store/register_store")
    public Response<Map<String, Object>> registerStore(@RequestBody StoreInfo storeInfo) {
        return storeService.registerStore(storeInfo);
    }
    @PostMapping("/store/update")
    public Response<Map<String, Object>> updateStore(@RequestBody StoreInfo storeInfo) {
        return storeService.updateStore(storeInfo);
    }
    @PostMapping("/store/login")
    public Response<Map<String, Object>> login(@RequestBody Map<String, String> storeInfo) {
        Integer storeId = Integer.parseInt(storeInfo.get("store_id"));
        String password = storeInfo.get("password");
        return storeService.login(storeId, password);
    }
    @DeleteMapping("/store/delete_store")
    public Response<String> deleteStore(@RequestParam int store_id) {
        return storeService.deleteStore(store_id);
    }
    @GetMapping("/get_reputation")
    public Response<Map<String, Object>>getReputation(@RequestBody StoreInfo store_id){
        return storeService.getReputation(store_id);
    }

    @GetMapping("/get_fans")
    public Response<Map<String, Object>>getFans(@RequestBody StoreInfo store_id){
        return  storeService.getFans(store_id);
    }
}