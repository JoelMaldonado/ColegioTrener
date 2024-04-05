//
//  DiariaAcumuladaView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DiariaAcumuladaView: View {
    
    @StateObject private var viewModel = DiariaAcumuladaViewModel()
    @State private var fecha : Date = .now
    @State private var asistencias: [Date: Bool] = [:]
    
    var body: some View {
        
        VStack(spacing: 0){
            TopView(title: "Asistencia")
            SelectHijo()
            
            ScrollView {
                
                DatePicker("Fecha", selection: $fecha, displayedComponents: .date)
                    .datePickerStyle(.graphical)
                
            }
        }
    }
}

#Preview {
    DiariaAcumuladaView()
}
