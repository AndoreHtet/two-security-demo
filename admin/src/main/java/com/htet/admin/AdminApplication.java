package com.htet.admin;

import com.htet.data.repo.base.BaseRepoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.htet.admin","com.htet.data","com.htet.data.repo","com.htet.data.service","com.htet.data.utils"})
@EntityScan({"com.htet.data.entities"})
@EnableJpaRepositories(value = {"com.htet.data.repo"},repositoryBaseClass = BaseRepoImpl.class)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
