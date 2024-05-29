package org.librarySystem.repository;


import org.librarySystem.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members,String> {
    Members findByEmail(String email);
}
