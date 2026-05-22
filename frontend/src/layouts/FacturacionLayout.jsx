import
FacturacionHeader

from

"../components/facturacion/FacturacionHeader";

import
FacturacionFooter

from

"../components/facturacion/FacturacionFooter";

export default function
FacturacionLayout({

  children

}) {

  return (

    <div
      className=
      "fact-layout"
    >

      <FacturacionHeader />

      <main
        className=
        "fact-main"
      >

        {children}

      </main>

      <FacturacionFooter />

    </div>

  );

}