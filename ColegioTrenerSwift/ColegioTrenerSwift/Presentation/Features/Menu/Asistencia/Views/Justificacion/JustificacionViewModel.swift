//
//  JustificacionViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI

class JustificacionViewModel: ObservableObject {
    
    @Published var hijoSelected: HijoTrener?
    @Published var listHijos: [HijoTrener] = []
    @Published var listInasistencia: [Inasistencia] = []
    @Published var listRazones: [Razon] = []
    @Published var isPresented = false
    
    @Published var isLoading = false
    
    init() {
        self.listarHijos()
        self.getListRazones()
    }
    
    func listarHijos() {
        DatosService.shared.getHijosTrener { res in
            switch res {
            case .success(let data):
                self.listHijos = data
                if let first = data.first {
                    self.hijoSelected = first
                    Task {
                        await self.getInasistencias(ctacli: first.ctacli)
                    }
                }
            case .failure(let err):
                if let message = err {
                    showToast(message: message)
                }
            }
        }
    }
    
    func getListRazones() {
        Task {
            do {
                self.listRazones = try await AsistenciaService.shared.getListRazones()
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
    
    func getInasistencias(ctacli: String) async {
        do {
            self.listInasistencia = []
            self.listInasistencia = try await AsistenciaService.shared.getInasistencias(ctacli: ctacli)
        } catch {
            switch error {
            case let errorNetwork as ErrorNetwork:
                showToast(message: errorNetwork.message)
            default:
                showToast(message: error.localizedDescription)
            }
        }
    }
    
    func sendJustificacion(
        inasistencia: Inasistencia,
        idRazon: String?,
        comentario: String,
        archivo: URL?
    ) async {
        do {
            
            DispatchQueue.main.async {
                self.isLoading = true
            }
            guard let idRazon = idRazon else { throw ErrorNetwork.motivo("Debes seleccionar una razon") }
            
            guard let ctacli = hijoSelected?.ctacli else { throw ErrorNetwork.motivo("No se pudo obtener el hijo") }
            
            guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { throw ErrorNetwork.motivo("No se pudo obtener el usuario") }
            
            var base64 = ""
            
            if let fileName = archivo {
                let fileData = try Data(contentsOf: fileName)
                base64 = fileData.base64EncodedString()
            }
            
            let request = SendJustificacionRequest(
                fecha: inasistencia.fecha.format(),
                ctacli: ctacli,
                ctamae: ctamae,
                idrazon: idRazon,
                comentario: comentario,
                filename: base64
            )
            
            let response = try await AsistenciaService.shared.sendJustificacion(request: request)
            await self.getInasistencias(ctacli: ctacli)
            
            DispatchQueue.main.async {
                self.isLoading = false
                self.isPresented = false
                showToast(message: response)
            }
        } catch {
            DispatchQueue.main.async {
                self.isLoading = false
                switch error {
                case let errorNetwork as ErrorNetwork:
                    showToast(message: errorNetwork.message)
                default:
                    showToast(message: error.localizedDescription)
                }
            }
        }
    }
    
}
