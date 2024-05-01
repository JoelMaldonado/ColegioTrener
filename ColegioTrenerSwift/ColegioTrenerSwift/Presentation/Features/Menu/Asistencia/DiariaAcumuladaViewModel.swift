//
//  DiariaAcumuladaViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI
import SVProgressHUD

class DiariaAcumuladaViewModel : ObservableObject {
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var listFechas: [FechaAsistencia] = []
    
    @Published var infoAsistencia: InfoAsistencia?
    @Published var isLoadingInfo = false
    
    @Published var fecha: Date = Date.now {
        didSet {
            self.listarFechas()
            self.getInfoAsistencia()
        }
    }
    
    init() {
        self.listarHijos()
        self.listarFechas()
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
    
    func listarFechas() {
        if let ctacli = hijoSelected?.ctacli {
            SVProgressHUD.show()
            AsistenciaService.shared.listarFechasAsistenciaAlumno(
                anio: fecha.format(pattern: "yyyy"),
                mes: fecha.format(pattern: "MM"),
                ctacli: ctacli
            ) { res in
                switch res {
                case .success(let data):
                    self.listFechas = data
                    SVProgressHUD.dismiss()
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    SVProgressHUD.dismiss()
                }
            }
        }
    }
    
    func getInfoAsistencia() {
        if let ctacli = hijoSelected?.ctacli {
            self.isLoadingInfo = true
            AsistenciaService.shared.getInfoAsistencia(
                anio: fecha.format(pattern: "yyyy"),
                mes: fecha.format(pattern: "MM"),
                ctacli: ctacli
            ) { res in
                switch res {
                case .success(let data):
                    self.infoAsistencia = data
                    self.isLoadingInfo = false
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    self.isLoadingInfo = false
                }
            }
        }
    }
}
