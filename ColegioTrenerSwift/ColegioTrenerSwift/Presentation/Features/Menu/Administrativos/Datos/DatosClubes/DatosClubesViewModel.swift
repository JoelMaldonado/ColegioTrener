//
//  DatosClubesViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import Foundation
import SVProgressHUD
import SwiftUIToast

class DatosClubesViewModel : ObservableObject {
    
    @Published var list: [DatosClub] = []
    @Published var isError = false
    @Published var error: String?
    
    @Published var agregarClub = false
    @Published var club = ""
    @Published var codClub = ""
    @Published var nroCarnet = ""
    @Published var vinculo = "Padre"
    @Published var codVinculo = "001"
    
    @Published var listComboClubs: [ComboClub] = []
    
    @Published var alertEliminar = false
    @Published var alertEliminarClub: DatosClub?
    
    init() {
        self.listarComboClubs()
        self.listarDatosClubs()
    }
    
    func listarComboClubs() {
        DatosService.shared.getComboClubs { res in
            switch res {
            case .success(let data):
                self.listComboClubs = data
            case .failure(let err):
                self.error = err
                self.isError = true
            }
        }
    }
    
    func listarDatosClubs() {
        SVProgressHUD.show()
        DatosService.shared.getDatosClubs { res in
            switch res {
            case .success(let data):
                self.list = data
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func insertClub() {
        
        if self.codClub.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Club invalido",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        if self.nroCarnet.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Número de carnet invalido",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        if self.codVinculo.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Vinculo invalido",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        
        SVProgressHUD.show()
        DatosService.shared.editDatosClub(
            accion: .Crear,
            codClub: codClub,
            codParentesco: codVinculo,
            nroCarnet: nroCarnet
        ) { res in
            switch res {
            case .success(let data):
                if data {
                    self.agregarClub = false
                    self.club = ""
                    self.codClub = ""
                    self.nroCarnet = ""
                    self.codVinculo = "001"
                    self.vinculo = "Padre"
                    self.listarDatosClubs()
                }
                SVProgressHUD.dismiss()
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
    }
    
    func deleteClub() {
        SVProgressHUD.show()
        if let club = alertEliminarClub {
            DatosService.shared.editDatosClub(
                accion: .Eliminar,
                codClub: club.codclub,
                codParentesco: club.codvinculo,
                nroCarnet: club.nrocar
            ){ res in
                switch res {
                case .success(let data):
                    self.listarDatosClubs()
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
