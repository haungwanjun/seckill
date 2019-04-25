package com.mobilephone.seckill.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mobilephone.seckill.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class SeckillUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date registerDate;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date lastLoginDate;
    private Integer loginCount;
    private String deliveryAddress;
}
