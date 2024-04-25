//
//  AutorizacionesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import Foundation
import SVProgressHUD

class AutorizacionesViewModel : ObservableObject {
    
    @Published var estado : EstadoAutorizacionTab = .Activos
    
    @Published var autorizacion: Autorizacion?
    @Published var list : [Autorizacion] = []
    @Published var listEstado : [EstadoAutorizacion] = []
    
    @Published var isError = false
    @Published var error : String?
    
    init() {
        self.listarAutorizaciones()
    }
    
    func listarAutorizaciones() {
        SVProgressHUD.show()
        AutorizacionService.shared.listarAutorizaciones(estado: estado.code()) { res in
            switch res {
            case .success(let data):
                self.list = data
                self.autorizacion = data.first
                if self.autorizacion != nil {
                    self.listarEstadoAutorizacion()
                }
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func listarEstadoAutorizacion() {
        SVProgressHUD.show()
        if let idAutorizacion = autorizacion?.idautorizacion {
            AutorizacionService.shared.listarEstadoAutorizacion(idAutorizacion: idAutorizacion) { res in
                switch res {
                case .success(let data):
                    self.listEstado = data
                    SVProgressHUD.dismiss()
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    SVProgressHUD.dismiss()
                }
            }
        }
    }
    
    func autorizar(estado: Bool, ctacli: String) {
        if !estado {
            self.isError = true
            self.error = "Ya se autorizo, no se puede cambiar"
        } else {
            if let idAutorizacion = autorizacion?.idautorizacion {
                SVProgressHUD.show()
                AutorizacionService.shared.autorizarAlumno(idPermiso: idAutorizacion, ctacli: ctacli) { res in
                    switch res {
                    case .success(let data):
                        if data {
                            self.listarEstadoAutorizacion()
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
    }
}
