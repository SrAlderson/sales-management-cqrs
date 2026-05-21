package org.empresaabc.salesmanagement.modules.contabilidad.query.repository;

import org.empresaabc.salesmanagement.modules.contabilidad.query.document.MovimientoContableDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContabilidadQueryRepository
        extends MongoRepository
        <MovimientoContableDocument, String> {

    Optional<MovimientoContableDocument>
    findByCodigoMovimiento(
            String codigoMovimiento
    );

    Optional<MovimientoContableDocument>
    findByCodigoVenta(
            String codigoVenta
    );
}