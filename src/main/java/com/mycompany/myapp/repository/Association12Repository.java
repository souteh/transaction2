package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Association12;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Association12 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Association12Repository extends JpaRepository<Association12, Long>, JpaSpecificationExecutor<Association12> {
}
