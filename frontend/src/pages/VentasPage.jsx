import {

  useEffect,
  useState

}

from "react";

import
VentasLayout

from

"../layouts/VentasLayout";

import
TablaVentas

from

"../components/ventas/TablaVentas";

import {

  obtenerVentas,
  obtenerVentaPorCodigo

}

from

"../services/ventasService";

import Swal
from "sweetalert2";

export default function
VentasPage() {

  const [

    ventas,

    setVentas

  ]

  = useState([]);

  const [

    codigoBusqueda,

    setCodigoBusqueda

  ]

  = useState("");

useEffect(() => {

  document.title =

    "Área Ventas";

  cargarVentas();

  const interval =

    setInterval(

      () => {

        if (

          !codigoBusqueda.trim()

        ) {

          cargarVentas();

        }

      },

      5000

    );

  return () =>

    clearInterval(
      interval
    );

}, [

  codigoBusqueda

]);
  const cargarVentas =

  async () => {

    try {

      const data =

        await obtenerVentas();

      const ventasOrdenadas =

        data.sort(

          (

            a,
            b

          ) =>

            new Date(

              b.fechaVenta

            )

            -

            new Date(

              a.fechaVenta

            )

        );

      setVentas(

        ventasOrdenadas

      );

    }

    catch (

      error

    ) {

      console.error(

        error

      );

    }

  };

  const buscarVenta =

  async () => {

    if (

      !codigoBusqueda

    ) {

      cargarVentas();

      return;

    }

    try {

      const venta =

        await obtenerVentaPorCodigo(

          codigoBusqueda

        );

      setVentas([

        venta

      ]);

    }

    catch (

      error

    ) {

      Swal.fire({

        title:
          "No encontrada",

        text:

          "No existe una venta con ese código.",

        icon:
          "warning",

        confirmButtonColor:
          "#166534"

      });

    }

  };

  const limpiarBusqueda =
  () => {

    setCodigoBusqueda("");

    cargarVentas();

  };

  /* ===================
     KPIs
  ==================== */

  const totalVentas =

    ventas.length;

  const totalDinero =

    ventas.reduce(

      (

        total,
        venta

      ) =>

        total +

        Number(

          venta.totalVenta

        ),

      0

    );

  const ultimaVenta =

    ventas.length > 0

    ?

    ventas[0]
    .codigoVenta

    :

    "N/A";

  return (

    <VentasLayout>

      {/* KPIS */}

      <div
        className=
        "kpis-grid"
      >

        <div
          className=
          "kpi-card"
        >

          <h3>

            Total Ventas

          </h3>

          <h2>

            {
              totalVentas
            }

          </h2>

        </div>

        <div
          className=
          "kpi-card"
        >

          <h3>

            Total Vendido

          </h3>

          <h2>

            $

            {

              totalDinero
              .toLocaleString(

                "es-CO"

              )

            }

          </h2>

        </div>

        <div
          className=
          "kpi-card"
        >

          <h3>

            Última Venta

          </h3>

          <h2>

            {
              ultimaVenta
            }

          </h2>

        </div>

      </div>

      <div
        className=
        "ventas-card"
      >

        <div
          className=
          "ventas-header-tools"
        >

          <h1>

            Ventas
            Registradas

          </h1>

          <div
            className=
            "estado-conexion"
          >

            <span
              className=
              "online-dot"
            >

            </span>

            <span>

              Actualización
              automática
              cada
              5 segundos

            </span>

          </div>

          <div
            className=
            "busqueda-container"
          >

            <input

              type="text"

              placeholder=
              "Buscar código venta"

              value={
                codigoBusqueda
              }

              onChange={

                (e) =>

                setCodigoBusqueda(

                  e.target.value

                )

              }

            />

            <button
              onClick={
                buscarVenta
              }
            >

              Buscar

            </button>

            <button
              className=
              "btn-limpiar"
              onClick={
                limpiarBusqueda
              }
            >

              Limpiar

            </button>

          </div>

        </div>

        <TablaVentas
          ventas={
            ventas
          }
        />

      </div>

    </VentasLayout>

  );

}