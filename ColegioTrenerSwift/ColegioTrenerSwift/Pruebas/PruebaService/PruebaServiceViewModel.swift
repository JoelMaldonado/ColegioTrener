//
//  PruebaServiceViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI


class PruebaServiceViewModel: ObservableObject {
    
    @Published var list: [HijoTrener] = []
    @Published var isLoading = false
    @Published var isError = false
    @Published var error: String?
    
    @Published var usuario = UserDefaults.standard.string(forKey: Keys.loginUser)
    
    init() {
        getList()
    }
    
    private func getList() {
        self.isLoading = true
        DatosService.shared.getHijosTrener { res in
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
    }
}
