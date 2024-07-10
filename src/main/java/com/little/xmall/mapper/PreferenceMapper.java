package com.little.xmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.little.xmall.entity.PreferenceInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 优惠映射器类
 * @author Little
 */
public interface PreferenceMapper extends BaseMapper<PreferenceInfo> {

    @Select("SELECT discount FROM discount_info WHERE id = #{pref_id}")
    float getDiscount(int pref_id);

    @Select("SELECT reduction FROM reduction_info WHERE id = #{pref_id}")
    float getReduction(int pref_id);

    @Select("SELECT gift FROM gift_info WHERE id = #{pref_id}")
    String getGift(int pref_id);

    @Insert("INSERT INTO discount_info discount VALUES #{discount}")
    void addDiscount(float discount);


    void addReduction(float reduction);


    void addGift(String gift);
}
