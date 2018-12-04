package com.balynsky.ac.user.clients;

import com.balynsky.ac.user.UserResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user", decode404 = true)
public interface UserClient extends UserResource {
}
