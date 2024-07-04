//
//  NotificacionesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI

struct NotificacionesView: View {
    @StateObject private var viewModel = NotificacionesViewModel()
    var body: some View {
        VStack(spacing: 0){
            
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: {
                    viewModel.getNotificaciones()
                }
            )
            
            VStack {
                if (viewModel.listNoficaciones.isEmpty) {
                    Text("No hay notificaciones")
                        .foregroundStyle(.gray)
                } else {
                    ForEach(viewModel.listNoficaciones, id: \.self) { notif in
                        VStack {
                            HStack {
                                VStack(alignment: .leading) {
                                    Text(notif.titulo ?? "Sin titulo")
                                        .font(.system(size: 14))
                                    Text(notif.descripcion ?? "Sin descripción")
                                        .font(.system(size: 12))
                                }
                                Spacer()
                                if let vinculo = notif.vinculo {
                                    if let url = URL(string: vinculo) {
                                        Button {
                                            UIApplication.shared.open(url)
                                        } label: {
                                            ZStack {
                                                Text("IR")
                                            }
                                            .frame(width: 40, height: 40)
                                            .foregroundStyle(.white)
                                            .background(.colorS1)
                                            .clipShape(.circle)
                                        }
                                    }
                                }
                            }
                            .padding(8)
                            Divider()
                        }
                    }
                    Spacer()
                }
            }
            .frame(maxHeight: .infinity)
        }
        .background(.white)
    }
}
