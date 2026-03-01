package com.smartclinic.webapp.repository;

import com.smartclinic.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
