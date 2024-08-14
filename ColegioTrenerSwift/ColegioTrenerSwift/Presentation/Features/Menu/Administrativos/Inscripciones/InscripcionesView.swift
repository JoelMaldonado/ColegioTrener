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
                click: { ctacli in
                    viewModel.listarInscripciones(ctacli: ctacli)
                }
            )
            
            ScrollView {
                if viewModel.listInscripciones.isEmpty {
                    Text("Sin Resultados")
                } else {
                    let list = Dictionary(grouping: viewModel.listInscripciones, by: { $0.tipoinscripcion }).values.map{ $0 }
                    ForEach(list, id: \.self) { inscripciones in
                        if let ctacli = viewModel.hijoSelected?.ctacli {
                            CardInscripcion(list: inscripciones, ctacli: ctacli)
                        }
                    }
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
