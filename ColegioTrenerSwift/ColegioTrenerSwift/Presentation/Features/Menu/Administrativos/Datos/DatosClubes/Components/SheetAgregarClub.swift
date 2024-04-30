//
//  SheetAgregarClub.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 30/04/24.
//

import SwiftUI
import SwiftUIToast

extension DatosClubesView {
    
    @ViewBuilder
    func SheetAgregarClub() -> some View {
        VStack {
            Text("Agregar Club")
                .font(.title2)
                .fontWeight(.bold)
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity)
                .padding()
                .background(.colorT1)
            
            VStack(spacing: 18) {
                CajaSelect(
                    text: $viewModel.club,
                    list: viewModel.listComboClubs.map { $0.descrip },
                    label: "Club",
                    fontLabel: .callout
                )
                .onChange(of: viewModel.club) {
                    if let code = viewModel.listComboClubs.first(where: { $0.descrip == viewModel.club }) {
                        viewModel.codClub = code.codigo
                    }
                }
                
                CajaTexto(
                    text: $viewModel.nroCarnet,
                    label: "Número de carnet",
                    placeholder: "Ingresar número de carnet",
                    fontLabel: .callout
                )
                .keyboardType(.numberPad)
                
                CajaSelect(
                    text: $viewModel.vinculo,
                    list: ["Padre", "Madre"],
                    label: "Vinculo",
                    fontLabel: .callout
                )
                .onChange(of: viewModel.vinculo) {
                    switch viewModel.vinculo {
                    case "Padre":
                        viewModel.codVinculo = "001"
                    case "Madre":
                        viewModel.codVinculo = "002"
                    default:
                        viewModel.codVinculo = "003"
                    }
                }
                
                HStack {
                    Button {
                        viewModel.insertClub()
                    } label: {
                        Text("Agregar")
                            .padding(.vertical, 8)
                            .padding(.horizontal)
                            .background(.colorP1)
                            .clipShape(.buttonBorder)
                    }
                    .frame(maxWidth: .infinity)
                    Button {
                        viewModel.agregarClub = false
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
}

#Preview {
    DatosClubesView()
}
