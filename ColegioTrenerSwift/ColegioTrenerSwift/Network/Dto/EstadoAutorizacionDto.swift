//
//  EstadoAutorizacionDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

struct EstadoAutorizacionDto: Codable {
    let anoaca: String?
    let ctacli: String?
    let nombre: String?
    let ctamae: String?
    let codgra: String?
    let autorizo: Double?
    
    func toDomain() -> EstadoAutorizacion {
        return EstadoAutorizacion(
            anoaca: anoaca?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            nombre: nombre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctamae: ctamae?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codgra: codgra?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            autorizo: autorizo == 1.0
        )
    }
}
