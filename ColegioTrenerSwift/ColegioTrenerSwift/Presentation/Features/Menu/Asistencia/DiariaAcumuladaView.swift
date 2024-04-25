//
//  DiariaAcumuladaView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DiariaAcumuladaView: View {
    
    @StateObject var viewModel = DiariaAcumuladaViewModel()
    @State private var fecha : Date = .now
    @State private var asistencias: [Date: Bool] = [:]
    
    var body: some View {
        
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: {
                    
                }
            )
        
            ScrollView {
                VStack {
                    CustomCalendar()
                    
                    LeyendaAcumulada()
                    
                    CardDiariaAcumulado()
                }
            }
        }
        .background(.white)
    }
}

#Preview {
    DiariaAcumuladaView()
}
