//
//  PagosPendientesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func PagosPendientes() -> some View {
        
        VStack {
            if viewModel.isLoadingPendientes {
                ProgressView()
                    .padding(.top)
            } else {
                if viewModel.listPagosPendientes.isEmpty {
                    SinInfo(text: "pendientes")
                } else {
                    ScrollView {
                        ForEach(viewModel.listPagosPendientes, id: \.self) { pago in
                           CardPago(pago: pago)
                        }
                    }
                }
            }
            Spacer()
        }
    }
}
