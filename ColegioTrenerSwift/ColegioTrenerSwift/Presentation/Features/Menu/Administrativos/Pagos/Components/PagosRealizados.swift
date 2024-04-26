//
//  PagosRealizadosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func PagosRealizados() -> some View {
        VStack {
            
            SelectYearRealizado()
            
            if viewModel.isLoadingRealizados {
                ProgressView()
                    .padding(.top)
            } else {
                if viewModel.listPagosRealizados.isEmpty {
                    SinInfo(text: "realizados")
                } else {
                    ScrollView {
                        ForEach(viewModel.listPagosRealizados, id: \.self) { pago in
                            CardPagoRealizado(pago)
                        }
                    }
                }
            }
            Spacer()
        }
    }
    
}
