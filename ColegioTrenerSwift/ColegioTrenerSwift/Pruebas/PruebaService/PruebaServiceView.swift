//
//  PruebaServiceView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI

struct PruebaServiceView: View {
    @StateObject private var viewModel = PruebaServiceViewModel()
    var body: some View {
        VStack {
            Text("Service")
                .font(.title)
                .fontWeight(.bold)
                .foregroundStyle(.colorP1)
            Text("USUARIO: \(viewModel.usuario ?? "Sin Definir")")
            
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
    PruebaServiceView()
}
