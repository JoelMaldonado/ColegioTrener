//
//  NotificacionesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 1/02/24.
//

import Foundation

class NotificacionesViewModel : ObservableObject {
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
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
                self.hijoSelected = data.first
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
}
