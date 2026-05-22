import axios
from "axios";

const COMMAND_URL =

  "http://localhost:9090/ventas";

const QUERY_URL =

  "http://localhost:9090/query/ventas";

/* ==========================
   CREAR VENTA
========================== */

export const crearVenta =

async (

  venta

) => {

  const response =

    await axios.post(

      COMMAND_URL,

      venta

    );

  return response.data;

};

/* ==========================
   CONSULTAR TODAS
========================== */

export const obtenerVentas =

async () => {

  const response =

    await axios.get(

      QUERY_URL

    );

  return response.data;

};

/* ==========================
   CONSULTAR POR CODIGO DE VENTA
========================== */

export const obtenerVentaPorCodigo =

async (

  codigoVenta

) => {

  const response =

    await axios.get(

      `${QUERY_URL}/${codigoVenta}`

    );

  return response.data;

};