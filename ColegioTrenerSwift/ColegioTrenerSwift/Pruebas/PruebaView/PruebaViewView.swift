//
//  PruebaViewView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI

struct PruebaViewView: View {
    @StateObject private var viewModel = PruebaViewViewModel()
    var usuario = UserDefaults.standard.string(forKey: Keys.loginUser)
    var body: some View {
        VStack {
            Text("View")
                .font(.title)
                .fontWeight(.bold)
                .foregroundStyle(.colorP1)
            Text("USUARIO: \(usuario ?? "Sin Definir")")
            
            Button {
                viewModel.getList(ctamaenull: usuario)
            } label: {
                Text("Obtener")
                    .padding(.vertical, 8)
                    .padding(.horizontal)
                    .background(.colorP1, in: .rect(cornerRadius: 4))
                    .foregroundStyle(.white)
            }
            
            if viewModel.isLoading {
                ProgressView()
            } else {
                if viewModel.list.isEmpty {
                    Text("Sin Datos")
                } else {
                    ForEach(viewModel.list, id: \.self) { item in
                        Text(item.nombre)
                    }
                }
            }
        }
        .alert(isPresented: $viewModel.isError) {
            Alert(title: Text(viewModel.error ?? "Sin Definir"))
        }
    }
}

#Preview {
    PruebaViewView()
}
