//
//  CitaInformeDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

struct CitaInformeDto: Codable {
    let nalumno: String?
    let clase: String?
    let fechacita: String?
    let horario: String?
    let observa: String?
    
    func toDomain() -> CitaInforme {
        return CitaInforme(
            nalumno: nalumno?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            clase: clase?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fechacita: fechacita?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            horario: horario?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            observa: observa?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
}
