//
//  PagosRealizadosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct PagosRealizadosView: View {
    @State private var selectedYear: Int = 2022
    
    let startYear = 2020
    let endYear = 2030
    
    var body: some View {
        VStack {
            Picker("Año", selection: $selectedYear) {
                ForEach(startYear...endYear, id: \.self) { year in
                    Text("\(year)")
                }
            }
            .pickerStyle(.menu)
            .background(.colorS1)
            .clipShape(.buttonBorder)
            .accentColor(.white)
            
            VStack {
                ZStack {
                    HStack{
                        Text("Nro. pago: 01")
                        Spacer()
                    }
                    .padding(.leading)
                    Text("P24 CUOTA MATRICULA")
                }
                .bold()
                .background(.colorS1)
                .foregroundStyle(.white)
                
                HStack{
                    VStack(alignment: .leading){
                        Text("Doc: D/I 0000383433")
                        Text("Grado/Lugar Pago: 08B")
                        Text("Fec. movimiento: 26/01/2023")
                    }
                    VStack(alignment: .leading){
                        HStack{
                            Image(systemName: "person")
                            VStack(alignment: .leading){
                                Text("Importe: 2600.00")
                                Text("Mora: 0.00")
                            }
                        }
                        Text("Fec. penalidad: 26/01/2023")
                    }
                }
            }
            .font(.footnote)
            .clipShape(.buttonBorder)
            .shadow(radius: 16)
        }
    }
}

#Preview {
    PagosRealizadosView()
}
