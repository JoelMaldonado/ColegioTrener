//
//  InscripcionesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI
import SVProgressHUD

class InscripcionesViewModel : ObservableObject {
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    
    @Published var listInscripciones: [Inscripcion] = []
    
    @Published var alert = false
    @Published var isError = false
    @Published var error: String?
    
    
    
    init() {
        self.listarHijos()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                if let findHijo = data.first {
                    self.hijoSelected = findHijo
                    self.listarInscripciones(ctacli: findHijo.ctacli)
                }
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func listarInscripciones(ctacli: String) {
        SVProgressHUD.show()
        self.listInscripciones = []
        InscripcionService.shared.getListIncripciones(ctacli: ctacli) { res in
            switch res {
            case .success(let data):
                self.listInscripciones = data
                if let first = data.first {
                    if first.inscripcionbloqueo {
                        self.alert = true
                    }
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
