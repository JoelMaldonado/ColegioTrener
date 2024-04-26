//
//  SinInfo.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension PagosView {
    
    @ViewBuilder
    func SinInfo(
        text: String
    ) -> some View {
        HStack {
            Divider()
                .frame(height: 60)
                .frame(width: 15)
                .background(.colorS1)
            Text("No existen conceptos \(text)")
                .frame(maxWidth: .infinity, alignment: .leading)
                .font(.title3)
        }
        .overlay(
        RoundedRectangle(cornerRadius: 8)
            .stroke(.colorS1, lineWidth: 2)
        )
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.white)
        .clipShape(.rect(cornerRadius: 8))
    }
}


#Preview {
    PagosView()
}
