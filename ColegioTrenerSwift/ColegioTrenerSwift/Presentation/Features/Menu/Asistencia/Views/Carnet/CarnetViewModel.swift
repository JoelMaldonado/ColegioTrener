//
//  CarnetViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI
import SwiftUIToast

class CarnetViewModel: ObservableObject {
    
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var carnetUrl: URL?
    @Published var downloadUrl: URL?
    
    init() {
        self.listarHijos()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                if let first = data.first {
                    self.hijoSelected = first
                    Task {
                        await self.getCarnet(ctacli: first.ctacli)
                    }
                }
                
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func getCarnet(ctacli: String) async {
        do {
            self.carnetUrl = nil
            self.downloadUrl = nil
            let carnet = try await AsistenciaService.shared.getCarnet(ctacli: ctacli)
            if let urlVista = carnet.linkVista {
                self.carnetUrl = URL(string: urlVista)
            }
            if let urlDownload = carnet.linkDescarga {
                self.downloadUrl = URL(string: urlDownload)
            }
        } catch {
            switch error {
            case let errorNetwork as ErrorNetwork:
                showToast(message: errorNetwork.message)
            default:
                showToast(message: error.localizedDescription)
            }
        }
    }
}
