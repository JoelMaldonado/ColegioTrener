//
//  PagosViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import Foundation

class PagosViewModel : ObservableObject {
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var listPagosPendientes: [Pago] = []
    @Published var listPagosVencidos: [Pago] = []
    @Published var listPagosRealizados: [Pago] = []
    
    @Published var isLoadingPendientes = false
    @Published var isLoadingVencidos = false
    @Published var isLoadingRealizados = false
    
    
    @Published var year: Date = .now
    
    init() {
        self.listarHijos()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                self.hijoSelected = data.first
                self.getPagos()
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func getPagos() {
        self.getPagosPendientes()
        self.getPagosVencidos()
        self.getPagosRealizados()
    }
    
    private func getPagosPendientes() {
        if let ctacli = hijoSelected?.ctacli {
            self.isLoadingPendientes = true
            PagoService.shared.getListPagos(
                ctacli: ctacli,
                estado: "Pendiente"
            ) { res in
                switch res {
                case .success(let data):
                    self.listPagosPendientes = data
                    self.isLoadingPendientes = false
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    self.isLoadingPendientes = false
                }
            }
        }
    }
    
    private func getPagosVencidos() {
        if let ctacli = hijoSelected?.ctacli {
            self.isLoadingVencidos = true
            PagoService.shared.getListPagos(
                ctacli: ctacli,
                estado: "Vencido"
            ) { res in
                switch res {
                case .success(let data):
                    self.listPagosVencidos = data
                    self.isLoadingVencidos = false
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    self.isLoadingVencidos = false
                }
            }
        }
    }
    
    func getPagosRealizados() {
        if let ctacli = hijoSelected?.ctacli {
            self.isLoadingRealizados = true
            let anio = year.format(pattern: "yyyy")
            PagoService.shared.getListPagosRealizados(
                ctacli: ctacli,
                anio: anio
            ) { res in
                switch res {
                case .success(let data):
                    self.listPagosRealizados = data
                    self.isLoadingRealizados = false
                case .failure(let err):
                    self.error = err
                    self.isError = true
                    self.isLoadingRealizados = false
                }
            }
        }
    }
}
