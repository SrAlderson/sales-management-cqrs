export default function TablaDistribucion({
  despachos
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

  const formatearEstado = (
    estado
  ) => {

    const estados = {

      PENDIENTE_PREPARACION:
        "Pendiente preparación",

      EN_PREPARACION:
        "En preparación",

      DESPACHADO:
        "Despachado",

      EN_TRANSITO:
        "En tránsito",

      ENTREGADO:
        "Entregado",

      CANCELADO:
        "Cancelado"

    };

    return (
      estados[estado]
      || estado
    );

  };

  return (

    <div
      className="tabla-container"
    >

      <table
        className="tabla-dist"
      >

        <thead>

          <tr>

            <th>
              Código Despacho
            </th>

            <th>
              Venta
            </th>

            <th>
              Cliente
            </th>

            <th>
              Ciudad
            </th>

            <th>
              Dirección
            </th>

            <th>
              Fecha
            </th>

            <th>
              Estado
            </th>

          </tr>

        </thead>

        <tbody>

          {

            despachos.length > 0

            ?

            (

              despachos.map(

                (
                  despacho
                ) => (

                  <tr
                    key={
                      despacho.id
                    }
                  >

                    <td>

                      {
                        despacho.codigoDespacho
                      }

                    </td>

                    <td>

                      {
                        despacho.codigoVenta
                      }

                    </td>

                    <td>

                      {
                        despacho.cliente
                      }

                    </td>

                    <td>

                      {
                        despacho.ciudad
                      }

                    </td>

                    <td>

                      {
                        despacho.direccionEntrega
                      }

                    </td>

                    <td>

                      {

                        formatearFecha(

                          despacho.fechaCreacion

                        )

                      }

                    </td>

                    <td>

                      <span

                        className={

                          `estado-dist
                          ${despacho.estadoDespacho.toLowerCase()}`

                        }

                      >

                        {

                          formatearEstado(

                            despacho.estadoDespacho

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
                  despachos
                  registrados

                </td>

              </tr>

            )

          }

        </tbody>

      </table>

    </div>

  );

}