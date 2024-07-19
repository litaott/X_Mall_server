package com.little.xmall.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPassword implements Serializable{
    private Integer user_id;
    private String old_password;
    private String new_password;
}
