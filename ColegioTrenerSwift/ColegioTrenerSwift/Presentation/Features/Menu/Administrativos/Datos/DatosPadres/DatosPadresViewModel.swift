//
//  DatosPadresViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import Foundation

class DatosPadresViewModel : ObservableObject {
    
    @Published var nombres = ""
    @Published var apodo = ""
    @Published var tipoDoc = ""
    @Published var doc = ""
    @Published var fechaNac = ""
    @Published var distrito = ""
    @Published var direc = ""
    @Published var cel = ""
    @Published var telf = ""
    @Published var empresa = ""
    @Published var cargoArea = ""
    @Published var telfEmpresa = ""
    @Published var correo = ""
}
