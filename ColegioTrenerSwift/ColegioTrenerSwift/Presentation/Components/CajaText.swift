//
//  CajaText.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI

struct CajaText: View {
    var icon: String
    var label: String
    @Binding var text: String
    var body: some View {
        HStack {
            Image(systemName: icon)
            ZStack {
                if text.isEmpty {
                    Text(label)
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                TextField("", text: $text)
            }
        }
        .padding()
        .background(.white, in: .rect(cornerRadius: 12))
    }
}
