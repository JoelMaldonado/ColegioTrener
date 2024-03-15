//
//  NotificacionesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 1/02/24.
//

import SwiftUI

struct NotificacionesView: View {
    @StateObject private var viewModel = NotificacionesViewModel()
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Notificaciones")
            SelectHijo()
            
            VStack{}
                .frame(maxHeight: .infinity)
        }
    }
}

#Preview {
    NotificacionesView()
}
