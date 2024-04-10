//
//  SplashView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 9/04/24.
//

import SwiftUI

struct SplashView: View {
    @Binding var isActive: Bool
    
    var body: some View {
        ZStack {
            
            Color(.colorP1)
                .edgesIgnoringSafeArea(.all)
            
            Image(.logo)
                .resizable()
                .scaledToFit()
                .frame(width: 300)
        }
        .opacity(isActive ? 1 : 0)
    }
}
