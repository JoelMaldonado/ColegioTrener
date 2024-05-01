//
//  InscripcionesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct InscripcionesView: View {
    
    @StateObject private var viewModel = InscripcionesViewModel()

    var back: () -> Void
    
    var body: some View {
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: {
                    viewModel.listarInscripciones()
                }
            )
            
            VStack {
                let list = Dictionary(grouping: viewModel.listInscripciones, by: { $0.codtipoinscripcion }).values.map{ $0 }
                ForEach(list, id: \.self) { inscripciones in
                    CardInscripcion(list: inscripciones)
                }
            }
            .padding(4)
            
            Spacer()
        }
        .alert(isPresented: $viewModel.alert) {
            Alert(
                title: Text("Warning"),
                message: Text("No se encuentra activo la inscripcion de talleres"),
                dismissButton: .cancel(
                    Text("ok"),
                    action: {
                        back()
                    }
                )
            )
        }
        .frame(maxWidth: .infinity)
        .background(.white)
    }
}
