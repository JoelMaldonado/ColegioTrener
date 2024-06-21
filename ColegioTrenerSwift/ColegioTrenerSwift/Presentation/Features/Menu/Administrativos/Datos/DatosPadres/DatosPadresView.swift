//
//  DatosPadresView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

enum TipoFamiliar {
    case padre
    case madre
}

extension TipoFamiliar {
    func code() -> String {
        return switch self {
        case .padre:
            "padre"
        case .madre:
            "madre"
        }
    }
}

struct DatosPadresView: View {
    @StateObject var viewModel = DatosPadresViewModel()
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            VStack(spacing: 12){
                
                SelectTipoFamiliar()
                
                HStack {
                    CajaTexto(
                        text: $viewModel.nombres,
                        label: "Nombres",
                        placeholder: "Ingresar nombres",
                        isActive: false
                    )
                    
                    CajaTexto(
                        text: $viewModel.apodo,
                        label: "Nombre que usa",
                        placeholder: "Ingresar nombre a usar",
                        isActive: false
                    )
                }
                
                HStack {
                    
                    CajaSelect(
                        text: $viewModel.tipoDoc,
                        list: ["DNI", "Pasaporte", "CE"],
                        label: "Tipo de Documento",
                        isActive: false
                    )
                    
                    CajaTexto(
                        text: $viewModel.doc,
                        label: "Número de Documento",
                        placeholder: "Ingresar número de documento",
                        isActive: false
                    )
                    
                }
                HStack {
                    
                    CajaTexto(
                        text: $viewModel.fechaNac,
                        label: "Fecha de Nacimiento",
                        placeholder: "Ingresar fecha de nacimiento"
                    )
                    
                    CajaSelectDistrito(
                        distrito: $viewModel.distrito,
                        list: viewModel.listDistritos
                    )
                    
                }
                
                CajaTexto(
                    text: $viewModel.direc,
                    label: "Dirección",
                    placeholder: "Ingresar dirección"
                )
                
                HStack {
                    
                    CajaTexto(
                        text: $viewModel.cel,
                        label: "Celular",
                        placeholder: "Ingresar celular"
                    )
                    
                    CajaTexto(
                        text: $viewModel.telf,
                        label: "Teléfono",
                        placeholder: "Ingresar teléfono"
                    )
                }
                
                CajaTexto(
                    text: $viewModel.empresa,
                    label: "Empresa",
                    placeholder: "Ingresar empresa"
                )
                HStack {
                    CajaTexto(
                        text: $viewModel.cargoArea,
                        label: "Cargo",
                        placeholder: "Ingresar cargo"
                    )
                    
                    CajaTexto(
                        text: $viewModel.telfEmpresa,
                        label: "Teléfono de Empresa",
                        placeholder: "Ingresar teléfono de empresa"
                    )
                }
                
                CajaTexto(
                    text: $viewModel.correo,
                    label: "Email* Si es mas de un correo, separarlo por un punto y coma",
                    placeholder: "Ingresar correo",
                    isActive: !viewModel.bloqueoEnabled
                )
                
                Button {
                    viewModel.save()
                } label: {
                    Text("Grabar")
                        .fontWeight(.heavy)
                        .foregroundStyle(.white)
                        .frame(height: 40)
                        .padding(.horizontal)
                        .background(.colorS1)
                        .clipShape(.rect(cornerRadius: 16))
                }
            }
            .padding(12)
        }
        .alert(Text(viewModel.error ?? "Sin definir"), isPresented: $viewModel.isError, actions: {})
        .alert(Text(viewModel.mensaje), isPresented: $viewModel.isSuccess, actions: {})
    }
    
}

#Preview {
    DatosPadresView()
}
