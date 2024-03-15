//
//  ItemClub.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI

struct ItemClub : View {
    var body: some View {
        HStack(spacing: 0) {
            Rectangle()
                .foregroundStyle(.colorS1)
                .frame(width: 16)
            Text("Padre")
                .padding(.horizontal)
                .frame(maxHeight: .infinity)
                .background(.colorT1.opacity(0.8))
            VStack(alignment: .leading){
                HStack {
                    Text("Club: Regatas")
                    Spacer()
                    Button {
                        
                    } label: {
                        Image(systemName: "pencil")
                            .foregroundStyle(.black)
                    }
                }
                HStack {
                    Text("Nro Carné: 25463")
                    Spacer()
                    Button {
                        
                    } label: {
                        Image(systemName: "trash.fill")
                            .foregroundStyle(.colorS1)
                    }
                }
            }
            .padding(.horizontal)
            .frame(maxHeight: .infinity)
            .background(.colorT1.opacity(0.5))
        }
        .frame(height: 80)
        .cornerRadius(16)
    }
}
