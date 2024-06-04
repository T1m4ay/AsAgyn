package com.example.asadmin.repository;

import com.example.asadmin.enumeration.CodeType;
import com.example.asadmin.model.ConfirmCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmCodesRepository extends JpaRepository<ConfirmCodes, Long> {

    List<ConfirmCodes> findAllByEmailAndCodeType(String email, @Param("code_type") CodeType codeType);
    ConfirmCodes deleteAllByEmailAndCodeType(String email, @Param("code_type") CodeType codeType);
}
