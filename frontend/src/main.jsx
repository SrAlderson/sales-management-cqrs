import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import "./styles/global.css";

import "./styles/registro.css";
import "./styles/ventas.css";
import "./styles/distribucion.css";
import "./styles/facturacion.css";
import "./styles/contabilidad.css";

ReactDOM.createRoot(
  document.getElementById('root')
).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)