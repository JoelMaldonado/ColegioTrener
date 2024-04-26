//
//  PagosVencidosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func PagosVencidos() -> some View {
        VStack {
            
            if viewModel.isLoadingVencidos {
                ProgressView()
                    .padding(.top)
            } else {
                
            }
            
            if viewModel.listPagosVencidos.isEmpty {
                SinInfo(text: "vencidos")
            } else {
                ScrollView {
                    ForEach(viewModel.listPagosVencidos, id: \.self) { pago in
                        CardPago(pago: pago)
                    }
                }
            }
            Spacer()
        }
    }
}
