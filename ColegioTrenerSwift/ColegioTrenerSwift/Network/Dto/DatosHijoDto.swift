//
//  DatosHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI

struct DatosHijoDto: Codable {
    let nombre: String?
    let fechanacimiento: String?
    
    func toDomain() -> DatosHijo {
        return DatosHijo(
            nombre: nombre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fechaNac: fechanacimiento?.toDate() ?? .now
        )
    }
}
