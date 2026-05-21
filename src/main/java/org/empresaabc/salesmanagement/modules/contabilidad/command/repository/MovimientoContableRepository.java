package org.empresaabc.salesmanagement.modules.contabilidad.command.repository;

import org.empresaabc.salesmanagement.modules.contabilidad.command.entity.MovimientoContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoContableRepository
        extends JpaRepository
        <MovimientoContable, Long> {

    Optional<MovimientoContable>
    findByCodigoMovimiento(
            String codigoMovimiento
    );

    Optional<MovimientoContable>
    findByCodigoVenta(
            String codigoVenta
    );
}