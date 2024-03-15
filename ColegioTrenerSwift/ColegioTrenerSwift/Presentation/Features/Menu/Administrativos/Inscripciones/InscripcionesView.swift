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
            TopView(title: "Inscripciones")
            SelectHijo()
            Spacer()
        }
    }
}

#Preview {
    InscripcionesView()
}
