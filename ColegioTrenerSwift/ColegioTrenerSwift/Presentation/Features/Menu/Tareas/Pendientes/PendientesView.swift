//
//  PendientesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct PendientesView: View {
    
    @StateObject private var viewModel = PendientesViewModel()
    
    var body: some View {
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: { ctacli in
                    viewModel.getFechasTareas()
                    viewModel.getTareasByDia()
                }
            )
            
            
            ScrollView(.vertical) {
                VStack(spacing: 0){
                    CustomCalendar(
                        date: $viewModel.fecha,
                        list: viewModel.listFechaTareas.toFechaCalendar()
                    )
                    LeyendaPendientes()
                    
                    let list = Dictionary(
                        grouping: viewModel.listInfoTareasPendientes,
                        by: { $0.fechaasignacion }
                    ).values.map{ $0 }
                    
                    ForEach(list, id: \.self) { info in
                        CardInfoTarea(info)
                    }
                    
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
    PendientesView()
}
