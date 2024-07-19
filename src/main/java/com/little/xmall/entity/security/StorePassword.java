package com.little.xmall.entity.security;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePassword implements Serializable{
    private Integer store_id;
    private String old_password;
    private String new_password;
}
