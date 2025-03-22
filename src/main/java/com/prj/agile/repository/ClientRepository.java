package com.prj.agile.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.prj.agile.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByDocument(String document);

}
