package com.sidhu.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long userId;
    private String fullName;
    private String email;
    private List<AccountVO> accounts;
    private String error;
}
