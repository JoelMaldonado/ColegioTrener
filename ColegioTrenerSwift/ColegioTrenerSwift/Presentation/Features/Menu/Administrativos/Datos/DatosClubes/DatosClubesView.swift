//
//  DatosClubesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosClubesView: View {
    @StateObject var viewModel = DatosClubesViewModel()
    var body: some View {
        VStack {
            
            HStack {
                Text("*Clubes a los que pertenece")
                Spacer()
                Button {
                    viewModel.agregarClub = true
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
                ForEach(viewModel.list, id: \.self) { item in
                    ItemClub(item)
                }
            }
            
            Spacer()
        }
        .sheet(isPresented: $viewModel.agregarClub) {
            SheetAgregarClub()
        }
        .alert(isPresented: $viewModel.isError) {
            Alert(title: Text("Error"), message: Text(viewModel.error ?? "Sin definir"))
        }
        .alert(isPresented: $viewModel.alertEliminar) {
            Alert(
                title: Text("Warning"),
                message: Text("¿Seguro de eliminar?"),
                primaryButton: .default(Text("Si, eliminar")) {
                    viewModel.deleteClub()
                },
                secondaryButton: .cancel(Text("Cancelar"))
            )
        }
        .padding()
    }
}

#Preview {
    DatosClubesView()
}
