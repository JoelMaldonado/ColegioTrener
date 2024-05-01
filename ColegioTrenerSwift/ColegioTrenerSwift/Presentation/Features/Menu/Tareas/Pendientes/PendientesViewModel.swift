//
//  PendientesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI
import SVProgressHUD

class PendientesViewModel : ObservableObject {
    
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var fecha: Date = Date.now {
        didSet {
            self.getFechasTareas()
            self.getTareasByDia()
        }
    }
    
    @Published var listFechaTareas: [FechaTarea] = []
    @Published var listInfoTareasPendientes: [InfoTareaPendiente] = []
    
    init() {
        self.listarHijos()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                self.hijoSelected = data.first
                self.getFechasTareas()
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func getFechasTareas() {
        if let ctacli = hijoSelected?.ctacli {
            SVProgressHUD.show()
            TareaService.shared.getTareasByMonth(
                ctacli: ctacli,
                anio: fecha.format(pattern: "yyyy"),
                mes: fecha.format(pattern: "MM")
            ) { res in
                switch res {
                case .success(let data):
                    self.listFechaTareas = data
                    SVProgressHUD.dismiss()
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    SVProgressHUD.dismiss()
                }
            }
        }
    }
    
    func getTareasByDia() {
        if let ctacli = hijoSelected?.ctacli {
            TareaService.shared.getTareasByDay(
                ctacli: ctacli,
                anio: fecha.format(pattern: "yyyy"),
                mes: fecha.format(pattern: "MM"),
                dia: fecha.format(pattern: "dd")
            ) { res in
                switch res {
                case .success(let data):
                    self.listInfoTareasPendientes = data
                case .failure(let err):
                    self.error = err
                    self.isError = true
                }
            }
        }
    }
}
