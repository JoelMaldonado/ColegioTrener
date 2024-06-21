//
//  FechaTareaDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI


enum LeyendaPendientesTab: CaseIterable{
    case NoTarea
    case Pendiente
    case Revisado
    
    func color() -> Color {
        switch self {
        case .NoTarea:
                .colorT1
        case .Pendiente:
                .yellow
        case .Revisado:
                .green
        }
    }
    
    func name() -> String {
        switch self {
        case .NoTarea:
            "No hizo tarea"
        case .Pendiente:
            "Pendiente"
        case .Revisado:
            "Revisado"
        }
    }
}

struct FechaTareaDto: Codable {
    let fechaasignacion: String?
    let estado: String?
    let cantidad: Double?
    
    
    
    func toDomain() -> FechaTarea {
        
        var estado: LeyendaPendientesTab?
        
        switch self.estado?.trimmingCharacters(in: .whitespacesAndNewlines) {
        case "No hizo tarea":
            estado = .NoTarea
        case "Pendiente":
            estado = .Pendiente
        case "Revisado":
            estado = .Revisado
        default:
            estado = nil
        }
        
        return FechaTarea(
            fechaAsignacion: fechaasignacion?.toDate() ?? .now,
            estado: estado,
            cantidad: Int(self.cantidad ?? 0)
        )
    }
}
