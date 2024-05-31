//
//  DatosApoderadoViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

class DatosApoderadoViewModel: ObservableObject {
    
    @Published var nombres = ""
    @Published var apodo = ""
    @Published var tipoDoc = ""
    @Published var doc = ""
    @Published var fechaNac = ""
    @Published var distrito: ComboDistrito?
    @Published var direc = ""
    @Published var cel = ""
    @Published var telf = ""
    @Published var empresa = ""
    @Published var cargoArea = ""
    @Published var telfEmpresa = ""
    @Published var correo = ""
    @Published var bloqueoEnabled: Bool = true
    
    @Published var apoderado: DatosApoderado?
    
    @Published var listDistritos: [ComboDistrito] = []
    
    @Published var isSuccess = false
    @Published var isError = false
    @Published var error: String?
    @Published var mensaje = ""
    
    
    init() {
        self.getDistritos()
    }
    
    func getDistritos() {
        DatosService.shared.getComboDistritos { res in
            switch res {
            case .success(let data):
                self.listDistritos = data
                self.getDatosApoderados()
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func getDatosApoderados() {
        DatosService.shared.getDatosPadres { res in
            switch res {
            case .success(let data):
                self.apoderado = data.first(where: { $0.tipo == .apoderado })
                self.setearDatos(data: self.apoderado)
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    
    func save() {
        DatosService.shared.saveDatos(
            tipo: "apoderado",
            fechaNacimiento: fechaNac,
            distrito: distrito?.coddis,
            direccion: direc,
            celular: cel,
            telefono: telf,
            empresa: empresa,
            cargo: cargoArea,
            telefEmpresa: telfEmpresa,
            email: correo
        ) { res in
            switch res {
            case .success(let data):
                self.isSuccess = true
                self.mensaje = data
                self.getDatosApoderados()
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    
    func setearDatos(data: DatosApoderado?) {
        if let a = data {
            self.nombres = a.nombre
            self.apodo = a.alias
            self.tipoDoc = a.tipodoc
            self.doc = a.documento
            self.fechaNac = a.fechanacimiento.format()
            self.distrito = listDistritos.first(where: { $0.coddis == a.distrito })
            self.direc = a.direccion
            self.cel = a.celular
            self.telf = a.telefono
            self.empresa = a.empresa
            self.cargoArea = a.cargo
            self.telfEmpresa = a.telefempresa
            self.correo = a.e_mailp
            self.bloqueoEnabled = a.emailbloqueo
        }
    }
}
