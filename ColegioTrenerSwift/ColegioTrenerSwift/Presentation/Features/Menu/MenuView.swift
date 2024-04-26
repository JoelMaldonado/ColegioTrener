//
//  MenuView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct MenuView: View {
    @State var tab : MenuTab = .Home
    var body: some View {
        VStack(spacing: 0){
            TopView(menuTab: $tab)
            ZStack {
                Image(.salon)
                    .resizable()
                    .ignoresSafeArea()
                ZStack {
                    switch tab {
                    case .Home:
                        MenuHome(tab: $tab)
                    case .Datos:
                        DatosView()
                    case .Pagos:
                        PagosView()
                    case .Inscripciones:
                        InscripcionesView(
                            back: {
                                self.tab = .Home
                            }
                        )
                    case .DiariaAcumulada:
                        DiariaAcumuladaView()
                    case .Pendientes:
                        PendientesView()
                    case .Incumplimientos:
                        IncumplimientosView()
                    case .CitaInforme:
                        CitaInformeView()
                    case .Autorizaciones:
                        AutorizacionesView()
                    case .Notificaciones:
                        NotificacionesView()
                    case .Perfil:
                        PerfilView()
                    }
                }
                .background(.gray.opacity(0.6))
            }
        }
        
    }
    
}

enum MenuTab {
    case Home
    case Datos
    case Pagos
    case Inscripciones
    case DiariaAcumulada
    case Pendientes
    case Incumplimientos
    case CitaInforme
    case Autorizaciones
    case Notificaciones
    case Perfil
    
    func name()-> String {
        switch self {
        case .Home:
            return "Inicio"
        case .Datos:
            return "Datos"
        case .Pagos:
            return "Pagos"
        case .Inscripciones:
            return "Inscripciones"
        case .DiariaAcumulada:
            return "Diaria Acumulada"
        case .Pendientes:
            return "Pendientes"
        case .Incumplimientos:
            return "Incumplimientos"
        case .CitaInforme:
            return "Cita Informe"
        case .Autorizaciones:
            return "Autorizaciones"
        case .Notificaciones:
            return "Notificaciones"
        case .Perfil:
            return "Cambios datos generales"
        }
    }
}


struct ItemMenuView : View {
    var label : String
    var body: some View {
        VStack(alignment: .leading, spacing: 0){
            Text(label)
                .font(.title2)
                .bold()
            Rectangle()
                .frame(height: 2)
        }
        .foregroundStyle(.white)
    }
}

#Preview {
    MenuView()
}
