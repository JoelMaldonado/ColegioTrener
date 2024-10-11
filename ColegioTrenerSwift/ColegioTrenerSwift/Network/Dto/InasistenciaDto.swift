//
//  InasistenciaDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI

struct InasistenciaDto: Codable {
    let fecha: String?
    let estado: String?
    let accion: String?
    
    func toDomain() -> Inasistencia {
        return Inasistencia(
            fecha: self.fecha?.toDate() ?? .now,
            estado: self.estado?.trim() ?? "" ,
            accion: self.accion?.trim() ?? ""
        )
    }
}
