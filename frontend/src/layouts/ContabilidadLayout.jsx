import
ContabilidadHeader

from

"../components/contabilidad/ContabilidadHeader";

import
ContabilidadFooter

from

"../components/contabilidad/ContabilidadFooter";

export default function
ContabilidadLayout({

  children

}) {

  return (

    <div
      className=
      "cont-layout"
    >

      <ContabilidadHeader />

      <main
        className=
        "cont-main"
      >

        {children}

      </main>

      <ContabilidadFooter />

    </div>

  );

}