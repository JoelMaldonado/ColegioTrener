//
//  PruebaViewModelView1.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI

struct PruebaViewModel2View: View {
    @StateObject private var viewModel = PruebaViewModel2ViewModel()
    var body: some View {
        VStack {
            Text("ViewModel 2")
                .font(.title)
                .fontWeight(.bold)
                .foregroundStyle(.colorP1)
            
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
    PruebaViewModel2View()
}
