//
//  PagosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct PagosView: View {
    
    @StateObject var viewModel = PagosViewModel()
    
    @State private var select : TipoPagos = .Pendientes
    
    var body: some View {
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: {
                    viewModel.getPagos()
                }
            )
            
            VStack {
                Picker("", selection: $select) {
                    Text("Pendientes")
                        .tag(TipoPagos.Pendientes)
                    Text("Vencidas")
                        .tag(TipoPagos.Vencidas)
                    Text("Pagos Realizados")
                        .tag(TipoPagos.Realizados)
                }
                .pickerStyle(.segmented)
                
                VStack {
                    switch select {
                    case .Pendientes:
                        PagosPendientes()
                    case .Vencidas:
                        PagosVencidos()
                    case .Realizados:
                        PagosRealizados()
                    }
                }
            }
            .padding(8)
            .frame(maxHeight: .infinity)
        }
        .background(.white)
    }
}

enum TipoPagos {
    case Pendientes
    case Vencidas
    case Realizados
}

#Preview {
    PagosView()
}
