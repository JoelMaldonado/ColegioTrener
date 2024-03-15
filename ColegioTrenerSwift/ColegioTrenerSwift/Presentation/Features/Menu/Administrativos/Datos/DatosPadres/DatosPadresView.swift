//
//  DatosPadresView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosPadresView: View {
    @State private var isSelected = false
    @State private var isShowingAlert = false
    @StateObject private var viewModel = DatosPadresViewModel()
    var listTipoDoc = ["DNI", "Pasaporte", "CE"]
    var opciones = ["Opción 1", "Opción 2", "Opción 3"]
    
    
    var body: some View {
        VStack(spacing: 12){
            
            Picker(selection: $isSelected, content: {
                Text("Padre")
                    .tag(false)
                Text("Madre")
                    .tag(true)
            }, label: {}
            )
            .pickerStyle(.segmented)
            
            ScrollView(.vertical){
                VStack(spacing: 12){
                    CajaText(
                        icon: "person",
                        label: "Nombres",
                        text: $viewModel.nombres
                    )
                    
                    CajaText(
                        icon: "person",
                        label: "Nombre que usa",
                        text: $viewModel.apodo
                    )
                    
                    CajaText(
                        icon: "mail",
                        label: "Tipo de Documento",
                        text: $viewModel.tipoDoc
                    )
                    
                    CajaText(
                        icon: "person.text.rectangle",
                        label: "Número de Documento",
                        text: $viewModel.doc
                    )
                    
                    CajaText(
                        icon: "calendar",
                        label: "Fecha de Nacimiento",
                        text: $viewModel.fechaNac
                    )
                    
                    CajaText(
                        icon: "building",
                        label: "Distrito",
                        text: $viewModel.distrito
                    )
                    
                    CajaText(
                        icon: "mappin",
                        label: "Dirección",
                        text: $viewModel.direc
                    )
                    
                    CajaText(
                        icon: "candybarphone",
                        label: "Celular",
                        text: $viewModel.cel
                    )
                    
                    CajaText(
                        icon: "phone",
                        label: "Teléfono",
                        text: $viewModel.telf
                    )
                    
                    CajaText(
                        icon: "building",
                        label: "Empresa",
                        text: $viewModel.empresa
                    )
                    
                    CajaText(
                        icon: "suitcase",
                        label: "Cargo",
                        text: $viewModel.cargoArea
                    )
                    
                    CajaText(
                        icon: "phone.and.waveform",
                        label: "Teléfono de Empresa",
                        text: $viewModel.telfEmpresa
                    )
                    
                    CajaText(
                        icon: "envelope",
                        label: "Correo",
                        text: $viewModel.correo
                    )
                    
                    Button {
                        self.isShowingAlert = true
                    } label: {
                        Text("Grabar")
                            .fontWeight(.heavy)
                            .foregroundStyle(.white)
                            .frame(height: 40)
                            .padding(.horizontal)
                            .background(.colorS1)
                            .clipShape(.buttonBorder)
                    }
                }
            }
        }
        .alert(isPresented: $isShowingAlert) {
            Alert(title: Text("Usuario guardado"), message: Text("El usuario se ha guardado correctamente"), dismissButton: .default(Text("Aceptar")))
        }
        .padding()
    }
}

#Preview {
    DatosPadresView()
}
