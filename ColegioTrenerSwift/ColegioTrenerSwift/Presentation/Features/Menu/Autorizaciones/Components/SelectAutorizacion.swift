//
//  SelectAutorizacion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

extension AutorizacionesView {
    
    @ViewBuilder
    func SelectAutorizacion() -> some View {
            VStack(alignment: .leading) {
                Text("Autorización")
                    .bold()
                
                VStack {
                    
                    ForEach(viewModel.list, id: \.self) { autorizacion in
                        HStack {
                            let ic = if viewModel.autorizacion == autorizacion { "circle.fill" }
                            else { "circle" }
                            
                            Button {
                                viewModel.autorizacion = autorizacion
                                viewModel.listarEstadoAutorizacion()
                            } label: {
                                Image(systemName: ic)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 15)
                                    .foregroundStyle(.black)
                            }
                            
                            Text(autorizacion.autorizacion)
                                .font(.system(size: 14))
                            Spacer()
                            
                            Button {
                                if let url = URL(string: autorizacion.linkPdf) {
                                    UIApplication.shared.open(url)
                                }
                            } label: {
                                Image(systemName: "eye.fill")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 30)
                                    .foregroundStyle(.colorP1)
                            }
                        }
                    }
                }
                .padding(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.colorT1, lineWidth: 1.5)
                )
            }
    }
}
