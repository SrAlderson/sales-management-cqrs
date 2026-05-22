import axios
from "axios";

const API_URL =

"http://localhost:9090/query/contabilidad";

/* ======================
   OBTENER TODOS
====================== */

export const
obtenerMovimientos =

async () => {

  const response =

    await axios.get(
      API_URL
    );

  return response.data;

};

/* ======================
   BUSCAR MOVIMIENTO
====================== */

export const
obtenerMovimientoPorCodigo =

async (

  codigoMovimiento

) => {

  const response =

    await axios.get(

      `${API_URL}/${codigoMovimiento}`

    );

  return response.data;

};

/* ======================
   BUSCAR POR VENTA
====================== */

export const
obtenerMovimientoPorVenta =

async (

  codigoVenta

) => {

  const response =

    await axios.get(

      `${API_URL}/venta/${codigoVenta}`

    );

  return response.data;

};