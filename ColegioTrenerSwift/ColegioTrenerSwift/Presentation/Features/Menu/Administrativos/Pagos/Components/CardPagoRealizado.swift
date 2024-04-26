//
//  CardPagoRealizado.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func CardPagoRealizado(_ pago: Pago) -> some View {
        VStack {
            ZStack {
                Text("Nro. pago: 01")
                    .padding(.leading, 6)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .font(.footnote)
                Text("\(pago.codconcepto) \(pago.concepto)")
                    .font(.callout)
                    .bold()
            }
            .padding(.vertical, 4)
            .background(.colorS1)
            .foregroundStyle(.white)
            
            HStack {
                VStack(alignment: .leading){
                    Text("Doc: D/I \(pago.numdoc): \(pago.tipdoc)")
                    Text("Fec. movimiento: \(pago.fecven.format())")
                }
                Spacer()
                VStack(alignment: .leading){
                    HStack{
                        Image(systemName: "person")
                        VStack(alignment: .leading){
                            Text("Importe: \(pago.importepagado.toSoles())")
                            Text("Mora: \(pago.mora.toSoles())")
                        }
                    }
                    Text("Fec. penalidad: \(pago.fecpag.format())")
                }
            }
            .padding(.horizontal, 8)
            .font(.footnote)
        }
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.colorS1, lineWidth: 1)
        )
        .clipShape(.rect(cornerRadius: 8))
    }
    
}
