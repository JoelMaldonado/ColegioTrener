//
//  CitaInformeView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct CitaInformeView: View {
    
    @StateObject private var viewModel = CitaInformeViewModel()
    
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Cita Informe")
        }
    }
}

#Preview {
    CitaInformeView()
}
