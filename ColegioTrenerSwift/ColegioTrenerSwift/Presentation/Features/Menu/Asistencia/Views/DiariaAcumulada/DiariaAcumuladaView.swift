//
//  DiariaAcumuladaView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DiariaAcumuladaView: View {
    
    @StateObject var viewModel = DiariaAcumuladaViewModel()
    
    var body: some View {
        
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: { ctacli in
                    viewModel.listarFechas()
                    viewModel.getInfoAsistencia()
                }
            )
        
            ScrollView {
                VStack {
                    
                    CustomCalendar(
                        date: $viewModel.fecha,
                        list: viewModel.listFechas.map { $0.toFechaCalendar() }
                    )
                    
                    LeyendaAcumulada()
                    
                    CardDiariaAcumulado(viewModel.infoAsistencia)
                    
                }
            }
        }
        .background(.white)
        .alert(isPresented: $viewModel.isError) {
            Alert(
                title: Text("Error"),
                message: Text(viewModel.error ?? "Sin definir")
            )
        }
    }
}

#Preview {
    DiariaAcumuladaView()
}
