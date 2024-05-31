//
//  CitaInformeView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct CitaInformeView: View {
    @StateObject private var viewModel = CitaInformeViewModel()
    
    private var years: [Date] = {
        var years: [Date] = []
        let calendar = Calendar.current
        let currentDate = Date()
        
        for i in 0..<5 {
            if let newYear = calendar.date(byAdding: .year, value: -i, to: currentDate) {
                years.append(newYear)
            }
        }
        
        return years
    }()
    
    var body: some View {
        VStack {
            
            
            VStack {
                HStack(spacing: 12){
                    Text("Año")
                    HStack {
                        Picker("", selection: $viewModel.year) {
                            ForEach(years, id: \.self){ anio in
                                Text(anio.format(pattern: "yyyy"))
                                    .tag(anio)
                            }
                        }
                        .tint(.white)
                        Spacer()
                    }
                    .frame(maxWidth: .infinity)
                    .padding(.trailing, 8)
                    .border(.white, width: 1)
                }
                HStack {
                    ForEach(TrimestreTab.allCases, id: \.self){ trimestre in
                        let ic = if viewModel.trimestreTab == trimestre {
                            "circle.fill"
                        } else {
                            "circle"
                        }
                        Button {
                            withAnimation {
                                viewModel.trimestreTab = trimestre
                            }
                            viewModel.getInformes()
                        } label: {
                            Image(systemName: ic)
                            Text(trimestre.name())
                                .lineLimit(1)
                        }
                        
                    }
                }
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(.colorS1)
            .foregroundStyle(.white)
            
            VStack(spacing: 12) {
                ForEach(viewModel.lista, id: \.self){ cita in
                    CardInforme(cita: cita)
                }
            }
            .padding(8)
            
            Spacer()
        }
        .alert(isPresented: $viewModel.isError) {
            Alert(title: Text("Warning"), message: Text(viewModel.error ?? "Sin Definir"))
        }
    }
}

struct CardInforme: View {
    var cita: CitaInforme
    var body: some View {
        VStack {
            Text(cita.nalumno)
                .bold()
                .frame(maxWidth: .infinity)
                .multilineTextAlignment(.center)
                .foregroundStyle(.white)
                .padding(.vertical, 4)
                .background(.colorT1)
            VStack {
                HStack {
                    Image(systemName: "calendar")
                    Text("Fecha")
                    Text(cita.fechacita)
                        .foregroundStyle(.black)
                    Spacer()
                }
                .padding(.top, 1)
                HStack {
                    Image(systemName: "clock")
                    Text("Horario")
                    Text(cita.horario)
                        .foregroundStyle(.black)
                    Spacer()
                }
                HStack {
                    Image(systemName: "door.left.hand.closed")
                    Text("Clase")
                    Text(cita.clase)
                        .foregroundStyle(.black)
                    Spacer()
                }
                HStack {
                    Image(systemName: "eye.fill")
                    Text("Observación")
                    Text(cita.observa)
                        .foregroundStyle(.black)
                    Spacer()
                }
                .padding(.bottom, 10)
            }
            .foregroundStyle(.gray)
            .padding(.horizontal)
            .font(.footnote)
        }
        .background(.white)
        .clipShape(.rect(cornerRadius: 16))
        .shadow(radius: 8)
    }
}

enum TrimestreTab: CaseIterable {
    case Uno
    case Dos
    case Tres
    
    func name() -> String {
        switch self {
        case .Uno:
            return "I Trimestre"
        case .Dos:
            return "II Trimestre"
        case .Tres:
            return "III Trimestre"
        }
    }
    
    func num() -> String {
        switch self {
        case .Uno:
            return "1"
        case .Dos:
            return "2"
        case .Tres:
            return "3"
        }
    }
    
    func code() -> String {
        switch self {
        case .Uno:
            return "1"
        case .Dos:
            return "2"
        case .Tres:
            return "3"
        }
    }
}
