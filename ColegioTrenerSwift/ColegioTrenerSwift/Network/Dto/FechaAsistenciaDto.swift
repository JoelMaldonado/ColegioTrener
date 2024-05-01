//
//  FechaAsistenciaDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

enum LeyendaAsistenciaTab: CaseIterable{
    case Tardanza
    case Justificada
    case Injustificada
    case Asesoria
    
    func color() -> Color {
        switch self {
        case .Tardanza:
                .yellow
        case .Justificada:
                .green
        case .Injustificada:
                .red
        case .Asesoria:
                .purple
        }
    }
    
    func name() -> String {
        switch self {
        case .Tardanza:
            "Tardanza"
        case .Justificada:
            "I. Justificada"
        case .Injustificada:
            "I. Injustificada"
        case .Asesoria:
            "Asesoría"
        }
    }
}

struct FechaAsistenciaDto: Codable {
    let anoaca: String?
    let mes: Int?
    let dia: Int?
    let ctacli: String?
    let codgra: String?
    let ctaemp: String?
    let semana: Int?
    let leyenda: String?
    let leyendapp: String?
    
    func toDomain() -> FechaAsistencia {
        var calendar = Calendar(identifier: .gregorian)
        
        var dateComponents = DateComponents()
        dateComponents.year = Int(anoaca ?? Date.now.format(pattern: "yyyy"))
        dateComponents.month = mes
        dateComponents.day = dia
        
        let fecha = calendar.date(from: dateComponents)
        
        var leyend: LeyendaAsistenciaTab
        
        switch self.leyendapp?.trimmingCharacters(in: .whitespacesAndNewlines) {
        case "Tardanza":
            leyend = .Tardanza
        case "Justificada":
            leyend = .Justificada
        case "Injustificada":
            leyend = .Injustificada
        default:
            leyend = .Asesoria
        }
        
        return FechaAsistencia(
            fecha: fecha ?? .now,
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codgra: codgra?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctaemp: ctaemp?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            semana: semana ?? 0,
            leyenda: leyend
        )
    }
}
