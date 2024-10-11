//
//  JustificacionView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI
import SwiftUIToast

struct JustificacionView: View {
    @StateObject private var viewModel = JustificacionViewModel()
    @State var isPresented = false
    
    @State var prueba = ""
    var body: some View {
        ZStack {
            VStack(spacing: 12) {
                SelectHijo(
                    hijoSelected: $viewModel.hijoSelected,
                    listHijos: viewModel.listHijos,
                    click: { ctacli in
                        Task {
                            await viewModel.getInasistencias(ctacli: ctacli)
                        }
                    }
                )
                
                VStack(spacing: 0){
                    Text("Inasistencias injustificadas")
                        .bold()
                        .foregroundStyle(.white)
                        .frame(maxWidth: .infinity)
                        .frame(height: 24)
                        .background(.colorT1)
                    HStack {
                        Text("Fecha")
                            .frame(maxWidth: .infinity)
                        Rectangle()
                            .frame(maxHeight: .infinity)
                            .frame(width: 1)
                            .foregroundStyle(.colorT1)
                        Text("Estado")
                            .frame(maxWidth: .infinity)
                        Rectangle()
                            .frame(maxHeight: .infinity)
                            .frame(width: 1)
                            .foregroundStyle(.colorT1)
                        Text("Acción")
                            .frame(maxWidth: .infinity)
                    }
                    .bold()
                    .frame(maxWidth: .infinity)
                    .frame(height: 24)
                    .foregroundStyle(.white)
                    .background(.colorS1)
                    ForEach(viewModel.listInasistencia, id: \.self) { item in
                        ItemJustificacion(inasistencia: item, viewModel: viewModel)
                    }
                    
                }
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.colorT1, lineWidth: 2)
                )
                .background(.white)
                .clipShape(.rect(cornerRadius: 12))
                
                Spacer()
            }
            SUIToastViewContainer(stackOverlap: .stack)
        }
        .background(.white)
    }
}

#Preview {
    JustificacionView()
}
