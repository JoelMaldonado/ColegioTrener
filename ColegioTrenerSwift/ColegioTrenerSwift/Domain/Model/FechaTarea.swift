//
//  FechaTarea.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

struct FechaTarea: Hashable {
    let fechaAsignacion: Date
    let estado: LeyendaPendientesTab?
    let cantidad: Int
}

extension [FechaTarea] {
    
    func toFechaCalendar() -> [FechaCalendar] {
        var listFechaCalendar: [FechaCalendar] = []
        
        let list = Dictionary(
            grouping: self,
            by: { $0.fechaAsignacion }
        ).values.map{ $0 }
        
        list.forEach { fechas in
            if let first = fechas.first {
                
                var colors: [Color] = []
                
                fechas.forEach { fecha in
                    var color: Color
                    if let estado = fecha.estado {
                        switch estado {
                        case .NoTarea:
                            color = .colorT1
                        case .Pendiente:
                            color = .yellow
                        case .Revisado:
                            color = .green
                        }
                        colors.append(color)
                    }
                }
                
                let fechaCalendar = FechaCalendar(
                    fecha: first.fechaAsignacion,
                    color: colors
                )
                
                listFechaCalendar.append(fechaCalendar)
            }
        }
        
        return listFechaCalendar
    }
}
