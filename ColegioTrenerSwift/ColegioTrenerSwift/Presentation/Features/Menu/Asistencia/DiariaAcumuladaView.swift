//
//  DiariaAcumuladaView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DiariaAcumuladaView: View {
    
    @StateObject private var viewModel = DiariaAcumuladaViewModel()
    @State private var fecha : Date = .now
    @State private var asistencias: [Date: Bool] = [:] // Almacena las asistencias (true = presente, false = ausente)

    var body: some View {
        ScrollView {
            
            VStack(spacing: 0){
                TopView(title: "Asistencia")
                SelectHijo()
                
                DatePicker("Fecha", selection: $fecha)
                    .datePickerStyle(.graphical)
                
                MesHeaderView()
                
                // Puedes ajustar el rango de fechas según tus necesidades
                let startDate = Date().addingTimeInterval(-60 * 60 * 24 * 30) // Hace un mes
                let endDate = Date().addingTimeInterval(60 * 60 * 24 * 30) // Dentro de un mes
                
                LazyVGrid(columns: Array(repeating: GridItem(), count: 7), spacing: 10) {
                    ForEach(diasDelMes(startDate: startDate, endDate: endDate), id: \.self) { fecha in
                        DiaView(fecha: fecha, asistencia: asistencias[fecha])
                    }
                }
                
            }
        }
    }
    private func diasDelMes(startDate: Date, endDate: Date) -> [Date] {
            var fecha = startDate
            var dias: [Date] = []
            
            while fecha <= endDate {
                dias.append(fecha)
                fecha = Calendar.current.date(byAdding: .day, value: 1, to: fecha)!
            }
            
            return dias
        }
}

struct MesHeaderView: View {
    var body: some View {
        Text("Febrero 2024")
            .font(.title)
            .fontWeight(.bold)
            .padding(.vertical, 10)
    }
}

struct DiaView: View {
    var fecha: Date
    var asistencia: Bool?
    
    var body: some View {
        VStack {
            Text(obtenerDiaDelMes(fecha: fecha))
                .font(.headline)
            
            if let asistencia = asistencia {
                Circle()
                    .fill(asistencia ? Color.green : Color.red)
                    .frame(width: 10, height: 10)
            }
        }
    }
    
    private func obtenerDiaDelMes(fecha: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "d"
        return formatter.string(from: fecha)
    }
}

#Preview {
    DiariaAcumuladaView()
}
