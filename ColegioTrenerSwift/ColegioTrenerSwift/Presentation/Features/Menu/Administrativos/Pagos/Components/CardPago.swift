//
//  CardPago.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func CardPago(pago: Pago) -> some View {
        VStack(spacing: 0){
            Text("\(pago.concepto)")
                .padding(.vertical, 4)
                .frame(maxWidth: .infinity)
                .background(.colorS1)
                .foregroundStyle(.white)
                .fontWeight(.semibold)
            HStack {
                Text("Deuda:")
                Text("\(pago.saldo.toSoles())")
                    .bold()
                Spacer()
                Text("Fec.ven:")
                Text("\(pago.fecven.format())")
                    .bold()
            }
            .font(.callout)
            .padding(8)
        }
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(.colorS1, lineWidth: 1)
        )
        .clipShape(.rect(cornerRadius: 12))
    }
}
