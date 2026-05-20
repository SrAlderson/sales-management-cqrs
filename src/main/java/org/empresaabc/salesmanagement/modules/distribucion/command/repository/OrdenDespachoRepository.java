package org.empresaabc.salesmanagement.modules.distribucion.command.repository;

import org.empresaabc.salesmanagement.modules.distribucion.command.entity.OrdenDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenDespachoRepository
        extends JpaRepository
        <OrdenDespacho, Long> {

    Optional<OrdenDespacho>
    findByCodigoDespacho(
            String codigoDespacho
    );
}