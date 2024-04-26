//
//  DatosHijosViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI
import SVProgressHUD

class DatosHijosViewModel: ObservableObject {
    
    @Published var nombre = ""
    @Published var fecha: Date = .now
    @Published var agregarHijo = false
    
    @Published var listHijos: [DatosHijo] = []
    
    @Published var isError = false
    @Published var error: String?
    
    init() {
        self.getDatosHijos()
    }
    
    func getDatosHijos() {
        SVProgressHUD.show()
        DatosService.shared.getDatosHijos { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func insertHijo() {
        DatosService.shared.insertDatoHijo(
            nombre: nombre,
            fechaNac: fecha.format(pattern: "dd/MM/yyyy")
        ) { res in
            switch res {
            case .success(let data):
                if data {
                    self.agregarHijo = false
                    self.nombre = ""
                    self.fecha = .now
                }
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
}
