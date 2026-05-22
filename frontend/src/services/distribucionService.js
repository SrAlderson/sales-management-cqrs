import axios
from "axios";

const API_URL =

"http://localhost:9090/query/distribucion";

/* ======================
   OBTENER TODOS
====================== */

export const
obtenerDespachos =

async () => {

  const response =

    await axios.get(
      API_URL
    );

  return response.data;

};

/* ======================
   BUSCAR DESPACHO
====================== */

export const
obtenerDespachoPorCodigo =

async (

  codigoDespacho

) => {

  const response =

    await axios.get(

      `${API_URL}/${codigoDespacho}`

    );

  return response.data;

};

/* ======================
   BUSCAR POR VENTA
====================== */

export const
obtenerDespachoPorVenta =

async (

  codigoVenta

) => {

  const response =

    await axios.get(

      `${API_URL}/venta/${codigoVenta}`

    );

  return response.data;

};