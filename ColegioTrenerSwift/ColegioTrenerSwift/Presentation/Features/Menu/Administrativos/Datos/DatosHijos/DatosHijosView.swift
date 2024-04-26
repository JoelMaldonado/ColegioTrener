//
//  DatosHijosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosHijosView: View {
    
    @ObservedObject var viewModel = DatosHijosViewModel()
    
    var body: some View {
        VStack {
            
            HStack {
                Text("*Agregar el nombre de todos los hijos")
                Spacer()
                Button {
                    viewModel.agregarHijo.toggle()
                } label: {
                    Text("Agregar")
                        .padding(.vertical, 5)
                        .padding(.horizontal, 12)
                        .background(.colorP1, in: .rect(cornerRadius: 12))
                }
            }
            .padding()
            .foregroundStyle(.white)
            .background(.colorT1, in: .rect(cornerRadius: 16))
            
            ForEach(viewModel.listHijos, id: \.self) { hijo in
                ItemDatoHijo(hijo)
            }
            
            Spacer()
        }
        .padding()
        .sheet(isPresented: $viewModel.agregarHijo) {
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
                    
                    DatePicker(
                        "Fecha de Nacimiento",
                        selection: $viewModel.fecha,
                        displayedComponents: .date
                    )
                    .datePickerStyle(.automatic)
                    
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
            }
        }
    }
}

#Preview {
    DatosHijosView()
}
