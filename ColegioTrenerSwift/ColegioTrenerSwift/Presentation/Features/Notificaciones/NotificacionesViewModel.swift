//
//  NotificacionesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI
import SVProgressHUD

class NotificacionesViewModel : ObservableObject {
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var listNoficaciones: [Notificacion] = []
    
    init() {
        self.listarHijos()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                self.hijoSelected = data.first
                self.getNotificaciones()
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func getNotificaciones() {
        SVProgressHUD.show()
        if let ctacli = self.hijoSelected?.ctacli {
            NotificacionesService.shared.getAll(ctacli: ctacli) { res in
                switch res {
                case .success(let data):
                    self.listNoficaciones = data
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
