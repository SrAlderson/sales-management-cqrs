export default function TablaFacturacion({
  facturas
}) {

  const formatearFecha = (
    fecha
  ) => {

    return new Date(
      fecha
    ).toLocaleString(
      "es-CO",
      {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit"
      }
    );

  };

  const formatearTipo = (
    tipo
  ) => {

    return tipo ===
      "ELECTRONICA"

      ? "Electrónica"

      : "General";

  };

  const formatearEstado = (
    estado
  ) => {

    const estados = {

      PENDIENTE_IMPRESION:
        "Pendiente impresión",

      GENERADA:
        "Generada",

      ENVIADA:
        "Enviada",

      ERROR_ENVIO:
        "Error envío",

      ANULADA:
        "Anulada"

    };

    return (
      estados[estado]
      || estado
    );

  };

  const formatearMoneda = (
    valor
  ) => {

    return new Intl
      .NumberFormat(
        "es-CO",
        {
          style:
            "currency",

          currency:
            "COP",

          minimumFractionDigits:
            0
        }
      )
      .format(
        valor
      );

  };

  return (

    <div
      className="tabla-container"
    >

      <table
        className="tabla-fact"
      >

        <thead>

          <tr>

            <th>
              Número Factura
            </th>

            <th>
              Venta
            </th>

            <th>
              Cliente
            </th>

            <th>
              Tipo
            </th>

            <th>
              Fecha
            </th>

            <th>
              Total
            </th>

            <th>
              Estado
            </th>

          </tr>

        </thead>

        <tbody>

          {

            facturas.length > 0

            ?

            (

              facturas.map(

                (
                  factura
                ) => (

                  <tr
                    key={
                      factura.id
                    }
                  >

                    <td>

                      {
                        factura.numeroFactura
                      }

                    </td>

                    <td>

                      {
                        factura.codigoVenta
                      }

                    </td>

                    <td>

                      {
                        factura.cliente
                      }

                    </td>

                    <td>

                      <span

                        className={`tipo-factura ${factura.tipoFactura.toLowerCase()}`}

                      >

                        {

                          formatearTipo(

                            factura.tipoFactura

                          )

                        }

                      </span>

                    </td>

                    <td>

                      {

                        formatearFecha(

                          factura.fechaFactura

                        )

                      }

                    </td>

                    <td>

                      {

                        formatearMoneda(

                          factura.totalFactura

                        )

                      }

                    </td>

                    <td>

                      <span

                        className={`estado-fact ${factura.estadoFactura.toLowerCase()}`}

                      >

                        {

                          formatearEstado(

                            factura.estadoFactura

                          )

                        }

                      </span>

                    </td>

                  </tr>

                )

              )

            )

            :

            (

              <tr>

                <td
                  colSpan="7"
                  style={{
                    textAlign:
                      "center",
                    padding:
                      "30px"
                  }}
                >

                  No existen
                  facturas
                  registradas

                </td>

              </tr>

            )

          }

        </tbody>

      </table>

    </div>

  );

}