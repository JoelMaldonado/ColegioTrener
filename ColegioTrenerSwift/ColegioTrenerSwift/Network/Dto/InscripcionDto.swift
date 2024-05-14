//
//  InscripcionDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

struct InscripcionDto: Codable {
    let tipoinscripcion: String?
    let codtipoinscripcion: String?
    let inscripcion: String?
    let codinscripcion: String?
    let precio: Double?
    let estadoinscripcion: String?
    let inscripcionbloqueo: Double?

    func toDomain() -> Inscripcion {
        return Inscripcion(
            tipoinscripcion: tipoinscripcion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codtipoinscripcion: codtipoinscripcion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            inscripcion: inscripcion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codinscripcion: codinscripcion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            precio: precio ?? 0.0,
            estadoinscripcion: estadoinscripcion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            inscripcionbloqueo: inscripcionbloqueo == 0.0
        )
    }
}
