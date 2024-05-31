//
//  ItemCardInscripcion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

struct ItemCardInscripcion: View {
    var inscripcion: Inscripcion
    var ctacli: String
    @StateObject private var viewModel = ItemCardInscripcionViewModel()
    @State private var isOn: Bool
    
    init(inscripcion: Inscripcion, ctacli: String) {
        self.inscripcion = inscripcion
        self.ctacli = ctacli
        _isOn = State(initialValue: inscripcion.estadoinscripcion == "Si")
    }
    
    
    var body: some View {
        
        VStack(spacing: 0) {
            Text("\(inscripcion.inscripcion)")
                .fontWeight(.semibold)
                .padding(4)
                .frame(maxWidth: .infinity, alignment: .leading)
                .background(.gray.opacity(0.4))
            HStack {
                Text("NH:")
                Text("\(inscripcion.codinscripcion)")
                    .fontWeight(.semibold)
                Spacer()
                Text("Importe")
                Text(inscripcion.precio.toSoles())
                    .fontWeight(.semibold)
                Spacer()
                
                Text("¿Inscribir?")
                Text("\(inscripcion.estadoinscripcion.trim())")
                if viewModel.isLoading {
                    ProgressView()
                } else {
                    Switch(
                        bool: $isOn,
                        click: {
                            viewModel.save(inscripcion: inscripcion, ctacli: ctacli)
                        }
                    )
                    /*
                     Toggle("", isOn: $isOn)
                     .frame(width: 60)
                     .onChange(of: isOn) { newValue in
                     if newValue {
                     withAnimation {
                     viewModel.save(inscripcion: inscripcion, ctacli: ctacli)
                     }
                     } else {
                     self.isOn = true
                     }
                     //viewModel.save(inscripcion: inscripcion, ctacli: ctacli)
                     }
                     */
                }
                
                
            }
            .padding(4)
            
            if viewModel.isError {
                Text(viewModel.error ?? "Sin Definir")
            }
        }
        .alert(Text(viewModel.error ?? "Sin Definir"), isPresented: $viewModel.isError, actions: {})
        .alert(Text(viewModel.mensaje), isPresented: $viewModel.isSuccess, actions: {})
    }
}

class ItemCardInscripcionViewModel: ObservableObject {
    
    @Published var isLoading = false
    @Published var isError: Bool = false
    @Published var isSuccess: Bool = false
    @Published var mensaje: String = ""
    @Published var error: String?
    
    func save(inscripcion: Inscripcion, ctacli: String) {
        
        self.isLoading = true
        InscripcionService.shared.saveInscripcion(
            ctacli: ctacli,
            codtipoinscripcion: inscripcion.codtipoinscripcion,
            codinscripcion: inscripcion.codinscripcion
        ) { res in
            switch res {
            case .success(let data):
                self.isSuccess = true
                self.mensaje = data
                self.isLoading = false
                
            case .failure(let err):
                self.isLoading = false
                self.error = err
                self.isError = true
                self.setError()
            }
        }
        
    }
    
    func setError() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            self.isError = false
        }
    }
    
}

struct Switch: View {
    @Binding var bool: Bool
    @State var alert = false
    var click: () -> Void
    var body: some View {
        ZStack {
            Button {
                if bool {
                    alert.toggle()
                } else {
                    bool.toggle()
                    click()
                }
            } label: {
                Text(bool ? "Si" : "No")
                    .font(.footnote)
                    .frame(width: 30, height: 15)
                    .background(bool ? .colorP1 : .colorS1)
                    .foregroundStyle(.white)
            }
            .offset(CGSize(width: bool ? 30 : 0, height: 0))
        }
        .frame(width: 60, height: 15, alignment: .leading)
        .overlay(
            Rectangle()
                .stroke(lineWidth: 1)
        )
        .alert(Text("No se puede realizar esta acción"), isPresented: $alert, actions: {})
    }
}
