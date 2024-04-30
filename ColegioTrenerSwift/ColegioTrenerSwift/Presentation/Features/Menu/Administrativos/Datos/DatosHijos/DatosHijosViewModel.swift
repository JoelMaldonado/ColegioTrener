//
//  DatosHijosViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI
import SVProgressHUD
import SwiftUIToast

class DatosHijosViewModel: ObservableObject {
    
    @Published var nombre = ""
    @Published var fechaTxt = ""
    @Published var agregarHijo = false
    
    @Published var listHijos: [DatosHijo] = []
    
    @Published var isError = false
    @Published var error: String?
    
    @Published var alertEliminar = false
    @Published var alertEliminarHijo: DatosHijo?
    
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
        
        if nombre.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Nombre invalido",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        
        if !isValidDateString(input: fechaTxt) {
            SUIToast.show(messageItem: .init(
                message: "Fecha invalida",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        
        SVProgressHUD.show()
        DatosService.shared.editDatoHijo(
            accion: .Crear,
            nombre: nombre,
            fechaNac: fechaTxt
        ) { res in
            switch res {
            case .success(let data):
                if data {
                    self.agregarHijo = false
                    self.nombre = ""
                    self.fechaTxt = ""
                    self.getDatosHijos()
                }
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func deleteHijo() {
        SVProgressHUD.show()
        
        if let hijo = alertEliminarHijo {
            DatosService.shared.editDatoHijo(
                accion: .Eliminar,
                nombre: hijo.nombre,
                fechaNac: hijo.fechaNac.format()
            ) { res in
                switch res {
                case .success(let data):
                    if data {
                        self.getDatosHijos()
                    }
                    SVProgressHUD.dismiss()
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    SVProgressHUD.dismiss()
                }
            }
        }
    }
    
    func isValidDateString(input: String, format: String = "dd/MM/yyyy") -> Bool {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        
        return dateFormatter.date(from: input) != nil
    }
    
}
