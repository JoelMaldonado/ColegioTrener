//
//  PruebaViewViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI

class PruebaViewViewModel: ObservableObject {
    
    @Published var list: [HijoTrener] = []
    @Published var isLoading = false
    @Published var isError = false
    @Published var error: String?
    
    func getList(ctamaenull: String?) {
        if let ctamae = ctamaenull {
            self.isLoading = true
            DatosService.shared.getHijosTrener2(ctamae: ctamae) { res in
                switch res {
                case .success(let data):
                    self.isLoading = false
                    self.list = data
                case .failure(let err):
                    self.isLoading = false
                    self.error = err
                    self.isError = true
                }
            }
        } else {
            self.error = "Sin Usuario"
            self.isError = true
        }
    }
}
