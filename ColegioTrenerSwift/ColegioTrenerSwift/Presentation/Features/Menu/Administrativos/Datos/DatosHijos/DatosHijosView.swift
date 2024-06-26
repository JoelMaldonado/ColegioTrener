//
//  DatosHijosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosHijosView: View {
    
    @ObservedObject var viewModel = DatosHijosViewModel()
    
    var body: some View {
        VStack {
            
            HStack {
                Text("*Agregar el nombre de todos los hijos")
                Spacer()
                Button {
                    viewModel.agregarHijo.toggle()
                } label: {
                    Text("Agregar")
                        .padding(.vertical, 5)
                        .padding(.horizontal, 12)
                        .background(.colorP1, in: .rect(cornerRadius: 12))
                }
            }
            .padding()
            .foregroundStyle(.white)
            .background(.colorT1, in: .rect(cornerRadius: 16))
            
            ScrollView {
                ForEach(viewModel.listHijos, id: \.self) { hijo in
                    ItemDatoHijo(hijo)
                }
            }
            
            Spacer()
            
        }
        .padding()
        .sheet(isPresented: $viewModel.agregarHijo) {
            SheetAgregrarHijo()
        }
        .alert(isPresented: $viewModel.isError) {
            Alert(
                title: Text("Error"),
                message: Text(viewModel.error ?? "Sin definir")
            )
        }
        .alert(isPresented: $viewModel.alertEliminar) {
            Alert(
                title: Text("Warning"),
                message: Text("¿Seguro de eliminar?"),
                primaryButton: .default(Text("Si, eliminar")) {
                    viewModel.deleteHijo()
                },
                secondaryButton: .cancel(Text("Cancelar"))
            )
        }
    }
}

#Preview {
    DatosHijosView()
}
