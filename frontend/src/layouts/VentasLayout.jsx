import
VentasHeader

from

"../components/ventas/VentasHeader";

import
VentasFooter

from

"../components/ventas/VentasFooter";

export default function
VentasLayout({

  children

}) {

  return (

    <div
      className=
      "ventas-layout"
    >

      <VentasHeader />

      <main
        className=
        "ventas-main"
      >

        {children}

      </main>

      <VentasFooter />

    </div>

  );

}