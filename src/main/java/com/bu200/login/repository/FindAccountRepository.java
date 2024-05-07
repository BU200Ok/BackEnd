package com.bu200.login.repository;

import com.bu200.login.entity.FindAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FindAccountRepository extends JpaRepository<FindAccount, Long> {

    Long countByIpAndSecretCode(String ip, Integer code);
//    @Query("SELECT COUNT(fa) FROM FindAccount fa WHERE fa.ip = :ip AND fa.secretCode = :code")
//    Long countByIpAndSecretCode(@Param("ip") String ip, @Param("code") Integer code);


    void deleteByIpAndSecretCode(String ip, Integer code);
}
