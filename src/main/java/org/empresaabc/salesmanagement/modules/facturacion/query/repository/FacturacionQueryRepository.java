package org.empresaabc.salesmanagement.modules.facturacion.query.repository;

import org.empresaabc.salesmanagement.modules.facturacion.query.document.FacturaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturacionQueryRepository
        extends MongoRepository
        <FacturaDocument, String> {

    Optional<FacturaDocument>
    findByNumeroFactura(
            String numeroFactura
    );

    Optional<FacturaDocument>
    findByCodigoVenta(
            String codigoVenta
    );
}