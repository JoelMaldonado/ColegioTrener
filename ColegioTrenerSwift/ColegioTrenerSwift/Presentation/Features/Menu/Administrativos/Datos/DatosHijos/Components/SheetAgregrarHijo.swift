//
//  SheetAgregrarHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 29/04/24.
//

import SwiftUI
import SwiftUIToast

extension DatosHijosView {
    
    @ViewBuilder
    func SheetAgregrarHijo() -> some View {
        VStack {
            Text("Agregar Hijo")
                .font(.title2)
                .fontWeight(.bold)
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity)
                .padding()
                .background(.colorT1)
            
            VStack(spacing: 18) {
                CajaTexto(
                    text: $viewModel.nombre,
                    label: "Nombres y apellidos",
                    placeholder: "Ingresar nombres y apellidos de su hijo",
                    fontLabel: .callout
                )
                
                CajaTexto(
                    text: $viewModel.fechaTxt,
                    label: "Fecha de Nacimiento",
                    placeholder: "dd/mm/aaaa"
                )
                .keyboardType(.numberPad)
                
                .onChange(of: viewModel.fechaTxt) {
                    viewModel.fechaTxt = formatDateString(input: viewModel.fechaTxt)
                }
                
                HStack {
                    Button {
                        viewModel.insertHijo()
                    } label: {
                        Text("Agregar")
                            .padding(.vertical, 8)
                            .padding(.horizontal)
                            .background(.colorP1)
                            .clipShape(.buttonBorder)
                    }
                    .frame(maxWidth: .infinity)
                    Button {
                        viewModel.agregarHijo = false
                    } label: {
                        Text("Cancelar")
                            .padding(.vertical, 8)
                            .padding(.horizontal)
                            .background(.colorS1)
                            .clipShape(.buttonBorder)
                    }
                    .frame(maxWidth: .infinity)
                }
                .fontWeight(.bold)
                .foregroundStyle(.white)
                .padding(.top)
            }
            .padding()
            
            
            Spacer()
            SUIToastViewContainer(stackOverlap: .stack)
        }
    }
    
    func formatDateString(input: String) -> String {
        var formattedDate = input.filter { $0.isNumber }

        if formattedDate.count > 2 {
            let index = formattedDate.index(formattedDate.startIndex, offsetBy: 2)
            formattedDate.insert("/", at: index)
        }
        if formattedDate.count > 5 {
            let index = formattedDate.index(formattedDate.startIndex, offsetBy: 5)
            formattedDate.insert("/", at: index)
        }
        if formattedDate.count > 10 {
            let endIndex = formattedDate.index(formattedDate.startIndex, offsetBy: 10)
            formattedDate = String(formattedDate.prefix(upTo: endIndex))
        }

        return formattedDate
    }
}

#Preview {DatosHijosView()}
