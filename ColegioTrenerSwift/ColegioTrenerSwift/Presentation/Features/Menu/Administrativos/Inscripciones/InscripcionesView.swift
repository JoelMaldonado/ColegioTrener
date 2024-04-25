//
//  InscripcionesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct InscripcionesView: View {
    
    @StateObject private var viewModel = InscripcionesViewModel()
    
    var body: some View {
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: {
                    
                }
            )
            Spacer()
        }
    }
}

#Preview {
    InscripcionesView()
}
