package com.htet.data.repo.user;

import com.htet.data.entities.User;
import com.htet.data.repo.base.BaseRepo;

import java.util.Optional;

public interface UserRepo extends BaseRepo<User, Long> {

    Optional<User> findByPhoneNo(String phoneNo);
}
