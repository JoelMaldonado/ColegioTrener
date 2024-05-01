//
//  FechaAsistencia.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

struct FechaAsistencia {
    let fecha: Date
    let ctacli: String
    let codgra: String
    let ctaemp: String
    let semana: Int
    let leyenda: LeyendaAsistenciaTab
    
    func toFechaCalendar() -> FechaCalendar {
        let color: Color
        switch leyenda {
        case .Tardanza:
            color = .yellow
        case .Justificada:
            color = .green
        case .Injustificada:
            color = .red
        case .Asesoria:
            color = .purple
        }
        return FechaCalendar(
            fecha: fecha,
            color: [color]
        )
    }
}
