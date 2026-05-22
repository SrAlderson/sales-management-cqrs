export default function
TablaContabilidad({

  movimientos

}) {

  const
  formatearFecha =

  (

    fecha

  ) => {

    return new Date(

      fecha

    ).toLocaleString(

      "es-CO",

      {

        day:
          "2-digit",

        month:
          "2-digit",

        year:
          "numeric",

        hour:
          "2-digit",

        minute:
          "2-digit"

      }

    );

  };

  const
  formatearTipo =

  (

    tipo

  ) => {

    const tipos = {

      INGRESO_VENTA:
        "Ingreso venta",

      DEVOLUCION:
        "Devolución",

      AJUSTE_CONTABLE:
        "Ajuste contable"

    };

    return tipos[
      tipo
    ] || tipo;

  };

  const
  formatearEstado =

  (

    estado

  ) => {

    const estados = {

      REGISTRADO:
        "Registrado",

      PROCESADO:
        "Procesado",

      ANULADO:
        "Anulado"

    };

    return estados[
      estado
    ] || estado;

  };

  const
  formatearMoneda =

  (

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
      className=
      "tabla-container"
    >

      <table
        className=
        "tabla-cont"
      >

        <thead>

          <tr>

            <th>
              Código Movimiento
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
              Valor
            </th>

            <th>
              Estado
            </th>

          </tr>

        </thead>

        <tbody>

          {

            movimientos.length > 0

            ?

            (

              movimientos.map(

                (

                  movimiento

                ) => (

                  <tr
                    key={
                      movimiento.id
                    }
                  >

                    <td>

                      {
                        movimiento
                        .codigoMovimiento
                      }

                    </td>

                    <td>

                      {
                        movimiento
                        .codigoVenta
                      }

                    </td>

                    <td>

                      {
                        movimiento
                        .cliente
                      }

                    </td>

                    <td>

                      <span

                        className={`tipo-mov ${movimiento.tipoMovimiento.toLowerCase()}`}

                      >

                        {

                          formatearTipo(

                            movimiento.tipoMovimiento

                          )

                        }

                      </span>

                    </td>

                    <td>

                      {

                        formatearFecha(

                          movimiento.fechaMovimiento

                        )

                      }

                    </td>

                    <td>

                      {

                        formatearMoneda(

                          movimiento.valorMovimiento

                        )

                      }

                    </td>

                    <td>

                      <span

                        className={`estado-mov ${movimiento.estadoMovimiento.toLowerCase()}`}

                      >

                        {

                          formatearEstado(

                            movimiento.estadoMovimiento

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
                  movimientos
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