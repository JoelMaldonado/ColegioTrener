//
//  CitaInformeViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import Foundation
import SVProgressHUD

class CitaInformeViewModel : ObservableObject {
    
    @Published var year: Date = .now
    @Published var trimestreTab = TrimestreTab.Uno
    
    @Published var lista : [CitaInforme] = []
    @Published var isError = false
    @Published var error : String?
    
    init() {
        getTrimestre()
    }
    
    func getTrimestre() {
        SVProgressHUD.show()
        InformeService.shared.getTrimestre { res in
            switch res {
            case .success(let t):
                self.trimestreTab = t
                self.getInformes()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func getInformes() {
        SVProgressHUD.show()
        self.lista = []
        InformeService.shared.getInforme(
            year: self.year.format(pattern: "yyyy"),
            trimestre: self.trimestreTab.code()
        ) { res in
            switch res {
            case .success(let value):
                self.lista = value
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    
}
